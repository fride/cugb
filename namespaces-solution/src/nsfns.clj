(ns nsfns)

(defn s-page
  []
  "S") 

(defn- nsfn
  [suffix [sym var]]
  (let [s (name sym)]
    (if (.endsWith s suffix)
      [(.substring s 0 (- (count s) (count suffix))) var]
      nil)))
		
(defn nsfns
  [suffix ns]
  (->> ns
       ns-publics
       (map (partial nsfn "-page"))
       (remove nil?)
       (into {})))
