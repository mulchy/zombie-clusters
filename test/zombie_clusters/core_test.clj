(ns zombie-clusters.core-test
  (:require [clojure.test :refer :all]
            [zombie-clusters.core :refer :all]))

(deftest zombie-clusters-test
  (testing "2 components"
    (is (= 2
           (zombieClusters ["1100" "1110" "0110" "0001"]))))

  (testing "Empty graph"
    (is (= 4
           (zombieClusters ["1000" "0100" "0010" "0001"])))))
