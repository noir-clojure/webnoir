---
layout: default
class: tutorialContent
---

##The man in the middle
What is middleware? Well, here's a quote from the [Ring Concepts][] doc:

> Middleware are higher-level functions that add additional functionality 
> to handlers. The first argument of a middleware function should be a 
> handler, and its return value should be a new handler function.

So basically, it's a function that takes in a handler and returns a new, more powerful handler. That's great,
but that doesn't tell us why we should care about them. You can think of middleware as a series of transformations
that gets applied to requests before they get routed and after they a response is generated.

Let's look at a practical example: enabling utf-8 characters in all of our responses. Noir includes a middleware
function to do exactly that, and it looks like this:

{% highlight clojure %}
(defn wrap-utf-8 
  "Adds the 'charset=utf-8' clause onto the content type declaration, 
  allowing pages to display all utf-8 characters."
  [handler]
  (fn [request]
    (let [resp (handler request)
          ct (get-in resp [:headers "Content-Type"])
          neue-ct (str ct "; charset=utf-8")]
      (assoc-in resp [:headers "Content-Type"] neue-ct))))
{% endhighlight %}

So as you can see, we're returning a new handler function that takes in the request map and then calls the next handler
to get a response. Once it has the response it gets the "Content-Type" header and appends "; charset=utf-8" onto it and
associates that into the response map. These functions are usually fairly simple, like this one, and always follow the
form of returning an anonymous function that takes in a request and returns a response. If you look through the 
[Noir source][], a great deal of its functionality is implemented in middleware. Another [great example][] 
is ring itself.

To add a custom piece of middleware to noir, all you have to do is:

{% highlight clojure %}
(server/add-middleware my-middleware)
{% endhighlight %}


##So when do I use it?
Anytime you need to modify requests or responses at a level beyond simply modifying status codes or bodies, 
middleware is likely your best option. Keep in mind, however, that every single request will then be routed through
that function. Performance is important here, and middleware's use should be judicious. A good rule of thumb is if
every single request needs to have something done to it or if you need access to the http request/response information
directly, then middleware is an option. Otherwise, see if there's another solution.

[Ring Concepts]: https://github.com/mmcgrana/ring/wiki/Concepts
[great example]: https://github.com/mmcgrana/ring/tree/master/ring-core/src/ring/middleware
[Noir source]: https://github.com/ibdknox/noir/tree/master/src/noir

