(ns {{namespace-main}}
  [:use [tawny.owl]]
  [:require [{{namespace}}]])


(defn -main [& args]
  (save-ontology {{namespace}}/{{name}} "{{name}}.omn"))
