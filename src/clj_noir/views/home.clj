(ns clj-noir.views.home
  (:require [clj-noir.views.common :as common])
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(def header-links [{:url "/#started" :text "Get Started"}
                   {:url "/tutorials" :text "Tutorials"}
                   {:url "http://groups.google.com/group/clj-noir" :text "Google Group"}
                   {:url "/docs/index.html" :text "API"}
                   {:url "https://github.com/ibdknox/noir" :text "Src"}])

(def links {:api (link-to "/docs/index.html" "API")
            :tutorial (link-to "/tutorials" "tutorials")
            :blog-project (link-to "https://github.com/ibdknox/Noir-blog" "blog project")
            :clojure (link-to "http://clojure.org" "Clojure")
            :lein-noir (link-to "https://github.com/ibdknox/lein-noir" "lein-noir")
            :lein (link-to "https://github.com/technomancy/leiningen" "leiningen")
            :compojure (link-to "https://github.com/weavejester/compojure" "Compojure")
            :hiccup (link-to "https://github.com/weavejester/hiccup" "Hiccup")
            :ring (link-to "https://github.com/mmcgrana/ring" "Ring")})

(def gists {:welcome "<script src=\"https://gist.github.com/1038738.js\"> </script>"
            :step1 "<script src=\"https://gist.github.com/1038862.js\"> </script>"
            :step2 "<script src=\"https://gist.github.com/1038952.js\"> </script>"
            :step3 "<script src=\"https://gist.github.com/1039072.js\"> </script>"
            :step4 "<script src=\"https://gist.github.com/1039191.js\"> </script>"})

(defpartial link [{:keys [url text]}]
            (link-to url text))

(defpartial link-item [lnk]
            [:li
             (link lnk)])

(defpartial logo []
            (link-to "/" (image "/img/noir-logo.png" "Noir")))

(defpartial header []
            [:div#header 
             [:h1 (logo)]
             [:ul
              (map link-item header-links)]
             [:h2 "The Clojure web framework"]
             ])

(defpage "/" []
         (common/layout
           (header)
           [:ul.start
            [:li
             [:div.left (:welcome gists)]
             [:div.right
              [:p#desc "Noir is a micro-framework that allows you to rapidly develop websites in " (:clojure links) 
               ". Designed to ensure fast iteration and maintainability, it provides a very simple and intuitive 
               API to handle everything from defining pages and generating HTML to managing cookies and validating
               form input."]
              ]]
            ]
           [:h2#started (image "/img/started.png" "Getting Started")]
           [:ul.start
            [:li
             [:div.left (:step1 gists)]
             [:div.right
              [:p "First, make sure you have " (:lein links) " installed. Then we'll get the " (:lein-noir links) " plugin
                  and create a new noir project. Those four shell commands get your site up and running."]
              ]]

            [:li
             [:div.left
              [:p#step2 "Now let's make it do something. Noir uses " (:hiccup links) " to generate HTML. 
               It's pretty simple; html elements are represented by vectors where the first keyword
               is the name of the tag you want and the rest is the content. To define a function that returns 
               HTML, you can use the (defpartial) macro. Here's an example that generates a list of todos."]
              ]
             [:div.right (:step2 gists)]]

            [:li
             [:div.left (:step3 gists)]
             [:div.right
              [:p#step2 "So we've created some html functions, but we need to define some pages that use them.
                        Noir is built on top of " (:ring links) " and " (:compojure links) ", which take care of
                        handling HTTP requests and responses. By using the (defpage) macro we can define what happens 
                        when someone accesses a given url. You define the \"route\" and supply 
                        a destructuring form for the parameters of the request, which contain the GET/POST values 
                        as well as route parameters you define. The rest is simply the content of the response."]
              ]]

            [:li
             [:div.left
              [:p#step4 "Those are the basics of what Noir helps you with, but it also provides ways for you to interact
                  with sessions, cookies, validation and more. Take a look at the " (:tutorial links) " and the " 
                  (:api links) " documentation for more information. Now go build some websites!"]]
              [:div.right (:step4 gists)]
              ]
            
            ]

            ))

(defpage "/tutorials" []
         (common/layout
           (header)
           [:div#tutorials
            [:h3 "We're still compiling some top notch tutorials."]
            [:p "For now, check out the example " (:blog-project links) 
             ". It provides a full fledged example of a noir app."]]))
