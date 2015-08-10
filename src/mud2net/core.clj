(ns mud2net.core
  (:use ring.adapter.jetty ring.middleware.resource hiccup.core mud2net.mudtime)
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
        $('#last-update').text(\"Last update: \"+new Date(update_time));
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
      [:span {:id "last-update" :style "font-size: xx-small;"}]
      [:p
       [:a {:href "muddledtimes/site/index.html"} "Muddled Times"]]
      ]
     ]))

(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    (html (page))})

(def app
  (wrap-resource handler "public"))

(def server (run-jetty app {:port 3000}))

