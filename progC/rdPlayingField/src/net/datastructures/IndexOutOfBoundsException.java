package net.datastructures;
//begin#fragment IndexOutOfBoundsException
/**
 * Runtime exception thrown when one tries to modify a list
 * and the given index is invalid.
//end#fragment IndexOutOfBoundsException
 * @author Roberto Tamassia
//begin#fragment IndexOutOfBoundsException
 */

@SuppressWarnings( "serial" )
public class IndexOutOfBoundsException extends RuntimeException
{
    public IndexOutOfBoundsException( String err )
    {
        super( err );
    }
}
//end#fragment IndexOutOfBoundsException
