(ns statute.facts
  "General-law compliance catalog for the Republic of Mozambique (MOZ)
  -- extends this repo's existing `marketentry.facts` (public-
  procurement market-entry only, narrow scope) with a second, orthogonal
  catalog of statutes a company operating in this jurisdiction must
  generally track for compliance. Mirrors cloud-itonami-iso3166-ben/
  -btn/-caf/-cog/-gin/-gnb's `statute.facts` (ADR-2607141700,
  cloud-itonami-compliance-fact-federation).

  Every entry below cites a government-hosted PDF (Boletim da
  República, Mozambique's own official gazette) or an official-agency-
  hosted download link -- never fabricated, all curl/pdftotext-verified
  directly this session (2026-07-22/23):

  - **Lei do Trabalho (Labour Law)**: this iteration found, on the
    Instituto Nacional de Segurança Social (INSS)'s own
    `https://www.inss.gov.mz/legislacao/` page (fetched directly, own
    link text, verbatim), TWO distinct labour-law downloads: \"Lei
    23/2007 de 1 de Agosto – Lei do Trabalho\" AND \"Nova Lei de
    Trabalho nº 13/2023 de 25 de Agosto\". This iteration downloaded
    the 2023 one directly and read its own primary text (a real,
    machine-readable Boletim da República PDF -- I Série, Número 165,
    2.º Suplemento, 25 de Agosto de 2023; `pdftotext` succeeded),
    confirming, verbatim, in the gazette's own SUMÁRIO section: \"Lei
    n.º 13/2023: ... Lei do Trabalho e revoga a Lei n.º 23/2007, de 1
    de Agosto.\" and, in the new Law's own final article: \"É revogada
    a Lei n.º 23/2007, de 1de Agosto.\" -- so Lei n.º 13/2023 (NOT the
    2007 one) is the CURRENT Lei do Trabalho, confirmed directly from
    its own primary text, not merely from INSS's citing page.
  - **Lei de Investimentos (Private Investment Law)**: `apiex.gov.mz`
    itself returned a \"BunkerWeb\"/\"Nothing to see here\" bot-
    detection block page to every direct fetch attempt this session, so
    this iteration used the Wayback Machine's most recent snapshot of
    APIEX's own `/legislation/` page (APIEX's own real, recently-
    crawled content, not a third-party paraphrase) to find the exact
    PDF APIEX's own site labels \"Law n.º 8/2023 Private Investment
    Law\", downloaded it via the same snapshot, and read its own
    primary text directly (`pdftotext`, a real machine-readable PDF),
    confirming, verbatim: \"Law No.8/2023 of 9 June ... considering the
    profound changes that have occurred since the approval of Law No.
    3/93, of 24 June - Investment Law ... This Law establishes the
    legal regime, bases and general principles applicable to the
    carrying out of private investments in the Republic of
    Mozambique...\". This iteration did NOT independently find, on
    APIEX's own site or elsewhere, the separate Council-of-Ministers
    instrument Article 22 §3/§4 of this Law itself delegates the
    \"competent entity\" designation to -- an honest gap, not filled by
    assuming APIEX is that instrument's own named entity (see
    `marketentry.facts` for the full discussion).
  - **Business licensing/registration (BAU)**: this iteration found, on
    BAU's own `https://www.bau.gov.mz/legislacao/` page (fetched
    directly, own link text, verbatim): \"Decreto n.º 29/2023, de 24 de
    Maio, que cria o Instituto Público, Balcões de Atendimento Único,
    abreviadamente designado por BAU\" -- independently corroborated on
    BAU's own `https://www.bau.gov.mz/institucuinal/` (\"Sobre Nós\")
    page, own primary text, verbatim: \"O Instituto Público Balcões de
    Atendimento Único (BAU, IP) é uma instituição pública de âmbito
    nacional, criada pelo Decreto n.º 29/2023, e tem por objecto a
    melhoria da prestação dos serviços públicos integrados ao cidadão e
    às empresas, através da simplificação e harmonização de
    procedimentos administrativos em plataformas físicas ou
    digitais/virtuais.\" This iteration did NOT independently confirm a
    specific Código Comercial (companies-law) citation for Mozambique
    this session (Mozambique is NOT an OHADA member state, unlike the
    `-ben`/`-caf`/`-cog`/`-gin`/`-gnb` siblings, so their own AUSCGIE
    citation does not apply here and this catalog does not borrow it)
    -- BAU's own business-licensing/registration regime is included
    instead as the general-compliance entry this iteration COULD
    confirm, an honest substitution stated here explicitly, not a
    silent one.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"MOZ"
   [{:statute/id "moz.lei-do-trabalho-2023"
     :statute/title "Lei do Trabalho da República de Moçambique"
     :statute/jurisdiction "MOZ"
     :statute/kind :law
     :statute/law-number "Lei n.º 13/2023, de 25 de Agosto (revoga a Lei n.º 23/2007, de 1 de Agosto -- own text, Boletim da República I Série, Número 165, 2.º Suplemento, 25 de Agosto de 2023, own final article: 'É revogada a Lei n.º 23/2007, de 1de Agosto')"
     :statute/url "https://www.inss.gov.mz/legislacao/"
     :statute/url-provenance :official-social-security-institute-inss
     :statute/enacted-date "2023-08-25"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor :employment}}
    {:statute/id "moz.lei-de-investimentos-2023"
     :statute/title "Lei de Investimento Privado da República de Moçambique"
     :statute/jurisdiction "MOZ"
     :statute/kind :law
     :statute/law-number "Lei n.º 8/2023, de 9 de Junho (revoga a Lei n.º 3/93, de 24 de Junho -- own text, own preamble: 'considering the profound changes that have occurred since the approval of Law No. 3/93, of 24 June - Investment Law')"
     :statute/url "https://apiex.gov.mz/legislation/"
     :statute/url-provenance :official-investment-agency-apiex-wayback-verified
     :statute/enacted-date "2023-06-09"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:investment}}
    {:statute/id "moz.bau-decreto-29-2023"
     :statute/title "Decreto que cria o Instituto Público Balcões de Atendimento Único (BAU, IP)"
     :statute/jurisdiction "MOZ"
     :statute/kind :decree
     :statute/law-number "Decreto n.º 29/2023, de 24 de Maio (per BAU's own legislação page and own 'Sobre Nós' page, both fetched directly this session)"
     :statute/url "https://www.bau.gov.mz/legislacao/"
     :statute/url-provenance :official-one-stop-shop-bau
     :statute/enacted-date "2023-05-24"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:business-registration :licensing}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-moz statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "MOZ")) " MOZ statutes seeded with an "
                 "official citation. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :investment)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))
