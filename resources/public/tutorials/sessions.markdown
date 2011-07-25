##Sessions

Noir uses Ring's session handling to store per-user sessions and provides a simple API to get and update values in a
stateful way. These functions are found in the noir.session namespace and behave like you'd expect:
<script src="https://gist.github.com/1103278.js?file=session.clj"></script>

As of 1.1.0, Noir also has flashing, which is typically used to store a simple message across redirects. For example, if
you redirect after a user is created, you would show the message "User added" on the user listings page. Note that flashes
in Noir have the lifetime of one retrieval, meaning that after the first (flash-get) the value will be nil.
<script src="https://gist.github.com/1103278.js?file=flash.clj"></script>

##Cookies
Cookies also have a simple stateful API that operates on the name of the cookie (which can either be a keyword or string).
<script src="https://gist.github.com/1103278.js?file=cookies.clj"></script>

##A note on testing
One thing you have to keep in mind when using these functions is that they are only valid within the context of a request,
i.e. inside of a (defpage). Trying to call these outside of the noir stack will throw an exception, since the values for
the session and cookies come from modified request objects. In order to test these, you can use the (with-noir) macro
in noir.util.test.
<script src="https://gist.github.com/1103278.js?file=testing.clj"></script>
