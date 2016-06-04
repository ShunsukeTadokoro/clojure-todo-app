(ns clojure-todo-app.view.todo
  (:require [hiccup.core :as hc]
            [hiccup.form :as hf]
            [clojure-todo-app.view.layout :as layout]))

(defn todo-index-view [req todo-list]
  (->> [:section.card
        (when-let [{:keys [msg]} (:flash req)]
          [:div.alert.alert-success [:strong msg]])
        [:h2 "TODO一覧"]
        [:a {:href "/todo/new"} "追加"]
        [:ul
         (for [{:keys [id title]} todo-list]
           [:li [:a {:href (str "/todo/" id)} title]])]]
       (layout/common req)))

(defn todo-new-view [req]
  (->> [:section.card
        [:h2 "TODO追加"]
        (hf/form-to
          [:post "/todo/new"]
          [:input {:name "title" :placeholder "タイトル"}]
          [:button.bg-blue "追加"])]
       (layout/common req)))

(defn todo-show-view [req todo]
  (let [todo-id (:id todo)]
    (->> [:section.card
          (when-let [{:keys [msg]} (:flash req)]
            [:div.alert.alert-success msg])
          [:h2 (:title todo)]
          [:a.wide-link {:href (str "/todo/" todo-id "/edit")} "更新"]
          [:a.wide-link {:href (str "/todo/" todo-id "/delete")} "削除"]
          [:a.wide-link {:href "/todo"} "一覧"]]
         (layout/common req))))

(defn todo-edit-view [req todo]
  (let [todo-id (get-in req [:params :todo-id])]
    (->> [:section.card
          [:h2 "TODO編集"]
          (hf/form-to
            [:post (str "/todo/" todo-id "/edit")]
            [:input {:name :title :value (:title todo) :placeholder "TODO"}]
            [:button.bg-blue "更新"])]
         (layout/common req))))

(defn todo-delete-view [req todo]
  (let [todo-id (get-in req [:params :todo-id])]
    (->> [:section.card
          [:h2 "TODO削除"]
          (hf/form-to
            [:post (str "/todo/" todo-id "/delete")]
            [:p "次のTODOを削除しますか？"]
            [:p "*" (:title todo)]
            [:button.bg-red "削除"])]
         (layout/common req))))