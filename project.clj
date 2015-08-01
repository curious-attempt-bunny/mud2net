(defproject mud2net "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.4.0"]]
  :resource-paths ["lib/jta26.jar"]
  :main ^:skip-aot mud2net.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
