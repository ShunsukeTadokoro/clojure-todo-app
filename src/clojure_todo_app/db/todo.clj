(ns clojure-todo-app.db.todo
  (:require [clojure.java.jdbc :as jdbc]
            [clojure-todo-app.db.db :as db]))

(defn save-todo [title]
  (jdbc/insert! db/db-spec :todo {:title title}))

(defn find-todo-all []
  (jdbc/query db/db-spec "select * from todo"))

