;;Create a page that lists out all our todos
(defpage "/todos" {}
         (let [items (all-todos)]
           (layout
             [:h1 "Todo list!"]
             (todos-list items))))

;; Handle posts to that page by adding a new todo 
;; and returning a json object of the new todo as 
;; the response
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

;; use validation to show 
(defpage "/valid" []
         (vali/rule (= 3 3)
                    [:math "3 and 3 are not equal"])
         (vali/rule (= 1 2)
                    [:math "1 and 2 are not equal"])
         (layout
           [:p "Let's check your math: "]
           [:p (str (vali/errors? :math))]))
