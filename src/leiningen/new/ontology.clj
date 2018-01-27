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
        core-ns (multi-segment (sanitize-ns name))
        main-ns (multi-segment (sanitize-ns name) name)
        base-dir-path (name-to-path name)
        onto-name
        (last (clojure.string/split base-dir-path #"/"))
        data {:name name
              :namespace-main core-ns
              :namespace main-ns
              :sanitized base-dir-path
              :onto-name onto-name
              }]
    (main/info "Generating fresh 'lein new' ontology project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/{{onto-name}}.clj"
              (render "onto.clj" data)]
             ["test/{{sanitized}}/{{onto-name}}_test.clj"
              (render "onto_test.clj" data)])))
