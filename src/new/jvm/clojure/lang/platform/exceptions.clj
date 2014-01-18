(ns clojure.lang.platform.exceptions)

(def argument-error IllegalArgumentException)

(defmacro new-argument-error [& args]
  (list* 'new argument-error args))

(def illegal-state-error IllegalStateException)

(defmacro new-illegal-state-error [& args]
  (list* 'new illegal-state-error args))

(def unsupported-error UnsupportedOperationException)

(defmacro new-unsupported-error [& args]
  (list* 'new unsupported-error args))