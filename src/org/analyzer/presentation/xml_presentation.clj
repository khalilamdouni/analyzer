(ns org.analyzer.presentation.xml-presentation
  (:require [org.analyzer.db.db-io :as dao]
            [clojure.xml :as xml]))

; utility method used to transform report record to xml element
(defn report-to-xml [report-element]
  (xml/emit-element {:tag :report :attrs nil :content [
          {:tag :id :attrs nil :content [(str (:id report-element))]}
          {:tag :title :attrs nil :content [(str (:title report-element))]}   
          ]})
)

; Presentation method used to get reports formatted in xml
(defn get-reports-xml [] 
  (str "<reports>" (with-out-str (apply str (map report-to-xml (dao/get-reports)))) "</reports>")
)

; Presentation method used to get report formatted in xml
(defn get-report-xml [id] 
 (with-out-str (apply str (map report-to-xml (dao/get-report id))))
)
