(ns clojure-todo-app.handler.todo
  (:require [compojure.core :refer [defroutes context GET POST]]
            [clojure-todo-app.util.response :as res]
            [clojure-todo-app.view.todo :as view]
            [clojure-todo-app.db.todo :as todo]))

(defn todo-index [req]
  (let [todo-list (todo/find-todo-all)]
    (-> (view/todo-index-view req todo-list)
         res/response
         res/html)))




(defn todo-new [req] "TODO new")
(defn todo-new-post [req] "TODO new post")
(defn todo-search [req] "TODO search")
(defn todo-show [req] "TODO show")
(defn todo-edit [req] "TODO edit")
(defn todo-edit-post [req] "TODO edit post")
(defn todo-delete [req] "TODO delete")
(defn todo-delete-post [req] "TODO delete post")

(defroutes todo-routes
  (context "/todo" _
    (GET "/" _ todo-index)
    (GET "/new" _ todo-new)
    (POST "/new" _ todo-new-post)
    (GET "/search" _ todo-search)
    (context "/:todo-id"_
      (GET "/" _ todo-show)
      (GET "/edit" _ todo-edit)
      (POST "/edit" _ todo-edit-post)
      (GET "/delete" _ todo-delete)
      (POST "/delete" _ todo-delete-post))))