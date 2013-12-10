(ns cljsapp.core
  (:require [dommy.core :as dommy])
  (:require-macros [dommy.macros :refer [deftemplate sel sel1 node]]))

;; For (println ..) etc. to behave like (.log js/console ..)
(enable-console-print!)

(def messages (atom ["Hi there"]))

;; TODO retrieve message from textfield and add to atom
(defn add-message! []
  )

;; TODO render vector of messages
(deftemplate render-messages [messages]
  )

(defn replace-messages-list! [messages-el]
  (dommy/replace! (sel1 :#messages) messages-el))

(defn onload []
  ;; BOUNTY currently the whole list is rerendered when a message is added.
  ;; remove this atom-watch and implement direct addition of a message to
  ;; the dom and the atom.
  (add-watch messages :render
             (fn [key ref old-val new-val]
               (replace-messages-list! (render-messages new-val))))
  (dommy/replace-contents!
   (sel1 :body)
   (node [:div {:id "container"}
          [:input {:id "txt-message" :placeholder "message text" :type "text"}]
          [:input {:id "btn-add" :value "add message" :type "button"}]
          [:ul {:id "messages"}]]))
  (dommy/listen! (sel1 :#btn-add) :click add-message!)
  (replace-messages-list! (render-messages @messages)))

(set! (.-onload js/window) onload)
