---
layout: default
class: tutorialContent
---

##From url to page

Noir uses the (defpage) macro to define what happens when a certain URL is accessed. To do this, Noir uses [Compojure][], which handles matching the request to a handler as well as setting up the routes for resources and such. By using Compojure, we have access to a powerful mechanism for defining routes and their actions. Let's see what we can do.

{% highlight clojure %}
;; A very simple page definition that maps to the root of your site.
(defpage "/" []
  "hello")
{% endhighlight %}

With this page definition you see that the first parameter is the URL
to map this page to, the second parameter is a destructuring form for
the params of the request (we'll get to that shortly) and the rest is
the content of the response. This will respond to an HTTP GET request,
but what happens when we want to handle a post?

{% highlight clojure %}
(defpage [:post "/"] []
  "You posted here!")
{% endhighlight %}


What we've done here is adjusted our first parameter to be a vector
containing the keyword of the request type to respond to followed
by the url. You can do this with any of the HTTP request types:

{% highlight clojure %}
(defpage [:get "/"] [] "This is a get") ;; same as (defpage "/" [] ..)
(defpage [:post "/"] [] "This is a post")
(defpage [:put "/"] [] "This is a put")
(defpage [:any "/"] [] "This is any request type")
;; and so on...
{% endhighlight %}


Now with something like a post, it's not very useful if we post a form
and can't get to the posted values. This is what that second value in
the routes is for. It's a destructuring form for the params of the request.
This includes all of the GET and POST variables. So let's say we have a login
form that has fields named username and password and we post it to /login

{% highlight clojure %}
(defpage [:post "/login"] {:keys [username password]}
  (str "You tried to login as " username " with the password " password))
{% endhighlight %}


So here we're pulling the values posted as username and password out of the
params. The destructuring form used here is just like the one you'd use in
a (let) form, so you can use all of clojure's powerful destructuring concepts.
There's another kind of param that you have access to here as well, and that is
route params, which are used for dynamic routes.

{% highlight clojure %}
(defpage "/user/:id" {:keys [id]}
  (str "You are user number " id))
{% endhighlight %}


This will match any routes like /user/12 and bind the last segment to the param
key :id. This allows you to define some pretty complex routes and pull the 
interesting parts of those out of the URL. You can also take this a step 
further and even define regex's for the route params to match against:

{% highlight clojure %}
(defpage [:get ["/user/:id" :id #"\d+"]] {:keys [id]}
  (str "You are user number " id))
{% endhighlight %}


##What happens to the content we return?

This depends on what you return. If you simply return a string like we are here, or
like you would by using (defpartial) or hiccup's (html) function, that is interpreted
as the body of your HTTP Response and sent off. Alternatively, you can return a map
with keys that correspond to the [Ring][] spec. Here's an example of returning
a response with a different status code:

{% highlight clojure %}
(defpage "/error" []
  {:status 500
   :body "Oh no! An error has occurred"})
{% endhighlight %}


In general, you shouldn't need to use anything more than strings and if you find yourself
needing to, check out the [noir.response][] namespace first. 

##Routes as filters

Noir also has the concept of a pre-route, which is a set of routes that get evaluated before
you get to the routes defined by (defpage). This is usually used to filter out access to an entire 
set of URLs based on permissions. Say for example, that you have an admin section, instead of checking
whether or not a user has permission in every single (defpage), you could just do this:

{% highlight clojure %}
(pre-route "/admin/*" {}
           (when-not (users/admin?)
             (resp/redirect "/login")))
{% endhighlight %}


[noir.response]: /autodoc/1.1.0/noir.response-api.html
[Ring]: https://github.com/mmcgrana/ring
[Compojure]: https://github.com/weavejester/compojure
