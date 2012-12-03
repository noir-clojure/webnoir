---
layout: default
class: tutorialContent
---

##Sessions

Noir uses Ring's session handling to store per-user sessions and provides a simple API to get and update values in a
stateful way. These functions are found in the noir.session namespace and behave like you'd expect:

{% highlight clojure %}
(require '[noir.session :as session])

(session/put! :username "chris")
(session/get :username)
;;  => "chris"

(session/remove! :username)
(session/get :username)
;; => nil

(session/get :username "anon")
;; => "anon"

(session/put! :username "chris")
(session/put! :user-id 2)
(session/clear!)
{% endhighlight %}

As of 1.1.0, Noir also has flashing, which is typically used to store a simple message across redirects. For example, if
you redirect after a user is created, you would show the message "User added" on the user listings page. Note that flashes
in Noir have the lifetime of one retrieval, meaning that after the first (flash-get) the value will be nil.

{% highlight clojure %}
(session/flash-put! "User added!")
(session/flash-get)
;; => "User added!"
;;
(session/flash-get)
;; => nil
{% endhighlight %}

##Cookies
Cookies also have a simple stateful API that operates on the name of the cookie (which can either be a keyword or string).

{% highlight clojure %}
(require '[noir.cookies :as cookies])

(cookies/put! :user-id 2)
(cookies/get :user-id)
;; => 2

(cookies/get :username "anon")
;; => "anon"

;; you can also pass a map that has all the cookie's attributes
(cookies/put! :tracker {:value 29649 :path "/" :expires 1})
{% endhighlight %}

##A note on testing
One thing you have to keep in mind when using these functions is that they are only valid within the context of a request,
i.e. inside of a (defpage). Trying to call these outside of the noir stack will throw an exception, since the values for
the session and cookies come from modified request objects. In order to test these, you can use the (with-noir) macro
in noir.util.test.

{% highlight clojure %}
(use 'noir.util.test)

(deftest cookies-get
         (with-noir
           (is (nil? (cookies/get :noir)))
           (is (= "noir" (cookies/get :noir "noir")))))
{% endhighlight %}
