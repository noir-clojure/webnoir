(defpage "/welcome" []
    "Welcome to Noir!")

(server/start 8080 {})


(defpartial todo-item [{:keys [id title due]}]
    [:li {:id id} ;; maps define HTML attributes
        [:h3 title]
        [:span.due due]]) ;; add a class

(defpartial todos-list [items]
    [:ul#todoItems ;; set the id attribute
        (map todo-item items)])

(todos-list [{:id "todo1"
              :title "Get Milk"
              :due "today"}])
;; =>
;; <ul id="todoItems">
;;  <li id="todo1">
;;    <h3>Get Milk</h3>
;;    <span class="due">today</span>
;;  </li>
;; </ul>



;;Create a page that lists out all our todos
(defpage "/todos" {}
         (let [items (all-todos)]
           (layout
             [:h1 "Todo list!"]
             (todos-list items))))

;; Handle an HTTP POST to /todos, returning a json 
;; object if successful
(defpage [:post "/todos"] {:keys [title due]}
         (if-let [todo-id (add-todo title due)]
           (response/json {:id todo-id
                           :title title
                           :due-date due-date})
           (response/empty)))

;; We can define route params too by making them
;; a keyword: /some/route/:param-name
(defpage "/todo/remove/:id" {todo-id :id}
         (if (remove-todo todo-id)
           (response/json {:id todo-id})
           (response/empty)))




;; add a value to the session
(defpage "/login" {}
         (session/put! :admin true)
         (layout
           [:p "Are you loggedin? "] 
           [:p (session/get :admin)]))

;; set a cookie and get its value
(defpage "/cookie" []
         (cookie/put! :noir "stuff")
         (let [v (cookie/get :noir)]
           (layout
             [:p "You created a cookie:"]
             [:p "Value " v])))

;; validate our math, if the first statement
;; is false, it fails validation and the error is
;; added for the given key.
(defpage "/validate" []
         (vali/rule (= 3 3)
                    [:math "3 != 3"])
         (vali/rule (= 1 2)
                    [:math "1 != 2"])
         (layout
           [:p "Let's check your math: "]
           [:p (str (vali/get-errors :math))]))
