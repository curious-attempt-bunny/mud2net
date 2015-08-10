(ns mud2net.mudtime)

(def reset-atom (atom nil))

(defn reset-data
  []
  @reset-atom)

(defn fetch-reset-data
  []
  (let [telnet (de.mud.telnet.TelnetWrapper.)]
     (future
       (Thread/sleep 60000)
       (.disconnect telnet))
     (.connect telnet "mudii.co.uk" 23)
     (.waitfor telnet "login:")
     (.send telnet "mudguest")
     (.waitfor telnet "Hit return.")
     (.send telnet "")
     (.waitfor telnet "Option (H for help):")
     (.send telnet "p")
     (let [reset-info (.waitfor telnet "By what name shall I call you")]
       (.send telnet "q")
       (.waitfor telnet "Option (H for help):")
       (.send telnet "q")
       (.disconnect telnet)

       (let [[_ date time] (re-find (re-matcher #"MUD last reset on (.*) at (.*)\." reset-info))
             pos  (.indexOf date "-")
             date (str (.substring date 0 (+ pos 2))
                       (.substring (.toLowerCase date) (+ pos 2)))
             reset-time (java.time.ZonedDateTime/parse (str date " " time " Europe/London") (java.time.format.DateTimeFormatter/ofPattern "d-MMM-yyyy HH:mm:ss VV"))
             reset-number (Integer/parseInt (last (re-find (re-matcher #"This reset is number (\d+)\." reset-info))))]
         {:number reset-number :time reset-time :update (System/currentTimeMillis)}))))

(def pool (java.util.concurrent.ScheduledThreadPoolExecutor. 1))

(.scheduleAtFixedRate
  pool
  (fn []
    (try
      (let [data (fetch-reset-data)]
        (println data)
        (reset! reset-atom data))
      (catch Exception e
        (prn e)
        @reset-atom)))
  0
  10
  (java.util.concurrent.TimeUnit/MINUTES))