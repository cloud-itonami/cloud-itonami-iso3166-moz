# Business model — MOZ (Mozambique)

Independent public-sector market-entry and procurement-compliance
service for Mozambique, run by a supervised MarketEntry-LLM advisor
sealed behind an independent **Market-Entry Compliance Governor** —
the same actor family as `cloud-itonami-iso3166-gnb` (Guinea-Bissau,
the closest architectural match: a fellow Lusophone jurisdiction) and
`cloud-itonami-iso3166-caf`.

## Grounding

This iteration's research (2026-07-22/23) confirmed the following
directly, by curl/pdftotext-fetching and reading each primary source
itself this session:

- **Procurement legal basis**: the Regulamento de Contratação de
  Empreitada de Obras Públicas, Fornecimento de Bens e Prestação de
  Serviços ao Estado, aprovado pelo Decreto n.º 79/2022, de 30 de
  Dezembro (Conselho de Ministros) — confirmed both from UFSA's own
  government website (`ufsa.gov.mz`) AND by downloading and reading the
  decree's own text directly from the Boletim da República (Mozambique's
  own official gazette), I Série, Número 252, 7.º Suplemento. This
  Decreto revokes the prior Decreto n.º 5/2016 (which had originally
  created UFSA) plus two intervening amendment decrees (71/2020,
  53/2021, 89/2021) — this catalog cites the CURRENT decree, not the
  superseded 2016 one UFSA's own institutional-history page still
  describes.
- **Procurement regulator**: Unidade Funcional de Supervisão das
  Aquisições (UFSA), established within the Direcção Nacional do
  Património do Estado, Ministério da Economia e Finanças, per Diploma
  Ministerial n.º 141/2006.
- **Flagship mechanism — Cadastro Único**: UFSA maintains a Cadastro
  Único de Empreiteiros de Obras Públicas, Fornecedores de Bens e
  Prestadores de Serviços ao Estado (Regulamento Artigos 43-45) — this
  iteration confirmed, in the decree's OWN text, that this is actually
  TWO distinct registries: (a) contractors/suppliers ELIGIBLE to
  participate in State contracting, and (b) contractors/suppliers
  Impedidos (barred) from participating. This iteration also
  independently confirmed a real, live "Fornecedores Impedidos" PDF
  exists on UFSA's own site (not merely claimed).
- **Business licensing/registration**: BAU (Balcão de Atendimento
  Único), a one-stop-shop public institute created by Decreto n.º
  29/2023, de 24 de Maio — confirmed on BAU's own government website
  (`bau.gov.mz`).
- **Tax registration**: NUIT (Número Único de Identificação Tributária),
  administered by the Autoridade Tributária de Moçambique (AT) — this
  iteration confirmed AT's own identity and its live NUIT-issuance
  portal (`nuit.at.gov.mz`) but did NOT independently confirm the
  specific decree that first established the NUIT regime itself (an
  honest gap).
- **Private investment**: Lei n.º 8/2023, de 9 de Junho (Lei de
  Investimento Privado), which revokes the prior Lei n.º 3/93 — this
  iteration confirmed this by downloading and reading the Law's own
  primary text via a Wayback Machine snapshot of APIEX's own site
  (`apiex.gov.mz` itself blocked every direct fetch attempt this
  session with its own bot-detection page).
- Did **NOT** find an independently-verifiable "Conservatória do Registo
  das Entidades Legais (CREL)" citation — Ministry-of-Justice domain
  guesses did not resolve this session, and BAU's own site does not
  mention CREL anywhere in its own text.
- Did **NOT** find a specific decree establishing the NUIT regime
  itself, distinct from AT's own creation/amendment law.
- Did **NOT** find the specific Council-of-Ministers instrument that Lei
  n.º 8/2023's own Art. 22 §3/§4 delegates the "competent entity"
  designation to — this catalog cites APIEX's own self-description
  rather than assert the base Law names APIEX by name.

See `src/marketentry/facts.cljk` and `src/statute/facts.cljk` for the
full catalog entries and their docstrings, which are the single source
of truth for every regulatory claim this actor makes.

## Service

A market-entry operator engages this actor to:

1. **Intake** an engagement (company/operator details).
2. **Assess** the jurisdiction — the actor cites the exact spec-basis
   above and returns the required-evidence checklist. A jurisdiction
   with no catalog entry gets NO fabricated checklist (HARD hold).
3. **Draft** a filing package once evidence is complete (always
   human-approved).
4. **Submit** the filing once Cadastro Único registration (and absence
   from UFSA's Impedidos debarment list) and the independently-
   recomputed engagement fee both check out (always human-approved,
   always HARD-held if either is wrong).

## Engagement fee

Base fee + monthly monitoring rate × monitoring months. The Governor
independently recomputes this on every `:filing/submit` and HARD-holds
on any mismatch with the claimed fee — never trusts the LLM's own
arithmetic.

## Trust Controls

- Any actual portal registration or filing submission requires
  Market-Entry Compliance Governor clearance and always escalates to
  human sign-off — no phase, however mature, allows
  `:filing/draft`/`:filing/submit` to auto-commit.
- A false or fabricated regulatory-requirement claim is a HARD hold —
  the Governor's `spec-basis` check rejects any proposal that doesn't
  cite `marketentry.facts`.
- A missing Cadastro Único registration, OR active membership on
  UFSA's own Impedidos (debarment) registry, on an engagement that
  requires it, is a HARD hold, unoverridable by a human approver.
- A mismatched engagement fee is a HARD hold, unoverridable by a
  human approver.
- Double-drafting or double-submitting the same engagement is a HARD
  hold.

## Precedent

This actor follows the same architecture as `cloud-itonami-iso3166-gnb`
and `cloud-itonami-iso3166-caf`, and ships SIX governor checks (not a
richer sibling's seven), because the dossier does not ground a distinct
NUIT-establishing-decree check separate from ordinary Cadastro Único
registration.
