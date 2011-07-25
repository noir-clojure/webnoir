(ns webnoir.views.common
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

(def links {:api (link-to "/docs/index.html" "API")
            :tutorial (link-to "/tutorials" "tutorials")
            :blog-project (link-to "https://github.com/ibdknox/Noir-blog" "blog project")
            :clojure (link-to "http://clojure.org" "Clojure")
            :lein-noir (link-to "https://github.com/ibdknox/lein-noir" "lein-noir")
            :lein (link-to "https://github.com/technomancy/leiningen" "leiningen")
            :compojure (link-to "https://github.com/weavejester/compojure" "Compojure")
            :hiccup (link-to "https://github.com/weavejester/hiccup" "Hiccup")
            :ring (link-to "https://github.com/mmcgrana/ring" "Ring")})

(def header-links [{:url "/#started" :text "Get Started"}
                   {:url "/tutorials" :text "Tutorials"}
                   {:url "http://groups.google.com/group/clj-noir" :text "Google Group"}
                   {:url "/docs/" :text "API"}
                   {:url "https://github.com/ibdknox/noir" :text "Src"}])

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

(def analytics "
<script type=\"text/javascript\">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-24109853-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>")

(defpartial footer []
            [:div#footer
             [:p "Copyright © 2011 " (link-to "http://chris-granger.com" "Chris Granger") ". All rights reserved."]])

(defpartial layout [& content]
            (html5
              [:head
               [:title "Noir"]
               (include-css "/css/reset.css")
               (include-css "/css/noir.css")
               (include-css "/css/gist.css")
               analytics]
              [:body
               [:div#wrapper
                [:div#content
                 content]
                (footer)]]))

