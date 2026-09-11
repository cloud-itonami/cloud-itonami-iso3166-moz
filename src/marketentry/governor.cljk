(ns marketentry.governor
  "Market-Entry Compliance Governor -- the independent compliance layer
  that earns the MarketEntry-LLM the right to commit. The LLM has no
  notion of Mozambican procurement law, whether a claimed Cadastro
  Único de Empreiteiros de Obras Públicas, Fornecedores de Bens e
  Prestadores de Serviços ao Estado registration is actually on file
  with UFSA (or whether that same supplier is actually on UFSA's own
  Cadastro Único ...Impedidos debarment list), whether a claimed
  engagement fee actually equals base + months x rate, or when a draft
  stops being a draft and becomes a real-world filing submission, so
  this MUST be a separate system able to *reject* a proposal and fall
  back to HOLD.

  `:itonami.blueprint/governor` is `:market-entry-compliance-governor`
  (shared family keyword on blueprints; this is the MOZ-family
  implementation, adapted from the `cloud-itonami-iso3166-gnb`
  reference implementation -- Guinea-Bissau's own SIX-check shape,
  which this catalog's own dossier justifies for the same reason: MOZ's
  dossier does not ground a distinct tax-ID/NUIT-establishing-decree
  check separate from ordinary Cadastro Único registration, see
  `marketentry.facts`).

  This blueprint's own text (docs/business-model.md Trust Controls:
  'any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off'; 'a false or fabricated regulatory-requirement claim
  is a HARD hold') names exactly the checks below.

  SIX checks, in priority order, ALL HARD violations: a human approver
  CANNOT override them. The confidence/actuation gate is SOFT: it asks
  a human to look (low confidence / actuation), and the human may
  approve -- but see `marketentry.phase`: for `:stake
  :actuation/draft-filing`/`:actuation/submit-filing` NO phase ever
  allows auto-commit either. Two independent layers agree that
  actuation is always a human call.

    1. Spec-basis                    -- did the jurisdiction proposal
                                         cite an OFFICIAL source
                                         (`marketentry.facts`), or
                                         invent one?
    2. Evidence incomplete           -- for `:filing/draft`/
                                         `:filing/submit`, has the
                                         jurisdiction actually been
                                         assessed with a full evidence
                                         checklist on file?
    3. Cadastro Único missing/
       impeded                       -- for `:filing/submit`, when the
                                         engagement declares
                                         `:requires-cadastro-unico?
                                         true`, INDEPENDENTLY verify
                                         `:has-cadastro-unico?` is true
                                         AND `:on-impedidos-list?` is
                                         NOT true. FLAGSHIP check for
                                         this jurisdiction, grounded
                                         directly in Artigo 43 of the
                                         Regulamento (Decreto n.º
                                         79/2022): UFSA maintains TWO
                                         distinct registries -- an
                                         eligible-supplier registry AND
                                         a debarred (\"Impedidos\")
                                         registry -- so this check is a
                                         genuine two-condition test
                                         (missing registration OR
                                         active debarment), not a
                                         single boolean, reflecting the
                                         Regulamento's own two-registry
                                         design (see
                                         `marketentry.facts`).
    4. Engagement fee mismatch       -- for `:filing/submit`,
                                         INDEPENDENTLY recompute whether
                                         the engagement's own
                                         `:claimed-fee` equals `base-fee
                                         + monthly-rate x monitoring-
                                         months` -- honest
                                         reapplication of the ground-
                                         truth-recompute discipline
                                         sibling actors use.
    5. Confidence floor / actuation
       gate                          -- LLM confidence below threshold,
                                         OR the op is `:filing/draft`/
                                         `:filing/submit` (REAL acts)
                                         -> escalate. (SOFT -- see
                                         above.)

  Two more guards, double-draft/double-submit prevention, are enforced
  off dedicated `:drafted?`/`:submitted?` facts (never a `:status`
  value) -- these plus items 1-4 above are the six HARD violation
  functions `check` actually concatenates.

  This catalog's dossier (`marketentry.facts`) does NOT ground a
  distinct NUIT-establishing-decree check separate from ordinary
  Cadastro Único registration (fiscal regularity is one of the
  Cadastro Único's own Artigo 44 qualification requirements, not a
  wholly separate regime this iteration could independently cite by
  its own decree number) -- so, like `cloud-itonami-iso3166-gnb`, this
  is SIX checks, not seven. Padding the check count to match a richer
  sibling would itself be a fabrication."
  (:require [marketentry.facts :as facts]
            [marketentry.registry :as registry]
            [marketentry.store :as store]))

(def confidence-floor 0.6)

(def high-stakes
  "Stakes grave enough to always require a human, even when clean.
  Drafting a real filing package and submitting a real portal/filing
  registration are the two real-world actuation events this actor
  performs."
  #{:actuation/draft-filing :actuation/submit-filing})

;; ----------------------------- checks -----------------------------

(defn- spec-basis-violations
  "A `:jurisdiction/assess` (or `:filing/draft`/`:filing/submit`)
  proposal with no spec-basis citation is a HARD violation -- never
  invent a jurisdiction's market-entry requirements."
  [{:keys [op]} proposal]
  (when (contains? #{:jurisdiction/assess :filing/draft :filing/submit} op)
    (let [value (:value proposal)]
      (when (or (empty? (:cites proposal))
                (and (contains? value :spec-basis) (nil? (:spec-basis value))))
        [{:rule :no-spec-basis
          :detail "公式spec-basisの引用が無い提案は法域要件として扱えない"}]))))

(defn- evidence-incomplete-violations
  "For `:filing/draft`/`:filing/submit`, the jurisdiction's required
  registration evidence must actually be satisfied."
  [{:keys [op subject]} st]
  (when (contains? #{:filing/draft :filing/submit} op)
    (let [e (store/engagement st subject)
          assessment (store/assessment-of st subject)]
      (when-not (and assessment
                     (facts/required-evidence-satisfied?
                      (:jurisdiction e) (:checklist assessment)))
        [{:rule :evidence-incomplete
          :detail "法域の必要書類(BAU登録/NUIT/Cadastro Único登録/APIEX投資登録等)が充足していない状態での提案"}]))))

(defn- cadastro-unico-missing-violations
  "For `:filing/submit`, when the engagement declares
  `:requires-cadastro-unico? true`, INDEPENDENTLY verify
  `:has-cadastro-unico?` is true AND `:on-impedidos-list?` is NOT true
  -- the MOZ flagship check, grounded in Artigo 43 of the Regulamento
  (Decreto n.º 79/2022): UFSA maintains a Cadastro Único of ELIGIBLE
  suppliers/contractors AND a separate Cadastro Único of Impedidos
  (debarred) suppliers/contractors. CONDITIONAL on the engagement's own
  `:requires-cadastro-unico?` ground truth (not every engagement
  necessarily requires it)."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (cond
        (and (true? (:requires-cadastro-unico? e))
             (not (true? (:has-cadastro-unico? e))))
        [{:rule :cadastro-unico-missing
          :detail (str subject " はCadastro Único de Empreiteiros de Obras Públicas, "
                      "Fornecedores de Bens e Prestadores de Serviços ao Estado(UFSA, "
                      "Artigo 43)への登録を要するが未確認 -- 提出提案は進められない")}]

        (true? (:on-impedidos-list? e))
        [{:rule :cadastro-unico-missing
          :detail (str subject " はUFSAのCadastro Único ...Impedidos(欠格者)登録に"
                      "記載されている -- 提出提案は進められない")}]))))

(defn- engagement-fee-mismatch-violations
  "For `:filing/submit`, INDEPENDENTLY recompute whether the
  engagement's own claimed fee equals base + months x rate."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (let [e (store/engagement st subject)]
      (when-not (registry/engagement-fee-matches-claim? e)
        [{:rule :engagement-fee-mismatch
          :detail (str subject " の申告手数料(" (:claimed-fee e)
                      ")が独立再計算値(" (registry/compute-engagement-fee e) ")と一致しない")}]))))

(defn- already-drafted-violations
  "For `:filing/draft`, refuses to draft the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/draft)
    (when (store/engagement-already-drafted? st subject)
      [{:rule :already-drafted
        :detail (str subject " は既にドラフト済み")}])))

(defn- already-submitted-violations
  "For `:filing/submit`, refuses to submit the SAME engagement twice."
  [{:keys [op subject]} st]
  (when (= op :filing/submit)
    (when (store/engagement-already-submitted? st subject)
      [{:rule :already-submitted
        :detail (str subject " は既に提出済み")}])))

(defn check
  "Censors a MarketEntry-LLM proposal against the governor rules.
  Returns {:ok? bool :violations [..] :confidence c :escalate? bool
  :high-stakes? bool :hard? bool}."
  [request _context proposal st]
  (let [hard (into []
                   (concat (spec-basis-violations request proposal)
                           (evidence-incomplete-violations request st)
                           (cadastro-unico-missing-violations request st)
                           (engagement-fee-mismatch-violations request st)
                           (already-drafted-violations request st)
                           (already-submitted-violations request st)))
        conf (:confidence proposal 0.0)
        low? (< conf confidence-floor)
        stakes? (boolean (high-stakes (:stake proposal)))
        hard? (boolean (seq hard))]
    {:ok?          (and (not hard?) (not low?) (not stakes?))
     :violations   hard
     :confidence   conf
     :hard?        hard?
     :escalate?    (and (not hard?) (or low? stakes?))
     :high-stakes? stakes?}))

(defn hold-fact
  "The audit fact written when a proposal is rejected (HOLD)."
  [request context verdict]
  {:t          :governor-hold
   :op         (:op request)
   :actor      (:actor-id context)
   :subject    (:subject request)
   :disposition :hold
   :basis      (mapv :rule (:violations verdict))
   :violations (:violations verdict)
   :confidence (:confidence verdict)})
