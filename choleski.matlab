% Choleski's Decomposition
% A = L*L'
% A must be SPD -- how do we know that it is 
% Symetric----------------------------------------------->|1 0 3|
% Positive Definite - sylvestre criterion                 |0 1 2|
% cost O(n^3/6) half the time of all of the others        |3 2 1|
% 

L = size(A,1);
for j = 1:n
	temp = A(j,j) - dot( A( J, 1:j-1), A(j, 1:j - 1));
	if temp < 0.0
		error('Matrix is not positive definite')
	end
	A(j, j) = sqrt(temp);
	for i = j+1:n
		A(i,j) = (A(i, j) - dot(A(i,1:j-1),A(j,1:j - 1)))/A(j, j);
	end
end

L = tril(A)
