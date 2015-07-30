(ns mud2net.core
  (:use ring.adapter.jetty)
  (:gen-class))

(defn handler [request]
      {:status  200
       :headers {"Content-Type" "text/html"}
       :body    "Hallo leute!"})

(defn -main
  []
  (run-jetty handler {:port 3000}))
