(ns webnoir.views.css
  (:use cssgen.use
        cssgen))

(def fldi (mixin :float :left
                 :display :inline))

(def emphasis (mixin :color :#6bffbd))
(def de-emph (mixin :color :#91979d))
(def dark-background (mixin :background :#2a2b2b
                            :color :#d1d9e1))

(def light-text (mixin :color :#d1d9e1))
(def box (mixin :border-radius :8px
                :padding :10px))
(def light-box (mixin box
                      :background :#474949
                      :border [:2px :solid :#616363]))
(def dark-box (mixin box
                      :background :#474949
                      :border [:2px :solid :#515353]))

(def emph-colors (mixin :background :#3f634d
                        :border [:2px :solid :#3c8455]))

(def emph-box (mixin box
                     emph-colors))


(css-file "resources/public/css/noir.css"
          (rule "body" 
                dark-background
                :background "url('/img/bg.png')"
                :padding [:60px :80px]
                :font-family "'Helvetica Neue',Helvetica,Verdana")
          (rule "#wrapper"
                :width :980px
                :margin [0 :auto])
          (rule "#content"
                fldi
                :width "100%"
                :min-height :480px
                :padding-bottom :100px)
          (rule "a"
                :text-decoration :none
                      light-text
                (rule "&:hover"
                emphasis
                      ))
          (rule "em"
                emphasis)
          (rule "h1"
                fldi
                :margin-bottom :0px
                light-text)
          (rule "h2"
                emphasis
                fldi
                :width "100%"
                :font-size :24px)
          (rule "h3"
                emphasis)
          (rule "p a"
                :text-decoration :underline
                de-emph)
          (rule "#started"
                :padding-top :70px
                :margin-bottom :30px)
          (rule "#tutorialContent"
                :margin-left :150px
                :width :700px
                (rule "blockquote"
                      de-emph
                      :margin [:30px 0]
                      :font-style :italic
                      :width :550px
                      :margin-left :50px)
                (rule ".gist"
                      :margin [:30px 0])
                (rule "p"
                      :margin-bottom :15px)
                (rule "h2"
                      :display :block
                      :margin [:15px 0]))
          (rule "#tutorials"
                fldi
                :width :800px
                :margin-left :90px
                (rule "ul"
                      fldi
                      :width "100%"
                      :list-style :none
                      :margin-bottom :20px
                      (rule "li"
                            fldi
                            :width "100%"
                            :margin-bottom :30px
                            :margin-right :20px
                            (rule "p"
                                  fldi
                                  de-emph
                                  :margin-top :10px
                                  :max-width :450px)
                            (rule "a"
                                  dark-box
                                  fldi
                                  :text-align :center
                                  :width :250px
                                  :margin-right :40px
                                  :font-size :18px
                                  :border-radius :8px
                                  :padding :20px
                                  (rule "&:hover"
                                        emph-colors
                                        ))))
                            
                (rule "h3"
                      fldi
                      :width "100%"
                      :margin-bottom :20px
                      :font-size :28px))
          (rule "#step2"
                :margin-top :110px)
          (rule "#step4"
                :margin-top :190px)
          (rule "#desc"
                :margin-top :45px)
          (rule "#footer"
                :padding-bottom :40px
                :float :left
                :display :inline
                :width "100%"
                :text-align :center)
          (rule "code"
                fldi
                light-box
                :font-family "Monaco, Consolas, 'Courier New'")
          (rule "#header"
                fldi
                :width "100%"
                :margin-bottom :100px
                (rule "h2"
                      fldi
                      light-text
                      :width "100%"
                      :margin-top :10px
                      :margin-left :60px
                      :font-size :18px
                      :font-weight :normal)
                (rule "ul"
                      :float :right
                      :display :inline
                      :list-style :none
                      :margin-top :30px
                      (rule "li"
                            fldi
                            (rule "a"
                                  fldi
                                  light-box
                                  :padding :8px
                                  :margin-left :10px
                                  (rule "&:hover"
                                        emph-colors
                                        )))))
          (rule "ul.start"
                (rule "li"
                      fldi
                      :margin-bottom :55px
                      :width "100%"
                      (rule "& + li"
                            :margin-top :20px)
                      (rule "p" 
                            :padding 0
                            :margin 0
                            :font-size :18px
                            )
                      (rule "p + p"
                            :margin-top :15px)
                      (rule ".gist"
                            :width "100%")

                      (rule ".right"
                            fldi
                            :width "45%"
                            :margin-left "10%")
                      (rule ".left"
                            fldi
                            :width "45%")))
          )


