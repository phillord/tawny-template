(ns {{namespace}}-test
    (:use [clojure.test])
    (:require
     [{{namespace}} :as ont]
     [tawny.owl :as o]
     [tawny.reasoner :as r]
     [tawny.fixture :as f]))

(use-fixtures :each (f/reasoner :hermit))

(deftest reasonable
  (is (r/consistent? {{namespace}}/{{name}}))
  (is (r/coherent? {{namespace}}/{{name}})))
