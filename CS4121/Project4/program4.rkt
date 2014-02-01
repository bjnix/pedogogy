;;functions from questions in programming assignment 4

;1. returns the zero based index of the last occurance of a in L
(define (last a L) (- (length L) (firstIndex a (reverse L))))

;returns the 0 based index for the first occurance of a in L or the length of the array + 1 if a is not
;in the list
(define (firstIndex a L ) 
  (cond [(null? L) (+ (length L) 1)]
        [(eq? (first L) a) 1]
        [else (+ 1 (firstIndex a (rest L)))]
   ))

;2. returns M with a pair of parentheses wrapped around each atom occuring in M
(define (wrap M) 
  (cond[(null? M) M]
       [else (cons (cons (first M) '()) (wrap(rest M)))]))

;3. counts the number of parenthesis in M
(define (count-parens-all M)
  (cond [(null? M) 2]
        [(pair? (first M)) (+(count-parens-all (first M)) (count-parens-all (rest M)))]
        [(null? (first M)) (+(count-parens-all (first M)) (count-parens-all (rest M)))]
        [else (count-parens-all (rest M))]))
;4. builds a list obtained by inserting the item new to the right of all occurrences of the item old in the list M
(define (insert-right-all new old M)
  (cond [(null? M) '()]
        [(pair? (first M)) (cons (first M) (insert-right-all new old (first M)))]
        [(eq? old (first M)) (cons (first M) (insert-right-all new old (cons new (rest M))))]
        [else (cons (first M) (insert-right-all new old (rest M)))]))
;5. M is a list of pairs, returns a list with each pair inverted
(define (invert M) (map reverse M))

;6. returns a flat list of those elements in L that do not satisfy pred
(define (filter-out pred L)
  (cond [(null? L) L]
        [(pred (first L)) (filter-out pred (rest L))]
        [else (cons(first L) (filter-out pred (rest L)))]))

;7. returns a list of pairs that represents the Cartesian product of two flat lists L1 and L2
(define (product L1 L2)
  (cond[(null? L1) L1]
       [else (cons (prodH (first L1) L2) (product (rest L1) L2))]))
(define (prodH l L)
  (cond [(null? L) L]
        [else (cons (cons l (cons (first L) '())) (prodH l (rest L)))]))

;8. returns M with all occurences of a1 replaced by a2 and all occurrences of a2 replaced by a1
(define (swapper a1 a2 M)
  (cond [(null? M) '()]
        [(pair? (first M)) (cons (swapper a1 a2 (first M)) (swapper a1 a2 (rest M)))]
        [(eq? a1 (first M)) (cons a2 (swapper a1 a2 (rest M)))]
        [(eq? a2 (first M)) (cons a1 (swapper a1 a2 (rest M)))]
        [else (cons (first M) (swapper a1  a2(rest M)))]
        )
  )
;9. returns M with all inner parentheses removed. turns list into flat list
(define (flatten M)(if (flat? (term M)) (term M) (flatten (term M)))) 
  ;(cond [(null? M) M]
   ;     [(pair? (first M)) (cons (term (first M)) (flatten (rest M)))]
    ;    [(null? (first M)) (flatten (rest M))]
     ;   [else (cons(first M) (flatten (rest M)))]))
;strips one layer of parentheses
(define (term M)
  (cond [(null? (rest M)) (first M)]
        [(pair? (first M)) (cons (term (first M)) (term (rest M)))]
        [(null? (first M)) (term (rest M))]
        [else (cons (first M) (term (rest M)))]
        ))
;checks to see if a list is flat
(define (flat? M)
  (cond [(null? M) #t]
        [(pair? (first M)) #f]
        [else (flat? (rest M))]))
;10.
(define (binary-tree-insert T n)
  (cond [(null? T) (cons '() (cons n (cons '() '() )))]
        [(> n (second T)) (cons (first T) (cons(second T) (cons (binary-tree-insert (third T) n) '() )))]
        [(< n (second T)) (cons (binary-tree-insert (first T) n) (cons (second T) (cons (third T) '() )))]
        [(eq? (second T) n) T]))

;11.
(define (abstractb f ff n)
  (local ( (define (helper a M)
           (cond
             [(null? M) n]
             [(not (pair? (first M)))(f a M helper)]
             [else (ff a M helper)])))
           helper
           ))
(define rember* (abstractb
                 (lambda (a M f_0) (if (eq? a (first M))
                                   (f_0 a (rest M))
                                   (cons (first M) (f_0 a (rest M)))))
                 (lambda (a M f_0) (cons (f_0 a (first M))
                                     (f_0 a (rest M))))
                 '()
                 ))
(define depth (abstractb
               (lambda (a M f_0) (f_0 0 (rest M)))
               (lambda (a M f_0) (max (add1 (f_0 0 (first M)))
                                  (f_0 0 (rest M))))
               1
               ))

