(ns clojure.lang.platform.hash
  (:refer-clojure :only [extend-protocol fn defn list update-in cons])
  (:require [clojure.lang.protocols :refer [IHash]]))

(defn platform-hash-method [methods init-macro]
  (update-in methods
             ['Object]
             (fn [old]
               (cons
                 (list 'hashCode ['this]
                       (list init-macro 'this))
                 old))))

(extend-protocol IHash
  Object
  (-hash [this]
    (.hashCode this))

  )
