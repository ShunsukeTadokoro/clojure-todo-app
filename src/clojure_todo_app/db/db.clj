(ns clojure-todo-app.db.db
  (:require [clojure.java.jdbc :as jdbc]))

(def db-spec
  {:dbtype   "postgresql"
   :dbname   "clojure_todo_app_dev"
   :host     "localhost"
   :port     5432
   :user     "postgres"
   :password ""})

(defn migrate []
  (jdbc/db-do-commands db-spec (jdbc/create-table-ddl :todo [:id :serial] [:title :varchar])))
