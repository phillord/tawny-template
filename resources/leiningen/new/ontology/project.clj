(defproject {{name}} "0.0.1-SNAPSHOT"
  :description "An ontology for {{name}}"
  :dependencies [[uk.org.russet/tawny-owl "2.0.0-SNAPSHOT"]]
  :main {{namespace-main}}

  :profiles
  {:light {:plugins [[nightlight/lein-nightlight "1.9.0"]]}}
  )
