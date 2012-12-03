---
layout: default
class: tutorialContent
---

## Getting some input
The first thing we need to do is create a form. We'll do that using hiccup, so if you haven't read the
Generating HTML doc, go do so real quick. In terms of organization, I've found it best to define forms as a partial
of their fields like so:

{% highlight clojure %}
(defpartial layout [& content]
  (html5
    [:head
     [:title "Forms"]]
    [:body
     content]))

(defpartial user-fields [{:keys [firstname lastname]}]
  (label "firstname" "First name: ")
  (text-field "firstname" firstname)
  (label "lastname" "Last name: ")
  (text-field "lastname" lastname))

(defpage "/user/add" {:as user}
  (layout
    (form-to [:post "/user/add"]
            (user-fields user)
            (submit-button "Add user"))))
{% endhighlight %}

This allows you to use that partial for all the various incarnations of the form (add, edit, etc). Another good
practice is to have forms post to the same URL, this will remove the need for extraneous redirects in the case
of an error. Right now, though, our form posts to a URL that we haven't handled. So let's define a page for the
POST to /user/add. Basically what we'll want it to do is check if our input is valid and either display a success
message or show the form again:

{% highlight clojure %}
(require '[noir.response :as resp])

(defn valid? [{:keys [firstname lastname]}]
  true)

(defpage [:post "/user/add"] {:as user}
  (if (valid? user)
    (layout
      [:p "User added!"])    
    (render "/user/add" user)))
{% endhighlight %}

This is probably the first time you've seen the (render) function. It allows you to call a page by its url as if it
were just a normal function. In this case, it's how we'll show the form again without having to redirect. Note that we're also
passing our parameters through so that whatever we inputted shows up when we render the form again. So now we need
to fill in that (valid?) function, which we'll do by using noir's validation functions.

{% highlight clojure %}
(require '[noir.validation :as vali])

(defn valid? [{:keys [firstname lastname]}]
  (vali/rule (vali/min-length? firstname 5)
             [:firstname "Your first name must have more than 5 letters."])
  (vali/rule (vali/has-value? lastname)
             [:lastname "You must have a last name"])
  (not (vali/errors? :lastname :firstname)))
{% endhighlight %}

What you see here is a set of rules that have the form (rule passed? [error-key error-text]) where if value of passed?
is false, the error text is added to the error key. There are several ways to then retrieve those errors, one of
which is to ask if there are any errors for a list of fields. That's done via the (errors?) function we use at the
end to determine if all our rules passed. Note that there's nothing special about the value you pass to (rule) and you
can use anything that returns truthiness as your test. Alright, we have errors and now we need to show them on our form. 
The last thing we'll use is the (on-error) function to show a partial when we have an error in our form.

{% highlight clojure %}
(defpartial error-item [[first-error]]
  [:p.error first-error])

(defpartial user-fields [{:keys [firstname lastname]}]
  (vali/on-error :firstname error-item)
  (label "firstname" "First name: ")
  (text-field "firstname" firstname)
  (vali/on-error :lastname error-item)
  (label "lastname" "Last name: ")
  (text-field "lastname" lastname))
{% endhighlight %}

One thing to note here is that errors for a field are provided as a collection since multiple rules can add errors
to a field. In this case, we only care about the first error, so our partial destructures it down to one value. 

And that's it! Here's the complete code:

{% highlight clojure %}
(use 'noir.core 'hiccup.page-helpers 'hiccup.form-helpers)
(require '[noir.validation :as vali])
(require '[noir.response :as resp])

(defpartial layout [& content]
  (html5
    [:head
     [:title "Forms"]]
    [:body
     content]))

(defpartial error-item [[first-error]]
  [:p.error first-error])

(defpartial user-fields [{:keys [firstname lastname]}]
  (vali/on-error :firstname error-item)
  (label "firstname" "First name: ")
  (text-field "firstname" firstname)
  (vali/on-error :lastname error-item)
  (label "lastname" "Last name: ")
  (text-field "lastname" lastname))

(defn valid? [{:keys [firstname lastname]}]
  (vali/rule (vali/min-length? firstname 5)
             [:firstname "Your first name must have more than 5 letters."])
  (vali/rule (vali/has-value? lastname)
             [:lastname "You must have a last name"])
  (not (vali/errors? :lastname :firstname)))

(defpage "/user/add" {:as user}
  (common/layout
    (form-to [:post "/user/add"]
            (user-fields user)
            (submit-button "Add user"))))

(defpage [:post "/user/add"] {:as user}
  (if (valid? user)
    (layout
      [:p "User added!"])
    (render "/user/add" user)))
{% endhighlight %}
