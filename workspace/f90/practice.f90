PROGRAM practice
IMPLICIT NONE
!INTEGER :: I, J, K, L, M 
!real :: N 

character(len=4) :: A = "abcdef", B = "ghik", C = "opq", D = "xy"
character(len=10) :: L, M, N, O

A(2:5) = "678" 
L = A
M = D // D // C
N = B // C
O = D // C // B
!        READ(*,*) I
!        READ(*,*) 
!        READ(*,*) J, K 
!        READ(*,*) 
!        READ(*,*) L, M, N

!        I = 4**1/2*3/2
!        J = 4**(1/2)
!        K = 4**2**3
!        L = (4**2)**3
!        M = 4**(2**3)
!        N = M**(1.0/3.0)

 !       WRITE(*,*) "I = ", I
 !       WRITE(*,*) "J = ", J
 !       WRITE(*,*) "K = ", K 
        WRITE(*,*) "L = ", L
        WRITE(*,*) "M = ", M
        WRITE(*,*) "N = ", N
        WRITE(*,*) "O = ", N
END PROGRAM
