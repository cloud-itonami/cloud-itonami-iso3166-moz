# cloud-itonami-iso3166-moz

**MOZ**: Mozambique.

- UFSA (Unidade Funcional de Supervisão das Aquisições) public-procurement
  compliance -- Regulamento de Contratação de Empreitada de Obras Públicas,
  Fornecimento de Bens e Prestação de Serviços ao Estado, aprovado pelo
  Decreto n.º 79/2022, de 30 de Dezembro
- Cadastro Único de Empreiteiros de Obras Públicas, Fornecedores de Bens e
  Prestadores de Serviços ao Estado (UFSA, Artigos 43-45) -- this vertical's
  flagship governor check
- BAU (Balcão de Atendimento Único) business licensing/registration +
  NUIT (Autoridade Tributária de Moçambique) + APIEX private-investment
  registration

AGPL-3.0-or-later.

## Market-entry / statute catalogs

Governed public-sector market-entry compliance actor, same architecture as
`cloud-itonami-iso3166-gnb` (Guinea-Bissau, a fellow Lusophone jurisdiction
-- the closest architectural match) and `cloud-itonami-iso3166-caf`:

- `src/marketentry/{facts,governor,phase,sim,operation,registry,store,
  marketentryllm}.cljc` -- the actor. `facts.cljc` cites UFSA's own
  government website (`ufsa.gov.mz`) and the Boletim da República (Decreto
  n.º 79/2022, de 30 de Dezembro) for the current Regulamento, BAU's own
  site (`bau.gov.mz`, Decreto n.º 29/2023) for business licensing/
  registration, and the Autoridade Tributária de Moçambique (`at.gov.mz`)
  for NUIT. `governor.cljc`'s flagship check (`cadastro-unico-missing`)
  independently verifies Cadastro Único registration AND absence from
  UFSA's own Impedidos (debarment) registry -- a genuine two-condition
  test grounded directly in Artigo 43 of the Regulamento's own text (UFSA
  maintains TWO distinct registries: eligible suppliers AND debarred
  suppliers).
- `src/statute/facts.cljk` -- general-law catalog: Lei n.º 13/2023, de 25
  de Agosto (Lei do Trabalho, which this iteration confirmed directly from
  its own primary text revokes the prior Lei n.º 23/2007), Lei n.º 8/2023,
  de 9 de Junho (Lei de Investimento Privado, revokes Lei n.º 3/93), and
  Decreto n.º 29/2023, de 24 de Maio (creates BAU). Mozambique is NOT an
  OHADA member state (unlike several `-ben`/`-caf`/`-cog`/`-gin`/`-gnb`
  siblings), so this catalog does not borrow their AUSCGIE company-law
  citation -- this iteration could not independently confirm a specific
  Código Comercial citation for Mozambique this session; BAU's own
  business-licensing/registration decree is included instead as an
  honestly-substituted general-compliance entry.

Every citation is curl/pdftotext-verified against an official source
(`ufsa.gov.mz`, `bau.gov.mz`, `at.gov.mz`, `inss.gov.mz`) or, where the
official site itself blocked automated fetches this session
(`apiex.gov.mz` returned its own "BunkerWeb"/"Nothing to see here"
bot-detection page to every attempt, including WebFetch, which separately
failed on a self-signed-certificate error), the Wayback Machine's most
recent snapshot of that SAME official site's own content (not a
third-party paraphrase) -- see `src/marketentry/facts.cljk`'s namespace
docstring for the full research trail, including facts this iteration
could NOT verify (a Conservatória do Registo das Entidades Legais (CREL)
citation; the specific decree establishing the NUIT regime itself; the
specific instrument naming APIEX as Lei n.º 8/2023's own Art. 22 §3/§4
"competent entity") and honestly left out rather than invented.

```
clojure -M:dev:test
```

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Mozambique:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.
