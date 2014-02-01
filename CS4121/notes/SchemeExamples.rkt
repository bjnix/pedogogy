;slide 21
(define (cube n) (* n n n))

(define (mySecond L) (first (rest L)))

(define (sumFirstThree L) (+ (first L) (second L) (third L)))

;slide 25
; return true for an empty list
(define (lon? L)
  (if (null? L)
      #t
      (and (number? (first L))
           (lon? (rest L)))))

;return false for an empty list
(define (lon1? L)
  (cond [(null? L) #f]
        [(not (number? (first L))) #f]
        [(empty? (rest L)) #t]
        [else (lon1? (rest L))]))

;slide 26
(define (member? L a)
  (cond [(null? L) #f]
        [(eq? (first L) a) #t]
        [else (member? (rest L) a)]))

;slide 27
(define (addxn x n)
  (if (= n 0) x
      (+ x (addxn x (sub1 n)))))

;slide 28
(define (removeThird M)
  (cond [(null? M) M]
        [(null? (rest M)) M]
        [(null? (rest (rest M))) M]
        [else (cons (first M) (cons (second M) (rest (rest (rest M)))))]
        ))

;slide 29
(define (rember a L)
  (cond [(null? L) L]
        [(eq? (first L) a) (rest L)]
        [else (cons (first L) (rember a (rest L)))]
        ))

;slide 30
(define (nth n L)
  (if (= n 1)
      (first L)
      (nth (sub1 n) (rest L))))

(define (sumL1L2 L1 L2)
  (cond [(null? L1) L2]
        [(null? L2) L1]
        [else (cons (+ (first L1) (first L2)) (sumL1L2 (rest L1) (rest L2)))]
  )
)

(define (replaceb L a)
  (cond [(null? L) '()]
        [(eq? 'b (first L)) (cons a (rest L))]
        [else (cons (first L) (replaceb (rest L) a))]
        )
  )

;slide 32
(define (nonumber? L)
  (cond [(null? L) #t]
        [(number? (first L)) #f]
        [else (nonumber? (rest L))]))

(define (substitute3to1 L)
  (cond [(null? L) '()]
        [(eq? (first L) 3) (cons 1 (rest L))]
        [else (cons (first L) (substitute3to1 (rest L)))]))

;slide 33
(define (member? a M)
  (cond [(null? M) #f]
        [(pair? (first M)) 
         (if (member? a (first M)) #t
             (member? a (rest M)))]
        [else (if (eq? a (first M)) #t
                  (member? a (rest M)))]
        )
  )

(define (sum M)
  (cond [(null? M) 0]
        [(pair? (first M)) (+ (sum (first M)) (sum (rest M)))]         
        [else (+ (first M) (sum (rest M)))]
        )
  )

;slide 34
(define (removeall a M)
  (cond [(null? M) '()]
        [(pair? (first M)) (cons (removeall a (first M)) (removeall a (rest M)))]
        [(eq? a (first M)) (removeall a (rest M))]
        [else (cons (first M) (removeall a (rest M)))]
        )
  )

(define (suball a M)
  (cond [(null? M) '()]
        [(pair? (first M)) (cons (suball a (first M)) (suball a (rest M)))]
        [(eq? 'b (first M)) (cons a (suball a (rest M)))]
        [else (cons (first M) (suball a (rest M)))]
        )
  )
