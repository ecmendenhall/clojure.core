(ns clojure.lang.persistent-array-map-test
  (:refer-clojure :only [deftype nil? let])
  (:require [clojure.test                      :refer :all]
            [clojure.lang.persistent-map-test  :refer [map-test]]
            [clojure.lang.protocols            :refer [ISeqable ISequential]]
            [clojure.next                      :refer :all]))

(deftype FixedSequential [seq]
  ISequential
  ISeqable
  (-seq [this] seq))

(deftest array-map-test
  (map-test "PersistentArrayMap" array-map))

(deftest array-map-seq-test
  (testing "seq returns nil when the map is empty"
    (is (nil? (seq (array-map)))))

  (testing "first gives a map entry with the key and val"
    (let [m1 (array-map :k1 1)
          m1-seq (seq m1)
          entry (first m1-seq)]
      (is (= :k1 (key entry)))
      (is (= 1 (val entry)))))

  (testing "next returns nil when there is only one entry"
    (is (nil? (next (seq (array-map :k1 1))))))

  (testing "returns items in the order that they are constructed"
    (let [m1 (array-map :k1 1 :k2 2)
          m1-seq (seq m1)
          entry (first m1-seq)]
      (is (= :k1 (key entry)))
      (is (= 1 (val entry)))))

  (testing "next returns nil when there more than one entry"
    (let [m1 (array-map :k1 1 :k2 2)
          m1-seq (seq m1)
          m2-seq (next m1-seq)
          entry (first m2-seq)]
      (not (identical? m1-seq m2-seq))
      (is (= :k2 (key entry)))
      (is (= 2 (val entry)))))

  (testing "can keep calling next and eventually get to every item"
    (let [m1-seq (seq (array-map :k1 1 :k2 2 :k3 3 :k4 4 :k5 5))
          m2-seq (next m1-seq)
          m3-seq (next m2-seq)
          m4-seq (next m3-seq)
          m5-seq (next m4-seq)
          m6-seq (next m5-seq)]
      (is (= :k1 (key (first m1-seq))))
      (is (= 1   (val (first m1-seq))))
      (is (= :k2 (key (first m2-seq))))
      (is (= 2   (val (first m2-seq))))
      (is (= :k3 (key (first m3-seq))))
      (is (= 3   (val (first m3-seq))))
      (is (= :k4 (key (first m4-seq))))
      (is (= 4   (val (first m4-seq))))
      (is (= :k5 (key (first m5-seq))))
      (is (= 5   (val (first m5-seq))))
      (is (nil? m6-seq))))

  (testing "counts the items in a seq"
    (let [m1-seq (seq (array-map :k1 1 :k2 2 :k3 3 :k4 4 :k5 5))
          m2-seq (next m1-seq)
          m3-seq (next m2-seq)
          m4-seq (next m3-seq)
          m5-seq (next m4-seq)
          m6-seq (next m5-seq)]
      (is (= 5 (count m1-seq)))
      (is (= 4 (count m2-seq)))
      (is (= 3 (count m3-seq)))
      (is (= 2 (count m4-seq)))
      (is (= 1 (count m5-seq)))))

  (testing "array-map-seqs and Seqable and return themselves"
    (let [s (seq (array-map :k1 1 :k2 2 :k3 3))]
      (is (identical? s (seq s)))))

  (testing "seqs are equal when every seq entry is equal"
    (is (= (seq (array-map :k1 1 :k2 2 :k3 3))
           (seq (array-map :k1 1 :k2 2 :k3 3)))))

  (testing "seqs are not equal when one item is missing from other"
    (is (not= (seq (array-map :k1 1 :k2 2 :k3 3))
              (seq (array-map :k1 1 :k2 2)))))

  (testing "seqs are not equal when one item is missing from this"
    (is (not= (seq (array-map :k1 1 :k2 2))
              (seq (array-map :k1 1 :k2 2 :k3 3)))))

  (testing "seqs are not equal when other is not a sequential"
    (is (not= (seq (array-map :k1 1)) 1)))

  (testing "seqs are equal to sequential things"
    (let [s1 (seq (array-map :k1 1 :k2 2))
          s2 (seq (array-map :k1 1 :k2 2))]
      (is (= s1 (FixedSequential. s2)))))

  )
