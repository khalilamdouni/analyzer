(ns org.analyzer.db.db-io
  (:require [clojure.java.jdbc :as sql]
            [clojure.tools.logging :as logger]))

; define the db object 
(def db {:classname "com.mysql.jdbc.Driver"
          :subprotocol "mysql"
          :subname "//localhost:3306/calculator"
          :user "root"
          :password "root"})

; Dao method used to get all reports objects
(defn get-reports []
   (logger/info "-------------CALL::get-reports------------ ")
   (sql/with-connection db 
    (sql/with-query-results rs ["select * from REPORTING_REPORTS"]  
      (doall rs))))

; Dao method used to get report info by report id
(defn get-report [id]
   (logger/info "-------------CALL::get-report------------ ")
   (sql/with-connection db 
    (sql/with-query-results rs ["select * from REPORTING_REPORTS where ID=?" id]  
      (doall rs))))

(defn get-report-results [id-report]
  (logger/info "-------------CALL::get-report-results------------ ")
  (sql/with-connection db (sql/with-query-results rs ["select * from calculator.REPORTING_RESULTS WHERE REPORT_ID=?" id-report] 
                            (doall rs))))