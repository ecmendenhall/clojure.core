(ns clojure.next-test
  (:require [clojure.test :refer :all]
            [clojure.next :refer :all]))

(deftest >-test
  (testing "returns true with one arg"
    (is (= true (> 1))))

  (testing "compares two numbers"
    (is (= true (> 1 0)))
    (is (= false (> 0 1)))
    (is (= false (> 1 1))))

  (testing "compares many numbers"
    (is (= true (> 2 1 0)))
    (is (= false (> 1 2 0)))
    (is (= false (> 1 1 1)))))

(deftest >=-test
  (testing "returns true with one arg"
    (is (= true (>= 1))))

  (testing "compares two numbers"
    (is (= true (>= 1 0)))
    (is (= false (>= 0 1)))
    (is (= true (>= 1 1))))

  (testing "compares many numbers"
    (is (= true (>= 2 1 0)))
    (is (= false (>= 1 2 0)))
    (is (= true (>= 1 1 1)))))

(deftest <-test
  (testing "returns true with one arg"
    (is (= true (< 1))))

  (testing "compares two numbers"
    (is (= true (< 0 1)))
    (is (= false (< 1 0)))
    (is (= false (< 1 1))))

  (testing "compares many numbers"
    (is (= true (< 0 1 2)))
    (is (= false (< 1 0 2)))
    (is (= false (< 1 1 1)))))

(deftest <=-test
  (testing "returns true with one arg"
    (is (= true (<= 1))))

  (testing "compares two numbers"
    (is (= true (<= 0 1)))
    (is (= false (<= 1 0)))
    (is (= true (<= 1 1))))

  (testing "compares many numbers"
    (is (= true (<= 0 1 2)))
    (is (= false (<= 1 0 2)))
    (is (= true (<= 1 1 1)))))

(deftest pos?-test
  (testing "returns true for positive numbers"
    (is (= true (pos? 1))))
  (testing "returns false for zero"
    (is (= false (pos? 0))))
  (testing "returns false for negative numbers"
    (is (= false (pos? -1)))))

(deftest neg?-test
  (testing "returns false for positive numbers"
    (is (= false (neg? 1))))
  (testing "returns false for zero"
    (is (= false (neg? 0))))
  (testing "returns true for negative numbers"
    (is (= true (neg? -1)))))

(deftest zero?-test
  (testing "returns false for positive numbers"
    (is (= false (zero? 1))))
  (testing "returns true for zero"
    (is (= true (zero? 0))))
  (testing "returns false for negative numbers"
    (is (= false (zero? -1)))))
