(ns hello.core
  (:gen-class)
  (:require
   [org.httpkit.server :as server]
   [pg.core :as pg]
   [pg.json :as json]
   [pg.pool :as pool]))


(defmacro rand-id []
  `(rand-int 9999))

(def pool-common
  {:user "benchmarkdbuser"
   :password "benchmarkdbpass"
   :database "hello_world"
   :pool-min-size 256
   :pool-max-size 1024})

(def pool-local
  (merge pool-common
         {:host "127.0.0.1"
          :port 15432}))

(def pool-docker
  (merge pool-common
         {:host "tfb-database"
          :port 5432}))

(def pool-spec
  pool-docker
  ;; pool-local
  )

(def POOL
  (delay
    (pool/pool pool-spec)))


(defn handler-db [request]
  (let [row
        (pool/with-connection [conn @POOL]
          (pg/execute conn "select id, randomnumber from WORLD where id = $1"
                      {:params [(rand-id)]
                       :first? true}))]
    {:status 200
     :headers {"content-type" "application/json"}
     :body (json/write-string row)}))


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
