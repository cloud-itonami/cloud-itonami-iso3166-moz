# Operator guide — MOZ (Mozambique)

## Regulatory grounding

- **Legal basis**: Regulamento de Contratação de Empreitada de Obras
  Públicas, Fornecimento de Bens e Prestação de Serviços ao Estado,
  aprovado pelo Decreto n.º 79/2022, de 30 de Dezembro.
  `https://www.ufsa.gov.mz/Docs/BR_252_I_SERIE_7o%20SUPLEMENTO_2022.pdf`
  (Boletim da República, own primary text). This decree revoked the
  prior Decreto n.º 5/2016 (which had created UFSA) and two intervening
  amendment decrees.
- **Procurement regulator**: Unidade Funcional de Supervisão das
  Aquisições (UFSA), Ministério da Economia e Finanças.
  `https://www.ufsa.gov.mz/`
- **Cadastro Único**: UFSA maintains a Cadastro Único de Empreiteiros de
  Obras Públicas, Fornecedores de Bens e Prestadores de Serviços ao
  Estado (Regulamento Artigos 43-45) — TWO distinct registries:
  eligible suppliers/contractors, and Impedidos (barred) suppliers/
  contractors. Registration requires qualificação jurídica,
  económico-financeira, técnica, and regularidade fiscal documents
  (Artigo 44). The registry is open to public consultation free of
  charge (Artigo 45).
- **No verified national e-procurement transactional portal**: UFSA's
  own site publishes bid notices/adjudications/cancellations directly
  as ordinary pages (`concursos.php`/`adjudicacoes.php`/
  `cancelamentos.php`/`ajustes_directos.php`) rather than through a
  dedicated self-service e-tendering portal.

## Business registration / licensing

- **BAU (Balcão de Atendimento Único)** — the one-stop-shop public
  institute for business licensing and registration, created by
  Decreto n.º 29/2023, de 24 de Maio. `https://www.bau.gov.mz/`
- **NUIT (Número Único de Identificação Tributária)** — administered
  by the Autoridade Tributária de Moçambique (AT).
  `https://www.at.gov.mz/` ; issuance portal:
  `https://nuit.at.gov.mz/nuit`
- **APIEX (Agência para a Promoção de Investimento e Exportações)** —
  administers investment facilitation under Lei n.º 8/2023, de 9 de
  Junho (Lei de Investimento Privado). No specific named "Conservatória
  do Registo das Entidades Legais (CREL)" citation was independently
  confirmed this session — this actor tracks only what IS confirmed
  (BAU, NUIT, Cadastro Único, APIEX) as required evidence.

## Using the actor

1. `:engagement/intake` — record/patch an engagement (operator name,
   fee terms, jurisdiction). Auto-commits when clean at phase 3.
2. `:jurisdiction/assess` — the actor proposes the MOZ required-
   evidence checklist above, citing the Regulamento/UFSA source.
   ALWAYS requires human approval, even when clean.
3. `:filing/draft` — the actor proposes drafting a filing package.
   ALWAYS requires human approval; HELD if evidence is incomplete or
   the engagement was already drafted.
4. `:filing/submit` — the actor proposes submitting the filing.
   ALWAYS requires human approval; HARD-HELD (unoverridable) if:
   - the engagement requires Cadastro Único registration but doesn't
     have it on file (`:requires-cadastro-unico? true` +
     `:has-cadastro-unico? false`);
   - the engagement IS on UFSA's own Cadastro Único Impedidos
     (debarment) list (`:on-impedidos-list? true`), regardless of
     whether it also has ordinary registration;
   - the claimed engagement fee doesn't equal
     `base-fee + monthly-rate × monitoring-months`;
   - the engagement was already submitted.

## Demo engagements

`src/marketentry/store.cljc`'s `demo-data` seeds five engagements:

| id     | scenario                                                        |
|--------|------------------------------------------------------------------|
| eng-1  | clean — commits end-to-end                                        |
| eng-2  | requires Cadastro Único but lacks it — HARD hold                  |
| eng-3  | has Cadastro Único but is on UFSA's Impedidos list — HARD hold     |
| eng-4  | claimed fee doesn't match recomputed fee — HARD hold               |
| eng-5  | doesn't itself require Cadastro Único — commits                    |
