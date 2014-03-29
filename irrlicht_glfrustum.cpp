float* CvIrrCamController::create_glfrustum(float left, float right, float bottom, float top, float nearZ, float farZ, float *m)
{
   nearZ = -nearZ;
   farZ = -farZ;
   
   float x = (2.0F*nearZ) / (right-left);
   float y = (2.0F*nearZ) / (top-bottom);
   float a = (right+left) / (right-left);
   float b = (top+bottom) / (top-bottom);
   float c = -(farZ+nearZ) / ( farZ-nearZ);
   float d = -(2.0F*farZ*nearZ) / (farZ-nearZ);

#define M(row,col)  m[col*4+row]
   M(0,0) = -x;     M(0,1) = 0.0F;  M(0,2) = a;      M(0,3) = 0.0F;
   M(1,0) = 0.0F;  M(1,1) = -y;     M(1,2) = b;      M(1,3) = 0.0F;
   M(2,0) = 0.0F;  M(2,1) = 0.0F;  M(2,2) = -c;      M(2,3) = -d;
   M(3,0) = 0.0F;  M(3,1) = 0.0F;  M(3,2) = 1.0F;  M(3,3) = 0.0F;
#undef M
}