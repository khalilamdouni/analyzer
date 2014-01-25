(ns analyzer.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.tools.logging :as logger]))

(defn say-hello [name]
  (logger/info (str "begin of say-hello function with :name, " name))
  (logger/info (str "end of say-hello function with :name, " name))
  (str "Hello, " name)
)

(defroutes app-routes
  (GET "/" [name] (say-hello name))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
