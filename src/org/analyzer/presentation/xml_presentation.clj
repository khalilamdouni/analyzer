(ns org.analyzer.presentation.xml-presentation
  (:require [org.analyzer.db.db-io :as dao]
            [clojure.xml :as xml]
            [org.analyzer.business.analysis-engine :as business]))

; utility method used to transform report record to xml element
(defn report-to-xml [report-element]
  (xml/emit-element {:tag :report :attrs nil :content [
          {:tag :id :attrs nil :content [(str (:id report-element))]}
          {:tag :title :attrs nil :content [(str (:title report-element))]}   
          ]}))

; Presentation method used to get reports formatted in xml
(defn get-reports-xml [] 
  (str "<reports>" (with-out-str (apply str (map report-to-xml (dao/get-reports)))) "</reports>"))

; Presentation method used to get report formatted in xml
(defn get-report-xml [id] 
 (with-out-str (apply str (map report-to-xml (dao/get-report id)))))


; Utility method used to transform an analysis point to xml
(defn analysis-point-to-xml [point]
  (xml/emit-element {:tag :point :attrs nil :content [
          {:tag :report :attrs nil :content [(str (point 0))]}
          {:tag :x :attrs nil :content [(str (point 1))]}   
          ]}))

; Presentation method used to get analysis results in xml format
(defn analysis-points [report-id1 report-id2]
  (str "<analysis>" (with-out-str (apply str (map analysis-point-to-xml (business/compare-reports report-id1 report-id2)))) "</analysis>"))