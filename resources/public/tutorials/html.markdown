##Of vectors and keywords
Noir uses [Hiccup] to generate HTML, which represents elements as vectors whose first item is a keyword of the tag name. The best way to learn is to simply walk through a series of inputs and see what Hiccup outputs:
<script src="https://gist.github.com/1049822.js"> </script>

##Partials
Noir adds a simple macro to make writing functions that return html easy to identify and nice to write: (defpartial). The macro defines a function with the given name and just wraps the body in Hiccup's (html) function:
<script src="https://gist.github.com/1049935.js"> </script>

One of the greatest advantages of hiccup is the ability to use these html generating functions with higher-order functional concepts like map. As an example, here is the idiomatic way to generate a list of blog posts in Noir:
<script src="https://gist.github.com/1050697.js"> </script>

##Other templating options
Noir intentionally doesn't lock you into a specific templating solution, since it is highly dependent on the make up of your team and personal preference. As such, (defpartial) is the only real convenience method included in Noir for generating HTML. This means you can use [Enlive], [StringTemplate], or anything else you can think of inside of your page definitions.

[Enlive]: https://github.com/cgrand/enlive
[StringTemplate]: http://www.stringtemplate.org/
