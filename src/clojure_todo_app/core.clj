(ns clojure-todo-app.core
  (:require [compojure.core :refer [routes]]
            [ring.adapter.jetty :as server]
            [ring.middleware.resource :as resource]
            [clojure-todo-app.handler.main :refer [main-routes]]
            [clojure-todo-app.handler.todo :refer [todo-routes]]
            [clojure-todo-app.middleware :refer [wrap-dev]]
            [environ.core :refer [env]]))

(defn- wrap [handler middleware opt]
  (if (true? opt)
    (middleware handler)
    (if opt
      (middleware handler opt)
      handler)))

(defonce server (atom nil))

(def app
  (-> (routes
        todo-routes
        main-routes)
      (wrap wrap-dev (:dev env))
      (wrap resource/wrap-resource "public")))

(defn start-server []
  (when-not @server
    (reset! server (server/run-jetty #'app {:port 3000 :join? false}))))

(defn stop-server []
  (when @server
    (.stop @server)
    (reset! server nil)))

(defn restart-server []
  (when @server
    (stop-server)
    (start-server)))
