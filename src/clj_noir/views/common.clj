(ns clj-noir.views.common
  (use noir.core
       hiccup.core
       hiccup.page-helpers))

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

