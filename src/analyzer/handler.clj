(ns analyzer.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.tools.logging :as logger]
            [org.analyzer.config.config-manager :as config]))

(defn say-hello [name]
  (logger/info (str "begin of say-hello function with :name, " name))
  (logger/info (str "end of say-hello function with :name, " name))
  (str (config/get-property "hello.world") name)
)

(defroutes app-routes
  (GET "/" [name] (say-hello name))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
