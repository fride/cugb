(ns practicums.assignment)

(def groups [{:id 0 :title "<unassigned>" :max 0}
             {:id 1 :title "Math" :max 2}
             {:id 2 :title "English" :max 2}
             {:id 3 :title "History" :max 2}])

(def persons [{:name "Donald" :prefs [1 3] :group 0}
              {:name "Daisy" :prefs [1 2] :group 0}
              {:name "Mickey" :prefs [1 2] :group 0}
              {:name "Goofy" :prefs [3 1] :group 0}])


; find a nice name for this
(defn p [groups persons]
  (map (fn [[gid persons]]
         (let [max (:max (groups gid))
               [booked overbooked] (split-at max persons)]
           {:gid gid :booked booked :overbooked overbooked :max max})
         ) (group-by :group persons))
         
  )

(defn greed-assign [persons step]
  (map (fn [person]
         (assoc person :group ((:prefs person) step)))
       persons)
  )
(defn persons-by-group [persons groups]
  ( for [g groups]
    {:id (g :id)
     :max (g :max)
     :persons (filter  #(= (g :id) (% :group) )
                       persons)}
  ))

(defn is-overbooked [group]
  (> (count (:persons group)) (:max group) ))

                                        ; liefert link die ueberbuchten, rechts die nicht ueberuchten.
(defn overbooked [persons groups]
  "Will return a tuple. The first element contains the overbooked groups. the second the not
   overbooked groups."
  (let [ [stuffed free]  (split-with is-overbooked (persons-by-group persons groups))]
    [stuffed free]))

(defn reassign [index groups persons]
  nil
  )

(defn assign [groups persons]
  (overbooked persons groups)
  )

; This invocation here 
#_(assign groups persons)

; should return:
; ({:group 3, :name "Donald", :prefs [1 3]}
;  {:group 1, :name "Daisy", :prefs [1 2]}
;  {:group 1, :name "Mickey", :prefs [1 2]}
;  {:group 3, :name "Goofy", :prefs [3 1]})
; or something similar

