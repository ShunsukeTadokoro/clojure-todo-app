(ns clojure-todo-app.handler.main
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [clojure-todo-app.util.response :as res]
            [clojure-todo-app.view.main :as view]))

(defn home [req]
  (-> (view/home-view req)
      res/response
      res/html))

(defroutes main-routes
           (GET "/" _ home)
           (route/not-found "<h1>404 Not Found</h1>"))

