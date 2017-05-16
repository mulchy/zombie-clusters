(ns zombie-clusters.core
  (:gen-class))

(defn- adjacent [row]
  (keep-indexed (fn [idx char]
                  (if (= \1 char)
                    idx
                    nil))
                row))

(defn- make-adjacency-list [zombies]
  (zipmap (range)
          (map (comp set adjacent)
               zombies)))

(defn zombieClusters [zombies]
  (let [adjacency-list (make-adjacency-list zombies)
        get-neighbors  (fn [n] (get adjacency-list n))]
    (loop [visited #{}
           components 0
           vertices (set (range (count zombies)))]
      (if (empty? vertices)
        components
        (let [current               (first vertices)
              visited               (conj visited current)
              neighbors             (get-neighbors current)
              neighbors-not-visited (remove visited neighbors)]
          (if (empty? neighbors-not-visited)
            (recur visited
                   (inc components)
                   (set (rest vertices)))
            (recur visited
                   components
                   (set (into (rest vertices) neighbors-not-visited)))))))))
