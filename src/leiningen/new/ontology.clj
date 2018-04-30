(ns leiningen.new.ontology
  (:require
   [clojure.string]
   [leiningen.new.templates :refer [renderer year date project-name
                                    ->files sanitize-ns name-to-path
                                    multi-segment
                                    ]]
   [leiningen.core.main :as main]))

(def render (renderer "ontology"))

(defn ontology
  "Generate a new project using Tawny-OWL."
  [name]
  (let [
        split-name (clojure.string/split name #"[.]|-")
        base (if (= 1 (count split-name))
               (first split-name)
               (clojure.string/join "."
                (butlast split-name)))
        onto (if (= 1 (count split-name))
               (first split-name)
               (last split-name))
        core-ns (str (multi-segment (sanitize-ns base)))
        main-ns (str (sanitize-ns base) "."  onto)
        base-dir-path (name-to-path base)
        data {:base base
              :split-name split-name
              :name name
              :namespace-main core-ns
              :namespace main-ns
              :sanitized base-dir-path
              :onto-name onto
              }]
    (main/info "Generating fresh 'lein new' ontology project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/{{onto-name}}.clj"
              (render "onto.clj" data)]
             ["test/{{sanitized}}/{{onto-name}}_test.clj"
              (render "onto_test.clj" data)])))
