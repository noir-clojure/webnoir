(ns webnoir.views.home
  (:require [webnoir.views.common :as common])
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(def gists {:welcome "<script src=\"https://gist.github.com/1038738.js\"> </script>"
            :welcome2 "<script src=\"https://gist.github.com/1039687.js\"> </script>"
            :step1 "<script src=\"https://gist.github.com/1038862.js\"> </script>"
            :step2 "<script src=\"https://gist.github.com/1038952.js\"> </script>"
            :step3 "<script src=\"https://gist.github.com/1039072.js\"> </script>"
            :step4 "<script src=\"https://gist.github.com/1039191.js\"> </script>"})


(defpage "/" []
         (common/layout
           (common/header)
           [:ul.start
            [:li
             [:div.left (:welcome2 gists)]
             [:div.right
              [:p#desc "Noir is a micro-framework that allows you to rapidly develop websites in " (:clojure common/links) 
               "."]
               [:p "And it couldn't be any " [:em "simpler"] "."]
              ]]
            ]
           [:h2#started (image "/img/started.png" "Getting Started")]
           [:ul.start
            [:li
             [:div.left (:step1 gists)]
             [:div.right
              [:p "Make sure you have " (:lein common/links) " installed, that way we can use the " (:lein-noir common/links) " plugin
                  to create a new noir project. Four commands later, your site is up and running."]
              ]]

            [:li
             [:div.left
              [:p#step2 "Now let's make it do something. Noir uses " (:hiccup common/links) " to generate HTML. 
               Hiccup represents html elements as vectors where the first keyword
               is the name of the tag and everything else is the content. With Noir you can define
               functions that return HTML by using the (defpartial) macro. The code to the right, for example, 
               shows how you could generate an unordered list of todos."]
              ]
             [:div.right (:step2 gists)]]

            [:li
             [:div.left (:step3 gists)]
             [:div.right
              [:p#step2 "We've created some html functions, but we need to define some pages that use them.
                        Noir is built on top of " (:ring common/links) " and " (:compojure common/links) ", which take care of
                        handling HTTP requests and responses. The (defpage) macro extends these with a simple
                        way to define what happens when someone accesses a given url. 
                        You just pass it a \"route\" and supply a destructuring form for the parameters of the request 
                        (basically, the GET or POST values). The rest is then evaluated as the content of 
                        the response."]
              ]]

            [:li
             [:div.left
              [:p#step4 "This shows you the basics of Noir, but it provides much more than what you see here. 
                        Eveything you need to build production ready websites is included: from session and cookie handling to 
                        validation and custom status pages. Take a look at the " (:tutorial common/links) " and the " 
                  (:api common/links) " documentation..."]
               [:p [:em "and then go build some websites!"]]]
              [:div.right (:step4 gists)]
              ]
            
            ]

            ))
