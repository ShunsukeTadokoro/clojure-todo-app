(ns clojure-todo-app.view.main
  (:require [clojure-todo-app.view.layout :as layout]))

(defn home-view [req]
  (->> [:section.card
        [:h2 "ホーム"]
        [:a {:href "/todo"} "TODO一覧"]]
       (layout/common req)))
