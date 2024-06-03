(ns hello.core
  (:gen-class)
  (:require
   [org.httpkit.server :as server]
   [pg.core :as pg]
   [pg.pool :as pool]))


(defmacro rand-id []
  `(rand-int 9999))

(def pool-spec
  {:host "127.0.0.1" #_"tfb-database"
   :port 15432 #_5432
   :user "benchmarkdbuser"
   :password "benchmarkdbpass"
   :database "hello_world"})

(def POOL
  (delay
    (pool/pool pool-spec)))


(defn handler-db [request]
  (let [result
        (pool/with-connection [conn @POOL]
          (pg/execute conn "select id, randomnumber from WORLD where id = $1" {:params [(rand-id)]}))]
    {:status 200
     :body (str result)}))


(defn handler-not-found [request]
  {:status 400
   :body "bbb"})





(defn handler [request]

  #_
  (println request)

  (case (:uri request)

    "/db"
    (handler-db request)

    ;; else
    (handler-not-found request)))


(defn -main [& _]
  (let [server (server/run-server handler {:port 8080})]
    ))
