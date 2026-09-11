(ns culture.facts
  "Country-level regional-culture catalog for Mozambique (MOZ) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"MOZ"
   [{:culture/id "moz.dish.matapa"
     :culture/name "Matapa"
     :culture/country "MOZ"
     :culture/kind :dish
     :culture/summary "Typical Mozambican stew of young cassava, pumpkin or spinach leaves, ground and cooked with peanuts, tomatoes, garlic, onion and coconut milk, often served over rice."
     :culture/url "https://en.wikipedia.org/wiki/Matapa"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "moz.dish.xima"
     :culture/name "Xima"
     :culture/country "MOZ"
     :culture/kind :dish
     :culture/summary "Mozambican name for a maize-based staple porridge known regionally across Africa as ugali, ugali's Names section lists \"Xima - Mozambique\" among local terms."
     :culture/url "https://en.wikipedia.org/wiki/Ugali"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "moz.dish.frango-a-zambeziana"
     :culture/name "Frango à Zambeziana"
     :culture/country "MOZ"
     :culture/kind :dish
     :culture/summary "Spicy Mozambican chicken dish prepared with coconut milk, per the Mozambican cuisine article."
     :culture/url "https://en.wikipedia.org/wiki/Mozambican_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "moz.product.piri-piri"
     :culture/name "Piri piri"
     :culture/country "MOZ"
     :culture/kind :product
     :culture/summary "Capsicum frutescens cultivar whose name traces to the Ronga language of southern Mozambique, where Portuguese explorers developed the pepper; the piri-piri spelling is used in Portuguese-speaking countries including Mozambique."
     :culture/url "https://en.wikipedia.org/wiki/Piri_piri"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "moz.beverage.muchekele"
     :culture/name "Muchekele"
     :culture/country "MOZ"
     :culture/kind :beverage
     :culture/summary "Cashew apple liquor distilled in Mozambique, the Mozambican variant of fermenting and distilling cashew apples into a traditional alcoholic beverage."
     :culture/url "https://en.wikipedia.org/wiki/Cashew"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "moz.craft.makonde-art"
     :culture/name "Makonde art"
     :culture/country "MOZ"
     :culture/kind :craft
     :culture/summary "Woodcarving and sculpture tradition of the Makonde people of northern Mozambique and southern Tanzania; the first documented exhibition was held in Mozambique in the 1930s."
     :culture/url "https://en.wikipedia.org/wiki/Makonde_art"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "moz.heritage.island-of-mozambique"
     :culture/name "Island of Mozambique"
     :culture/country "MOZ"
     :culture/kind :heritage
     :culture/summary "Historically significant island off Mozambique's northeastern coast that served as the capital of Portuguese East Africa until 1898, inscribed as a UNESCO World Heritage Site in 1991."
     :culture/url "https://en.wikipedia.org/wiki/Island_of_Mozambique"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-moz culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "MOZ"))
                 " MOZ entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
