---
layout: default
class: tutorialContent
---

##How in the world does this thing work?
That's the question we're going to set out to answer, but in order to do so, we need to understand a little bit about how the internet works.

##Bouncing around the tubes
When you access a URL in your browser, you eventually end up sending an [HTTP request][] to a server connected to the internet. This request includes information that is needed to serve you a response; things like the exact url you requested, the query string, any POST information, your cookies, etc. The server then hands that off to some program that generates an HTTP response. The body of this response ends up being the content you finally see in your browser. In Clojure, the [Ring][] library handles transforming these HTTP requests and responses into something easy for us to use: Clojure maps. 

##One Ring to rule them all

##A man in the middle

##Now do something with it

##We're off to see the wizard


[HTTP request]: http://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol
[Ring]: https://github.com/mmcgrana/ring
[Compojure]: https://github.com/weavejester/compojure
[Hiccup]: https://github.com/weavejester/hiccup
