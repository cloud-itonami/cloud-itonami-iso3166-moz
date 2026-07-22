(ns statute.facts-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest moz-has-spec-basis
  (let [sb (facts/spec-basis "MOZ")]
    (is (= 3 (count sb)))
    (is (every? #(str/starts-with? (:statute/url %) "https://") sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["MOZ" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["moz.lei-do-trabalho-2023"]
         (mapv :statute/id (facts/by-topic "MOZ" :labor))))
  (is (= ["moz.lei-de-investimentos-2023"]
         (mapv :statute/id (facts/by-topic "MOZ" :investment))))
  (is (= ["moz.bau-decreto-29-2023"]
         (mapv :statute/id (facts/by-topic "MOZ" :business-registration))))
  (is (empty? (facts/by-topic "MOZ" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))

(deftest labour-law-cites-current-not-superseded-act
  (let [sb (first (facts/by-topic "MOZ" :labor))]
    (is (re-find #"13/2023" (:statute/law-number sb)))
    (is (re-find #"revoga" (:statute/law-number sb)))
    (is (re-find #"23/2007" (:statute/law-number sb)))))
