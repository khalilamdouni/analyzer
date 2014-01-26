(ns org.analyzer.db.db-io
  (:require [clojure.java.jdbc :as sql]))

; define the db object 
(def db {:classname "com.mysql.jdbc.Driver"
          :subprotocol "mysql"
          :subname "//localhost:3306/calculator"
          :user "root"
          :password "root"})

; Dao method used to get all reports objects
(defn get-reports []
   (sql/with-connection db 
    (sql/with-query-results rs ["select * from REPORTING_REPORTS"]  
      (doall rs))))

; Dao method used to get report info by report id
(defn get-report [id]
   (sql/with-connection db 
    (sql/with-query-results rs ["select * from REPORTING_REPORTS where ID=?" id]  
      (apply str rs))))