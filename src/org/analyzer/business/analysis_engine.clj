(ns org.analyzer.business.analysis-engine
  (:require [org.analyzer.db.db-io :as dao]))

; business method used to compare two 
(defn compare-results [result1 result2]
  (if (> (:y result1) (:y result2)) ([1 (:x result1)]) ([2 (:x result2)])))

; business method used to compare 
(defn compare-reports [report-id1 report-id2]
  (map compare-results (dao/get-report-results report-id1) (dao/get-report-results report-id2)))

