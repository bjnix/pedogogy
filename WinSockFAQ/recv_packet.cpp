#include <winsock.h>
#include <assert.h>

static const int prefix_size = 2;
static char g_holding_buffer[1000];
static int g_held_bytes = 0;

// Pass in the socket handle, a pointer to a buffer large enough to hold
// the packet, and the size of that buffer.  Returns the size of the
// packet received on success, or 0 on failure.

int recv_packet(SOCKET sd, char* p_out_buffer, int buffer_size)
{
    int bytes_read = 0;
    int packet_size;
    bool building_prefix = true;

    assert(buffer_size == sizeof(g_holding_buffer));

    // Copy any data remaining from previous call into output buffer.
    if (g_held_bytes > 0) {
        assert(buffer_size >= g_held_bytes);
        memcpy(p_out_buffer, g_holding_buffer, g_held_bytes);
        bytes_read += g_held_bytes;
        g_held_bytes = 0;
    }

    // Read the packet
    while (1) { 
        if (building_prefix) {
            if (bytes_read >= prefix_size) {
                packet_size = 0;
                for (int i = 0; i < prefix_size; ++i) {
                    packet_size <<= 8;
                    packet_size |= p_out_buffer[i];
                }
                building_prefix = false;
                if (packet_size > buffer_size) {
                    // Buffer's too small to hold the packet!
                    // Do error handling here.
                    return 0;
                }
            }
        }

        if (!building_prefix && (bytes_read >= packet_size)) {
            break;  // finished building packet
        }

        int new_bytes_read = recv(sd, p_out_buffer + bytes_read,
                buffer_size - bytes_read, 0);
        if ((new_bytes_read == 0) || (new_bytes_read == SOCKET_ERROR)) {
            return 0;       // do proper error handling here!
        }
        bytes_read += new_bytes_read;
    }

    // If anything is left in the read buffer, keep a copy of it
    // for the next call.
    g_held_bytes = bytes_read - packet_size;
    memcpy(g_holding_buffer, p_out_buffer + packet_size, g_held_bytes);
    
    return packet_size;
}

