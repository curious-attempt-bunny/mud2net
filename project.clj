(defproject mud2net "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [ring "1.4.0"]
                 [hiccup "1.0.5"]]
  :resource-paths ["lib/jta26.jar", "lib/newrelic.jar","resources"]
  :jvm-opts ["-javaagent:lib/newrelic.jar"]
  :main ^:skip-aot mud2net.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
