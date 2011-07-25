(ns webnoir.server
  (:require [noir.server :as server]
            [webnoir.models.tutorial :as tut]))

(server/load-views "src/webnoir/views/")

(defn -main [& m]
  (let [mode (or (first m) :dev)]
    (tut/compile-all)
    (server/start 8005 {:mode (keyword mode)
                        :ns 'webnoir})))

