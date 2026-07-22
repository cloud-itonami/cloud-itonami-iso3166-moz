(ns marketentry.facts
  "Mozambique (MOZ) market-entry / public-procurement catalog.

  Every fact under \"MOZ\" below is grounded ONLY in primary sources this
  iteration actually curl/pdftotext-fetched and read directly this
  session (2026-07-22/23) -- UFSA's own government website
  (`ufsa.gov.mz`), the Boletim da República (Mozambique's own official
  gazette) PDF that UFSA's own site links to, APIEX's own website (via
  a recent Wayback Machine snapshot, since `apiex.gov.mz` itself was
  blocked by its own WAF/bot-detection this session -- see below), and
  BAU's own government website (`bau.gov.mz`). Nothing below is
  paraphrased from training-data memory; where this iteration could not
  independently confirm a specific fact, that gap is stated explicitly
  rather than filled with a plausible-sounding invention (the same
  discipline `cloud-itonami-iso3166-gnb`'s and `cloud-itonami-iso3166-
  caf`'s own catalogs use).

  Sources actually fetched and read this session:

  - **Public procurement**: `https://www.ufsa.gov.mz/criacao_ufsa.php`
    (fetched directly, own primary text, verbatim): \"Pelo Regulamento
    de Contratação de Empreitada de Obras Públicas, Fornecimento de Bens
    e Prestação de Serviços ao Estado, aprovado pelo Decreto n.º
    5/2016, de 8 de Março foi criada a Unidade Funcional de Supervisão
    das Aquisições [UFSA]... Pelo Diploma Ministerial n.º 141/2006, de
    05 de Setembro, a Unidade Funcional de Supervisão das Aquisições...
    foi estabelecida na Direcção Nacional do Património do Estado.\"
    This SAME page also names the ministry directly: \"Ministério da
    Economia e Finanças\". UFSA's OWN announcement page
    (`https://www.ufsa.gov.mz/divnovoreg.php`, fetched directly, own
    primary text, verbatim) confirms the Regulamento was subsequently
    REVISED: \"A Direcção Nacional do Património do Estado está a
    promover acções de divulgação do Regulamento, aprovado pelo Decreto
    n.º 79/2022, de 30 de Dezembro, com objectivo de partilhar as
    alterações resultantes da revisão do Regulamento anterior...\". This
    iteration independently downloaded the Boletim da República PDF
    itself (`https://www.ufsa.gov.mz/Docs/BR_252_I_SERIE_7o%20SUPLEMENTO_
    2022.pdf`, I Série, Número 252, 7.º Suplemento, 30 de Dezembro de
    2022 -- a real, machine-readable, government-published gazette, NOT
    a scanned image; `pdftotext` succeeded) and read the decree's own
    text directly, confirming: \"Artigo 1. É aprovado o Regulamento de
    Contratação de Empreitada de Obras Públicas, Fornecimento de Bens e
    Prestação de Serviços ao Estado, em anexo ao presente Decreto...\"
    and \"Art. 4. São revogados o Decreto n.º 5/2016, de 8 de Março; o
    Decreto n.º 71/2020, de 13 de Agosto; o Decreto n.º 53/2021, de 29
    de Julho; e o Decreto n.º 89/2021, de 29 de Outubro.\" -- so Decreto
    n.º 79/2022 is CONFIRMED as the current, in-force Regulamento (this
    catalog cites it as `:legal-basis`, not the 2016 predecessor the
    `criacao_ufsa.php` institutional-history page still describes --
    that page's history text is accurate as HISTORY but is not itself
    up to date about which decree is CURRENTLY in force; this iteration
    does not conflate the two, mirroring the same
    correction-history discipline `cloud-itonami-iso3166-gnb`'s own
    `facts.cljc` uses for its own ARMP/ARCP correction).
  - **UFSA's own competence** is separately grounded in
    `https://www.ufsa.gov.mz/contextualizacao_sup.php` (fetched
    directly, own primary text, verbatim): \"«Compete à UFSA, coordenar
    e supervisar todas as actividades relacionadas com a contratação
    pública, gestão do sistema nacional centralizado de dados e
    informações e programas de capacitação em matéria de contratação,
    conforme estabelecido na alínea x) do artigo 3 do Regulamento de
    Empreitada de Obras Públicas, Fornecimento de Bens e Prestação de
    Serviço ao Estado.» (Art. 4 do Diploma Ministerial n. 141/2006).\"
  - **Cadastro Único (this catalog's flagship mechanism -- see
    `marketentry.governor`)**: `https://www.ufsa.gov.mz/cadastro_unico.php`
    (fetched directly, own primary text, verbatim): \"Nos termos da
    alínea a) e b) do artigo 43 do Regulamento, aprovado pelo Decreto
    n.º 79/2022, de 30 de Dezembro, compete à UFSA, criar e manter
    actualizado o Cadastro Único de Empreiteiros de Obras Públicas,
    Fornecedores de Bens e Prestadores de Serviços ao Estado.\" This
    iteration independently confirmed the SAME mechanism directly in the
    Decreto's own primary text (the same Boletim da República PDF
    above), Artigo 43 (Constituição de Cadastro Único): the Cadastro
    Único has TWO distinct sub-registries UFSA must create and maintain
    -- (a) contractors/suppliers ELIGIBLE to participate in State
    contracting, and (b) contractors/suppliers Cadastro Único
    ...IMPEDIDOS de participar (barred/debarred from participating) --
    and Artigo 44 (Inscrição, Manutenção e Actualização do Cadastro
    Único), own text, verbatim: \"1. A inscrição no Cadastro Único...
    depende da apresentação pelo interessado dos respectivos documentos
    de qualificação jurídica, económico-finançeira técnica, e
    regularidade fiscal, segurança social e estatística, previstos no
    presente Regulamento.\" -- i.e. fiscal regularity (which in practice
    means a valid NUIT, see below) is one of the Cadastro Único's own
    qualification requirements, not a wholly separate regime. UFSA's own
    site ALSO independently confirms a live, real \"Fornecedores
    Impedidos\" debarment-list PDF exists and is downloadable
    (`https://www.ufsa.gov.mz/Docs/Impedidas24.pdf`, confirmed reachable
    this session, `Content-Type: application/pdf`, dated 2024 per its own
    filename) -- this catalog does not merely take UFSA's word for the
    debarment mechanism's existence; it independently confirmed the
    artifact is live.
  - **Business registration / licensing single-window**:
    `https://www.bau.gov.mz/institucuinal/` (fetched directly, own
    primary text, verbatim): \"O Instituto Público Balcões de
    Atendimento Único (BAU, IP) é uma instituição pública de âmbito
    nacional, criada pelo Decreto n.º 29/2023, e tem por objecto a
    melhoria da prestação dos serviços públicos integrados ao cidadão e
    às empresas...\" -- the exact date is confirmed on BAU's own
    `https://www.bau.gov.mz/legislacao/` page (fetched directly, own
    link text, verbatim): \"Decreto n.º 29/2023, de 24 de Maio, que cria
    o Instituto Público, Balcões de Atendimento Único, abreviadamente
    designado por BAU\". The SAME `legislacao/` page also lists BAU's
    own economic-activity licensing decrees (Decreto n.º 34/2013 de 2 de
    Agosto -- commercial licensing; Decreto n.º 22/2014 de 16 de Maio --
    industrial licensing; Decreto n.º 27/2023 de 23 de Maio -- Mera
    Comunicação Prévia; Decreto n.º 39/2017 de 28 de Julho -- Licenciamento
    Simplificado), and BAU's own licensing page
    (`https://www.bau.gov.mz/licenciamento-eregisto-de-actividades-
    economicas-2/`, fetched directly) confirms NUIT is one of the
    documents required for at least one of its registration services
    (Registo do Operador de Comércio Externo), own text, verbatim:
    \"Cópia do Alvará; Número Único de Identificação Tributária (NUIT);
    Preenchimento do formulário; Taxa: valor correspondente a 25%.\".
    This iteration did NOT find, on BAU's own site or elsewhere, an
    independently-verifiable Mozambican \"Conservatória do Registo das
    Entidades Legais (CREL)\" reachable via a live primary source --
    `mjacr.gov.mz`/`crel.gov.mz` and similar Ministry-of-Justice domain
    guesses did not resolve this session, and neither `bau.gov.mz` page
    fetched mentions \"CREL\"/\"Conservatória\" by name anywhere in its
    own text. Rather than invent that this named body administers legal-
    entity registration, this catalog names only what IS confirmed: BAU
    (business licensing/registration single-window, per its own
    Decreto n.º 29/2023) -- the CREL gap is stated here honestly, not
    silently dropped.
  - **Tax / NUIT**: `https://www.at.gov.mz/` (fetched directly, own
    `<title>` tag, verbatim: \"Home - Autoridade Tributária de
    Moçambique\") is the tax authority (AT); its own homepage links
    directly to `https://nuit.at.gov.mz/nuit` labelled, in AT's own
    words, \"Sistema de Impressão de Cartas de NUIT\" (NUIT-card
    printing system) -- confirming both AT's identity and that NUIT
    registration/issuance is a live AT-administered system. This
    iteration did NOT independently confirm the specific decree/
    regulation number that FIRST established the NUIT regime itself (as
    distinct from AT's own creation/amendment law, `Lei n.º 19/2009, de
    10 de Setembro`, which this iteration found on `at.gov.mz`'s own
    `/por/Legislacao/AT/Lei` page but which that page's own link text
    describes only as \"Alterações AT\", i.e. an amendment, not NUIT's
    own founding instrument) -- an honest gap, not filled by guessing a
    decree number.
  - **Private investment**: `apiex.gov.mz` itself returned a
    \"BunkerWeb\"/\"Nothing to see here\" bot-detection block page to
    every fetch attempt this session (including via WebFetch, which
    separately failed on a self-signed-certificate error) -- so this
    iteration instead used the Wayback Machine's most recent snapshot of
    APIEX's own site (`web.archive.org`, snapshot timestamped
    2026-01-19/2026-01-09, i.e. APIEX's own real, recently-crawled
    content, not a third-party paraphrase) to independently confirm
    APIEX's own mandate (own `<title>` text, verbatim): \"APIEX
    Moçambique – Investment and Export Promotion Agency, abbreviated as
    APIEX, I.P. is a public institution, endowed with legal personality,
    with administrative, financial and patrimonial autonomy, is
    supervised by the Minister in charge of the area of Industry and
    Trade, tutelage created through Decree no. 60/2016 of 12 December.\"
    -- note this is a DIFFERENT supervising ministry (Indústria e
    Comércio) than UFSA's (Economia e Finanças); this catalog does not
    conflate the two. This iteration then independently downloaded, via
    the same Wayback Machine snapshot, the exact PDF APIEX's own
    `/legislation/` page links to and labels \"Law n.º 8/2023 Private
    Investment Law\", and read its own primary text directly
    (`pdftotext`, a real machine-readable PDF, not scanned), confirming,
    verbatim: \"Law No.8/2023 of 9 June ... considering the profound
    changes that have occurred since the approval of Law No. 3/93, of 24
    June - Investment Law ... This Law establishes the legal regime,
    bases and general principles applicable to the carrying out of
    private investments in the Republic of Mozambique which are
    eligible for the enjoyment of tax and non-tax incentives and
    guarantees.\" Article 22 of this Law's own text (\"Investment
    regimes\") establishes a \"Mere registration regime\" (simple
    submission of an investment proposal) for most projects and an
    \"authorisation regime\" for large-scale/high-impact projects, but
    LEAVES THE DESIGNATION of \"the entity that... coordinates the
    process of authorisation of private investments\" (Art. 22 §3) to a
    separate Council-of-Ministers decision -- this iteration did NOT
    independently find that separate designating instrument's own text,
    so `:owner-authority` below is grounded in APIEX's own self-
    description (a public institution whose own site hosts this exact
    Law as its own \"Legislation\") rather than in the base Law's own
    text naming APIEX by name, an honest distinction this catalog does
    not blur.
  - **Labour law** (grounds the `statute.facts` catalog's labour entry,
    referenced here for cross-catalog context only):
    `https://www.inss.gov.mz/legislacao/` (fetched directly, own
    primary text, verbatim): \"Lei 23/2007 de 1 de Agosto – Lei do
    Trabalho\" AND, separately, \"Nova Lei de Trabalho nº 13/2023 de 25
    de Agosto\" -- both hosted as real, downloadable PDFs on INSS's own
    site. This iteration downloaded the 2023 one directly and read its
    own primary text (the Boletim da República itself, I Série, Número
    165, 2.º Suplemento, 25 de Agosto de 2023), confirming, verbatim:
    \"Lei n.º 13/2023: ... Lei do Trabalho e revoga a Lei n.º 23/2007, de
    1 de Agosto.\" and, in its own final article: \"É revogada a Lei
    n.º 23/2007, de 1de Agosto.\" -- so Lei n.º 13/2023 is CONFIRMED as
    the CURRENT Lei do Trabalho (see `statute.facts` for the full
    citation).

  What this catalog still does NOT claim: no independently-verified
  \"Conservatória do Registo das Entidades Legais (CREL)\" citation (see
  above); no independently-verified specific decree establishing the
  NUIT regime itself (only AT's identity and its live NUIT portal are
  confirmed); no independently-verified instrument naming APIEX, by
  name, as the Law n.º 8/2023 Art. 22 §3/§4 \"competent entity\" (only
  APIEX's own self-description and its own hosting of that Law are
  confirmed). None of these is invented to make the catalog look more
  complete -- the same honest-gap discipline `cloud-itonami-iso3166-
  gnb`'s and `cloud-itonami-iso3166-caf`'s own catalogs use.")

(def catalog
  "iso3 -> requirement map. `:required-evidence` mirrors the generic
  intake/portal-registration/filing evidence set; `:legal-basis` /
  `:owner-authority` / `:provenance` are the citation the governor
  requires before any `:jurisdiction/assess` proposal can commit.
  `:cadastro-unico-owner-authority` / `:cadastro-unico-legal-basis` /
  `:cadastro-unico-provenance` ground this vertical's flagship governor
  check (`cadastro-unico-missing-violations` in `marketentry.governor`,
  `cadastro-unico-satisfied?` in `marketentry.registry`)."
  {"MOZ" {:name "Mozambique"
          :owner-authority "Unidade Funcional de Supervisão das Aquisições (UFSA) -- established within the Direcção Nacional do Património do Estado, Ministério da Economia e Finanças, per Diploma Ministerial n.º 141/2006, de 5 de Setembro (Art. 4, referencing alínea x) do artigo 3 do Regulamento)"
          :legal-basis "Regulamento de Contratação de Empreitada de Obras Públicas, Fornecimento de Bens e Prestação de Serviços ao Estado, aprovado pelo Decreto n.º 79/2022, de 30 de Dezembro (Conselho de Ministros; Artigo 1 approves the Regulamento in annex; Art. 4 revokes the Decreto n.º 5/2016, de 8 de Março that originally created UFSA, plus the Decreto n.º 71/2020, Decreto n.º 53/2021 and Decreto n.º 89/2021 that had amended it) -- this catalog cites the CURRENT decree, not the superseded 2016 one still described by UFSA's own institutional-history page (see namespace docstring)"
          :national-spec "Cadastro Único de Empreiteiros de Obras Públicas, Fornecedores de Bens e Prestadores de Serviços ao Estado (Art. 43-45 of the Regulamento) -- a UFSA-maintained registry, permanently open to public consultation free of any fee (Art. 45); no dedicated national e-procurement TRANSACTIONAL portal beyond UFSA's own bid-notice website was found (concursos.php/adjudicacoes.php/cancelamentos.php/ajustes_directos.php on ufsa.gov.mz)"
          :provenance "https://www.ufsa.gov.mz/criacao_ufsa.php ; https://www.ufsa.gov.mz/divnovoreg.php ; https://www.ufsa.gov.mz/contextualizacao_sup.php ; https://www.ufsa.gov.mz/Docs/BR_252_I_SERIE_7o%20SUPLEMENTO_2022.pdf (Boletim da República I Série, Número 252, 7.º Suplemento, 30 de Dezembro de 2022 -- Decreto n.º 79/2022 own primary text)"
          :required-evidence ["BAU (Balcão de Atendimento Único) business licensing/registration record (Decreto n.º 29/2023, de 24 de Maio)"
                               "NUIT (Número Único de Identificação Tributária) record (Autoridade Tributária de Moçambique)"
                               "Cadastro Único de Empreiteiros de Obras Públicas, Fornecedores de Bens e Prestadores de Serviços ao Estado registration record (UFSA, Art. 43-45 of the Regulamento, Decreto n.º 79/2022)"
                               "APIEX private-investment registration record (Lei n.º 8/2023, mere-registration or authorisation regime), when the engagement is a private-investment project"]
          :cadastro-unico-owner-authority "Unidade Funcional de Supervisão das Aquisições (UFSA)"
          :cadastro-unico-legal-basis "Regulamento (Decreto n.º 79/2022, de 30 de Dezembro), Artigo 43 (Constituição de Cadastro Único): UFSA creates and maintains BOTH (a) a registry of contractors/suppliers/service-providers ELIGIBLE to participate in State contracting, and (b) a registry of contractors/suppliers/service-providers IMPEDIDOS (barred) from participating; Artigo 44 (Inscrição, Manutenção e Actualização): registration and its maintenance depend on the interessado's own qualificação jurídica, económico-financeira, técnica, and regularidade fiscal/segurança social/estatística documents; Artigo 45 (Acesso): the registry is open to public consultation free of charge"
          :cadastro-unico-provenance "https://www.ufsa.gov.mz/cadastro_unico.php ; https://www.ufsa.gov.mz/Docs/BR_252_I_SERIE_7o%20SUPLEMENTO_2022.pdf (Artigos 43-45, own primary text) ; https://www.ufsa.gov.mz/Docs/Impedidas24.pdf (confirmed reachable, application/pdf, this session)"}
   ;; -- reference jurisdictions, reused verbatim from already-merged
   ;; sibling repos (cloud-itonami-iso3166-caf), not new claims --
   "USA" {:name "United States"
          :owner-authority "U.S. General Services Administration (GSA) / SAM.gov"
          :legal-basis "Federal Acquisition Regulation (FAR); System for Award Management"
          :national-spec "SAM.gov entity registration + NAICS self-certification"
          :provenance "https://sam.gov/"
          :required-evidence ["EIN record"
                               "SAM.gov registration record"
                               "State business registration record"
                               "Authorized-representative record"]}
   "DEU" {:name "Germany"
          :owner-authority "Beschaffungsamt des BMI / e-Vergabe platforms"
          :legal-basis "Gesetz gegen Wettbewerbsbeschränkungen (GWB) / VgV"
          :national-spec "e-Vergabe supplier registration under EU procurement directives"
          :provenance "https://www.evergabe-online.de/"
          :required-evidence ["Handelsregister extract"
                               "e-Vergabe registration record"
                               "USt-IdNr record"
                               "Authorized-representative record"]}})

(defn spec-basis
  "The jurisdiction's requirement map, or nil -- nil means NO spec-basis,
  and the governor must hold any proposal that tries to assess or file
  on it."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report: how many of the requested jurisdictions actually
  have a spec-basis entry. Never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-moz R0: " (count catalog)
                 " jurisdictions seeded with an official spec-basis. "
                 "This is a starting catalog for market-entry navigation, "
                 "not a survey of all ~194 jurisdictions -- extend "
                 "`marketentry.facts/catalog`, never fabricate a "
                 "jurisdiction's requirements.")})))

(defn required-evidence-satisfied?
  "Does `submitted` (a set/coll of evidence keywords or strings) satisfy
  every evidence item listed for `iso3`? Missing spec-basis -> never
  satisfied."
  [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (let [need (count required-evidence)
          have (count (filter (set submitted) required-evidence))]
      (= need have))))

(defn evidence-checklist [iso3]
  (:required-evidence (spec-basis iso3) []))

(defn cadastro-unico-spec-basis
  "The jurisdiction's Cadastro Único (contractor/supplier registry)
  regime, or nil. For MOZ this is real and current -- the flagship
  check this vertical adds is grounded here (Regulamento, Decreto n.º
  79/2022, Artigos 43-45)."
  [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:cadastro-unico-owner-authority sb)
      (select-keys sb [:cadastro-unico-owner-authority
                       :cadastro-unico-legal-basis
                       :cadastro-unico-provenance]))))
