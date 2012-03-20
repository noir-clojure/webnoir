(ns webnoir.views.tutorials
  (:use noir.core
        hiccup.page-helpers)
  (:require [webnoir.views.common :as common]
            [webnoir.models.tutorial :as tut]))

(def tutorials [
                ;;{:id "overview" :title "Noir and the web" :desc "Let's lay out the conceptual foundation necessary for building websites in Clojure."}
                {:id "routes" :title "Defining pages" :desc "An in depth look at everything you can do with routes and how you define pages in Noir."}
                {:id "html" :title "Generating HTML" :desc "Your crash course on using Hiccup and partials to generate HTML, as well as a note on alternative templating libraries."}
                {:id "forms" :title "Forms and input" :desc "Websites wouldn't be very interesting if they didn't allow for interaction. This is your primer on getting and validating input."}
                {:id "sessions" :title "Sessions and cookies" :desc "It's time to talk about how we manage state in Noir through sessions and cookies."}
                {:id "middleware" :title "Middleware" :desc "Sometimes you need to mess around with the request and response maps. Middleware is how you do it."}
                {:id "others" :title "Using Noir with ..." :desc "This is a look into how you can use Noir with other tools such as lein-ring and lein-beanstalk."}
                ])
(def examples [{:url "https://github.com/ibdknox/Noir-blog" :title "Noir Blog" :desc "A completely functional blog written from the ground up in Noir"}
               {:url "http://thecomputersarewinning.com/post/clojure-heroku-noir-mongo" :title "Noir on Heroku" :desc "A simple example of getting Noir up on Heroku with a connection to MongoDB as well - by Ignacio Thayer."}])

(def docs [{:version "1.3.0" :title "Noir 1.3.0-beta1"}
           {:version "1.2.1" :title "Noir 1.2.1"}
           {:version "1.2.0" :title "Noir 1.2.0"}
           {:version "1.1.0" :title "Noir 1.1.0"}
           {:version "1.0.0" :title "Noir 1.0.0"}])

(defpartial tutorial-item [{:keys [id title desc]}]
            [:li
             (link-to (str "/tutorials/" id) title)
             [:p desc]])

(defpartial tutorial-list []
            [:ul
             (map tutorial-item tutorials)])

(defpartial example-item [{:keys [url title desc]}]
            [:li
             (link-to url title)
             [:p desc]])

(defpartial example-list []
            [:ul
             (map example-item examples)])

(defpartial doc-item [{:keys [version title]}]
             [:li (link-to (str "/autodoc/" version "/index.html") title)])

(defpartial docs-list []
            [:ul
             (map doc-item docs)
             [:li (link-to "https://github.com/ibdknox/noir/blob/master/history.md" "Change History")]])

(defpage "/docs*" []
         (common/layout
           (common/header)
           [:div#tutorials
            [:h3 "Which version?"]
            (docs-list)
            ]))

(defpage "/tutorials" []
         (common/layout
           (common/header)
           [:div#tutorials
            [:h3 "Concepts"]
            (tutorial-list)
            [:h3 "Examples"]
            (example-list)
            ]))

(defpage "/tutorials/:id" {:keys [id]}
         (common/layout
           (common/header)
           [:div#tutorialContent
             (tut/get-tutorial id)]))
