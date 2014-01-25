(ns org.analyzer.db.db-io
  (:require [clojure.java.jdbc :as sql]))

(def db {:classname "com.mysql.jdbc.Driver"
          :subprotocol "mysql"
          :subname "//localhost:3306/calculator"
          :user "root"
          :password "root"})

(defn get-report [id]
   (sql/with-connection db 
    (sql/with-query-results rs ["select * from REPORTING_REPORTS where ID=?" id]  
      (apply str rs))))