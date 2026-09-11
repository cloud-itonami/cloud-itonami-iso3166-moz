(ns marketentry.registry-test
  (:require [clojure.test :refer [deftest is testing]]
            [marketentry.registry :as registry]))

(deftest engagement-fee-recompute
  (let [e {:base-fee 340000 :monthly-rate 19000 :monitoring-months 12 :claimed-fee 568000.0}]
    (is (== 568000.0 (registry/compute-engagement-fee e)))
    (is (true? (registry/engagement-fee-matches-claim? e))))
  (let [bad {:base-fee 340000 :monthly-rate 19000 :monitoring-months 12 :claimed-fee 700000.0}]
    (is (false? (registry/engagement-fee-matches-claim? bad)))))

(deftest register-draft-and-submit
  (let [d (registry/register-draft "eng-1" "MOZ" 0)
        s (registry/register-submit "eng-1" "MOZ" 0)]
    (is (= "MOZ-DFT-000000" (get d "draft_number")))
    (is (= "MOZ-SUB-000000" (get s "submit_number")))
    (is (nil? (get-in d ["certificate" "proof"])))
    (is (= "draft-unsigned" (get-in s ["certificate" "status"])))))

(deftest register-requires-ids
  (is (thrown? Exception (registry/register-draft "" "MOZ" 0)))
  (is (thrown? Exception (registry/register-submit "eng-1" "" 0))))

(deftest cadastro-unico-satisfied-not-required
  (testing "an engagement that does not itself require Cadastro Único is satisfied regardless of the other two fields"
    (is (true? (registry/cadastro-unico-satisfied? {:requires-cadastro-unico? false
                                                     :has-cadastro-unico? false
                                                     :on-impedidos-list? false})))))

(deftest cadastro-unico-satisfied-clean
  (is (true? (registry/cadastro-unico-satisfied? {:requires-cadastro-unico? true
                                                   :has-cadastro-unico? true
                                                   :on-impedidos-list? false}))))

(deftest cadastro-unico-unsatisfied-missing-registration
  (is (false? (registry/cadastro-unico-satisfied? {:requires-cadastro-unico? true
                                                    :has-cadastro-unico? false
                                                    :on-impedidos-list? false}))))

(deftest cadastro-unico-unsatisfied-debarred
  (testing "registered but on UFSA's own Impedidos (debarred) list -- still unsatisfied"
    (is (false? (registry/cadastro-unico-satisfied? {:requires-cadastro-unico? true
                                                      :has-cadastro-unico? true
                                                      :on-impedidos-list? true})))))
