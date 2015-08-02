(ns mud2net.core
  (:use ring.adapter.jetty hiccup.core mud2net.mudtime)
  (:gen-class))

(defn page
  []
  (let [data   (reset-data)
        number (:number data)
        update (:update data)
        time   (.toEpochSecond (:time data))]
    [:html
     [:head
      [:script {:src "http://code.jquery.com/jquery-2.1.4.js"}]
      [:script (str "start_time = " (* 1000 time) ";" "\n"
                    "reset_number = " number ";\n"
                    "update_time = " update ";\n")]
      [:script
       "$(document).ready(function() {
        $('.time').each(function(k,tag) {
          $(tag).append(new Date(start_time).toString());
          start_time += (107 * 60 + 5)* 1000;
        });
        $('.number').each(function(k,tag) {
          $(tag).append(reset_number);
          reset_number += 1;
        });
       });"]]
     [:body
      [:table
       [:thead
        [:tr
         [:th "Reset number"]
         [:th "Starts at"]
        ]
       ]
       [:tbody
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        [:tr
         [:td {:class "number"}]
         [:td {:class "time"}]]
        ]
       ]
      ]
     ]))

(defn app [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (html (page))})

(def server (run-jetty app {:port 3000}))
