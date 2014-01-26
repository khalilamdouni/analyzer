(ns org.analyzer.presentation.xml-presentation
  (:require [org.analyzer.db.db-io :as dao]
            [clojure.xml :as xml]))

; utility method used to transform report record to xml element
(defn report-to-xml [report-element]
  (xml/emit-element {:tag :report :attrs nil :content [
                                                       {:tag :id :attrs nil :content ["id"]}
                                                       {:tag :title :attrs nil :content ["title"]}   
                                                       ]}))

; Presentation method used to get reports formatted in xml
(defn get-reports-xml [] 
  (str (clojure.string/join "" (map report-to-xml (dao/get-reports)))) )

