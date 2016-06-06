(ns clojure-todo-app.util.response
  (:require [potemkin :as p]
            [ring.util.http-response :as http-res]))

(defmacro import-ns [ns-sym]
  (do
    `(p/import-vars
       [~ns-sym
        ~@(map first (ns-publics ns-sym))])))

(import-ns ring.util.http-response)

(defn html [res]
  (http-res/content-type res "text/html; charset=utf-8"))