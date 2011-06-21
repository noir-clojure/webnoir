(ns clj-noir.server
  (:require [noir.server :as server]))

(server/load-views "src/clj_noir/views/")

(defn -main [& m]
  (let [mode (or (first m) :dev)]
    (server/start 8005 {:mode (keyword mode)
                        :ns 'clj-noir})))

