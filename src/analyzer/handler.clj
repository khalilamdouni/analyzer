(ns analyzer.handler
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojure.tools.logging :as logger]
            [org.analyzer.config.config-manager :as config]
            [org.analyzer.db.db-io :as dao]
            [org.analyzer.presentation.xml-presentation :as presentation]))

; Function returning the welconme word from the properties file
(defn welcome []
  (str name (config/get-property "welcome.word"))
)

; The central rooter of the application
(defroutes app-routes
  (GET "/" [] (welcome))
  (GET "/report" [id] {:status 200
                      :headers {"Content-Type" "text/xml"}
                      :body (presentation/get-report-xml id)})
  (GET "/reports" [] {:status 200
                      :headers {"Content-Type" "text/xml"}
                      :body (presentation/get-reports-xml)})
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
