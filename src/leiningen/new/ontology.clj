(ns leiningen.new.ontology
  (:require
   [leiningen.new.templates :refer [renderer year date project-name
                                    ->files sanitize-ns name-to-path
                                    multi-segment
                                    ]]
   [leiningen.core.main :as main]))

(def render (renderer "ontology"))

(defn ontology
  "FIXME: write documentation"
  [name]
  (println "name is" name)
  (let [
        core-ns (multi-segment (sanitize-ns name))
        main-ns (multi-segment (sanitize-ns name) name)
        data {:name name
              :namespace-main core-ns
              :namespace main-ns
              :sanitized (name-to-path name)
              }]
    (main/info "Generating fresh 'lein new' ontology project.")
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "core.clj" data)]
             ["src/{{sanitized}}/{{sanitized}}.clj"
              (render "onto.clj" data)]
             ["test/{{sanitized}}/{{sanitized}}_test.clj"
              (render "onto_test.clj" data)])))
