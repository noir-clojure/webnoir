## Using Noir with other Ring-based libraries
As of Noir 1.1.0 you can use the (noir.server/gen-handler) function to create a standard Ring-handler for your noir
application. The only catch is to make sure that you do so after you've defined your pages, past that, you simply
pass your server options like normal and you're on your way:
<script src="https://gist.github.com/1103341.js?file=genhandler.clj"></script>

Now you could use this in lein ring or lein beanstalk, for example, by simply adding the handler to your project definition:
<script src="https://gist.github.com/1103341.js?file=leinring.clj"></script>
