(ns clojure.lang.hash
  (:refer-clojure :only [defn extend-type fn])
  (:require [clojure.lang.platform.hash]
            [clojure.lang.protocols :refer [IHash]])
  (:import [clojure.lang Util]))

(defn hash-combine [hash1 hash2]
  (Util/hashCombine hash1 hash2))

(extend-type nil
  IHash
  (-hash [this] 0))
