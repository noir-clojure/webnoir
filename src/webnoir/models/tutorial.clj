(ns webnoir.models.tutorial
  (:use clj-markdown.core
        clojure.java.io))

(def compiled-tutorials (atom {}))

(defn file->name [f]
  (let [n (.getName f)
        i (.indexOf n ".")
        nme (subs n 0 i)
        ext (subs n i)]
    {:full n
     :ext ext
     :name nme}))

(defn compile-all []
  (let [files (.listFiles (file "resources/public/tutorials/"))]
    (doseq [tut files]
      (let [{file-name :name} (file->name tut)
            file-contents (slurp tut)]
        (swap! compiled-tutorials assoc file-name (md->html file-contents))))))

(defn get-tutorial [id]
  (get @compiled-tutorials id))

