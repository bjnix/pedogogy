(define f (cons 1 (cons 2 (cons 3 (map 
                                    (lambda (X) (+(third (reverse X)) (* 2 (second (reverse X))) (* 3 (first (reverse X))))) 
                                    (rest f))))))