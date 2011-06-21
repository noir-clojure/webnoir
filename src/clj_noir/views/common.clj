(ns clj-noir.views.common
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(defpartial footer []
            [:div#footer
             [:p "Copyright © 2011 " (link-to "http://chris-granger.com" "Chris Granger") ". All rights reserved."]])

(defpartial layout [& content]
            (html5
              [:head
               [:title "Noir"]
               (include-css "/css/reset.css")
               (include-css "/css/noir.css")
               (include-css "/css/gist.css")]
              [:body
               [:div#wrapper
                [:div#content
                 content]
                (footer)]]))

