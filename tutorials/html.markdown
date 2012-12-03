---
layout: default
class: tutorialContent
---

##Of vectors and keywords
Noir uses [Hiccup][hiccup] to generate HTML, which represents elements as vectors whose first item is a keyword of the tag name. The best way to learn is to simply walk through a series of inputs and see what Hiccup outputs:

{% highlight clojure %}
(use 'hiccup.core)

;; ****************************************************************
;; The basics
;; ****************************************************************

(html [:p]) 
  => "<p />"

;; Any elements after that become the content of the tag
(html [:p "hey"]) 
  => "<p>hey</p>"

;; Tags can be nested inside of tags and everything ends up concatenated
(html [:p "Hello " [:em "world"]])
  => "<p>Hello <em>world</em></p>"

;; You can specify attributes by supplying a map after the tag name
(html [:p {:id "my-p"} "hey"])
  => "<p id=\"my-p\">hey</p>"

;; There are shortcuts for setting ID and class, if you know CSS, 
;; these should look familiar
(html [:p#my-p [:span.pretty "hey"]])
  => "<p id=\"my-p\"><span class=\"pretty\">hey</span></p>"

;; You can escape a string using the (escape-html) function
(html [:p (hiccup.util/escape-html "<script>Do something evil</script>")])
  => "<p>&lt;script&gt;Do something evil&lt;/script&gt;</p>"

;; the h function is a shortcut for (hiccup.util/escape-html)
(html [:p (h "<script>Do more evil</script>")])
  => "<p>&lt;script&gt;Do more evil&lt;/script&gt;</p>"

;; you can actually generate generic xml too
(html [:books 
       [:book#142 {:title "Noir for beginners"}]])
  => "<books><book id=\"142\" title=\"Noir for beginners\" /></books>"

;; ****************************************************************
;; Page helpers
;; ****************************************************************

(use 'hiccup.page)

;; you can use the html5, html4, or xhtml functions to write the 
;; doctype boilerplate for you
(html (html5 [:p "hey"]))
  => "<!DOCTYPE html>\n<html><p>hey</p></html>"

;; there are helpers for including css and js files
(html (include-js "/js/core.js")
      (include-css "/css/reset.css"))
  =>"<script src=\"/js/core.js\" type=\"text/javascript\"></script>
     <link href=\"/css/reset.css\" rel=\"stylesheet\" type=\"text/css\" />"

(use 'hiccup.element)
;; there are also functions for creating links and images
(html (link-to "http://www.webnoir.org" "Noir")
      (mail-to "cool@cool.com")
      (image "/img/logo.png" "Noir"))
  => "<a href=\"http://www.webnoir.org\">Noir</a>
      <a href=\"mailto:cool@cool.com\">cool@cool.com</a>
      <image alt=\"Noir\" src=\"/img/logo.png\" />"

;; these functions can take maps to add custom attributes too
(html (link-to {:class "pretty"} "http://www.webnoir.org" "Noir"))
  => "<a class=\"pretty\" href=\"http://www.webnoir.org\">Noir</a>"

;; ****************************************************************
;; Form helpers
;; ****************************************************************

(use 'hiccup.form)

;; form helpers help you write the boilerplate for creating fields.
;; There are functions for all the different html input types.
(html (form-to [:post "/login"]
               (text-field "Username")
               (password-field "Password")
               (submit-button "Login")))
  => "<form action=\"/login\" method=\"POST\">
     <input id=\"Username\" name=\"Username\" type=\"text\" />
     <input id=\"Password\" name=\"Password\" type=\"password\" />
     <input type=\"submit\" value=\"Login\" />
     </form>"

;; the fields can take initial values as well
(html (text-field "Username" "chris"))
  => "<input id=\"Username\" name=\"Username\" type=\"text\" 
      value=\"chris\" />"
{% endhighlight %}

##Partials
Noir adds a simple macro to make writing functions that return html easy to identify and nice to write: (defpartial). The macro defines a function with the given name and just wraps the body in Hiccup's (html) function:

{% highlight clojure %}
(defmacro defpartial 
  "Create a function that returns html using hiccup. The function is 
   callable with the given name."
  [fname params & body]
  `(defn ~fname ~params
     (html
       ~@body)))

(defpartial hello [person]
  [:p "Hello " person])

(hello "noir")
  =>"<p>Hello noir</p>"
{% endhighlight %}

One of the greatest advantages of hiccup is the ability to use these html generating functions with higher-order functional concepts like map. As an example, here is the idiomatic way to generate a list of blog posts in Noir:

{% highlight clojure %}
(defpartial post-item [{:keys [perma-link title md-body date tme]}]
            [:li.post
             [:h2 (link-to perma-link title)]
             [:ul.datetime
              [:li date]
              [:li tme]]
             [:div.content md-body]])

(defpartial posts-list [items]
            [:ul.posts
             (map post-item items)])
{% endhighlight %}

##Other templating options
Noir intentionally doesn't lock you into a specific templating solution, since it is highly dependent on the make up of your team and personal preference. As such, (defpartial) is the only real convenience method included in Noir for generating HTML. This means you can use [Enlive], [StringTemplate], or anything else you can think of inside of your page definitions.

[hiccup]: https://github.com/weavejester/hiccup
[Enlive]: https://github.com/cgrand/enlive
[StringTemplate]: http://www.stringtemplate.org/
