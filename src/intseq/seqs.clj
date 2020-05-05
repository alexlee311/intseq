(ns intseq.seqs)

;; ------------------------------------------------------------------------------------------------------------------------------------
;; HELPER FUNCTIONS
;; ------------------------------------------------------------------------------------------------------------------------------------

(defn split-sequence [full-seq]
  "Given a sequence, splits it up evenly into a training sequence
  and a testing sequence"
  (let [midpoint (quot (count full-seq) 2)
        training-seq (take midpoint full-seq)
        testing-seq (drop midpoint full-seq)]
    {:training training-seq
     :testing testing-seq}))

(defn check [func seq]
  "Given (1) a function generated by our evolutionary process based on
  some training terms of a sequence, and (2) the sequence we were using,
  checks if the function generates the same new terms as the testing terms"
  (let [testing-seq (:testing (split-sequence seq))
        first-index (first (first testing-seq)) ;; first index of testing sequence
        end-index (first (last testing-seq)) ;; last index of testing sequence
        new-terms (for [index (range first-index (+ end-index 1) 1)]
                    [index (func index)])]
    (= new-terms testing-seq)))

;; ------------------------------------------------------------------------------------------------------------------------------------
;; TEST SEQUENCES
;; ------------------------------------------------------------------------------------------------------------------------------------

;; A simple sequence
(def simple
  (for [x (range 0 10 1)]
    [x (+ (* x x) (+ 2 x) 2)]))

;; Mathematica: Table[n^2*((n^2 + 1)/2), {n, 0, 19}]
;;  => [0, 1, 10, 45, 136, 325, 666, 1225, 2080, 3321, 5050, 7381, 10440, 14365, 19306, 25425, 32896, 41905, 52650, 65341]
(def A037270
  (let [seq [0 1 10 45 136 325 666 1225 2080 3321 5050 7381
             10440 14365 19306 25425 32896 41905 52650 65341]
        index (range (count seq))]
    (map #(vec [%1 %2]) index seq)))

;; Mathematica: Table[Binomial[n + 2, 3], {n, 0, 19}]
;;  => [0, 1, 4, 10, 20, 35, 56, 84, 120, 165, 220, 286, 364, 455, 560, 680, 816, 969, 1140, 1330]
(def A000292
  (let [seq [0 1 4 10 20 35 56 84 120 165 220 286 364 455 560 680 816 969 1140 1330]
        index (range (count seq))]
    (map #(vec [%1 %2]) index seq)))

;; Mathematica: Table[Binomial[n + 3, 3]*(11*n^2 + 29*n + 20)/20, {n, 0, 19}]
;;  => [1, 12, 61, 206, 546, 1232, 2478, 4572, 7887, 12892, 20163, 30394, 44408, 63168, 87788, 119544, 159885, 210444, 273049, 349734]
(def A114241
  (let [seq [1 12 61 206 546 1232 2478 4572 7887 12892 20163 30394 44408 63168 87788 119544 159885 210444 273049 349734]
        index (range (count seq))]
    (map #(vec [%1 %2]) index seq)))

;; Mathematica: Table[5 + 8 Floor[(n - 1)/2], {n, 20}]
;;  => [5, 5, 13, 13, 21, 21, 29, 29, 37, 37, 45, 45, 53, 53, 61, 61, 69, 69, 77, 77]
(def A168392
  (let [seq [5 5 13 13 21 21 29 29 37 37 45 45 53 53 61 61 69 69 77 77]
        index (range (count seq))]
    (map #(vec [%1 %2]) index seq)))

;; ------------------------------------------------------------------------------------------------------------------------------------
;; MATHEMATICALLY SIGNIFICANT SEQUENCES
;; ------------------------------------------------------------------------------------------------------------------------------------

;; Mathematica: f[s_List] := Block[{a = s[[-1]], len = Length@s}, Append[s, If[a > len && ! MemberQ[s, a - len], a - len, a + len]]]; Nest[f, {0}, 19]
;;  => [0, 1, 3, 6, 2, 7, 13, 20, 12, 21, 11, 22, 10, 23, 9, 24, 8, 25, 43, 62]
(def A005132
  (let [seq [0 1 3 6 2 7 13 20 12 21 11 22 10 23 9 24 8 25 43 62]
        index (range (count seq))]
    (map #(vec [%1 %2]) index seq)))

