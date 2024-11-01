(defproject {{name}} "0.0.1-SNAPSHOT"
  :description "An ontology for {{name}}"
  :dependencies [[uk.org.russet/tawny-owl "2.3.3"]]
  :main {{namespace-main}}

  :profiles
  {:light {:plugins [[nightlight/lein-nightlight "2.4.4"]]}}
  )
