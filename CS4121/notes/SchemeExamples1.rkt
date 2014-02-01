;slide 38
(define (len L)
    (local ((define (length-help L n) 
      (if (null? L) 
           n
          (length-help (rest L) (add1 n)))))
     (length-help L 0)            
    )
  )

;slide 44
(define (abstractra f)
  (local ( (define (helper a L)
             (cond [(null? L) '()]
                   [(eq? a (first L)) (f L)]
                   [else (cons (first L) (helper a (rest L)))])) )
   helper
  )
)

(define rem-first
  (abstractra rest))

(define add1-first
  (abstractra (lambda (X) (cons (add1 (first X)) (rest X)))))

;slide 45
(define (compse f g)
  (lambda (x)
    (f (g x))))

(define (compose3 f g h)
  (compose f (compose g h)))

(define (pairscal cal)
  (local ( (define (helper P) 
            (cond [(null? P) '()]
             [else (cons
             (cal (first P) (first (rest P)))
             (helper (cddr P)))])))
    helper
  )
)

(define add-pairs (pairscal +))

(define mul-pairs (pairscal *))

;slide 46
(define (abstractfr f g)
  (local ( (define (helper a L)
             (cond [(null? L) '()]
                   [(eq? a (first L)) (f a L)]
                   [else (g a L)])))
    helper
  )
)

(define filter1 
  (abstractfr (lambda (a L) (cons (first L) (filter1 a (rest L)))) (lambda (a L) (filter1 a (rest L)))))

(define remove1
  (abstractfr (lambda (a L) (remove1 a (rest L))) (lambda (a L) (cons (first L) (remove1 a (rest L))))))
