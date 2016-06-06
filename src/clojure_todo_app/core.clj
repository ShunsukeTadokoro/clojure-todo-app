(ns clojure-todo-app.core
  (:require [compojure.core :refer [routes]]
            [ring.adapter.jetty :as server]
            [clojure-todo-app.handler.main :refer [main-routes]]
            [clojure-todo-app.handler.todo :refer [todo-routes]]
            [clojure-todo-app.middleware :refer [middleware-set]]))

(defn- wrap [handler middleware opt]
  (if (true? opt)
    (middleware handler)
    (if opt
      (middleware handler opt)
      handler)))

(defonce server (atom nil))

(def app
  (middleware-set
  (routes
    todo-routes
    main-routes)))

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
