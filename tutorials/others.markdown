---
layout: default
class: tutorialContent
---

## Using Noir with other Ring-based libraries
As of Noir 1.1.0 you can use the (noir.server/gen-handler) function to create a standard Ring-handler for your noir
application. The only catch is to make sure that you do so after you've defined your pages, past that, you simply
pass your server options like normal and you're on your way:

{% highlight clojure %}
(require '[noir.server :as server])

(server/load-views "src/noir-example/views")

(def handler (server/gen-handler {:mode :dev
                                  :ns 'noir-example}))
{% endhighlight %}

Now you could use this in lein ring or lein beanstalk, for example, by simply adding the handler to your project definition:

{% highlight clojure %}
(defproject noir-example "0.1.0"
            :description "An example of a noir project"
            :dependencies [[org.clojure/clojure "1.2.1"]
                           [noir "1.1.0"]]
            :dev-dependencies [[lein-ring "0.4.3"]]
            :ring {:handler noir-example.server/handler}
            :main noir-example.server)
{% endhighlight %}
