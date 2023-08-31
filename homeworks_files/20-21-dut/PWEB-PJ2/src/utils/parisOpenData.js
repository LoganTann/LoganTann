class requestCache {
    static cache = {};

    static get(url) {
        return this.cache[url];
    }

    static set(url, data) {
        this.cache[url] = data;
    }
}

export const getColor = {
    "couloirs de bus ouverts aux vélos": "blue",
    "bandes cyclables": "orange",
    "autres itinéraires cyclables (ex : aires piétonnes - contre-sens cyclables)":
        "red",
    "pistes cyclables": "green",
    autre: "black",
    of(name) {
        name = name.trim().toLowerCase();
        return this[name] || this["autre"];
    },
};

/**
 * Retourne les pistes cyclables de la ville de Paris sous format geoJSON.
 * 4825 2.31 48.82
 * a : 48.8271317295054,2.31488049030304,48.826114625902136,2.31213390827179
 * p : 48.825916853359765,2.3119783401489253,48.827329497253174,2.3150360584259033
 */
export async function getPistesCyclables(
    location = "12, 48.85432, 2.34575",
    bbox = "48.7711250173148,2.1869659423828125,48.951817388604084,2.5323486328125",
    rows = 1500
) {
    const endpointURL =
        "https://opendata.paris.fr/api/records/1.0/download/?disjunctive.typologie_simple=true&disjunctive.bidirectionnel=true&disjunctive.statut=true&disjunctive.sens_velo=true&disjunctive.voie=true&disjunctive.arrdt=true&disjunctive.bois=true&disjunctive.position=true&disjunctive.circulation=true&disjunctive.piste=true&disjunctive.couloir_bus=true&disjunctive.type_continuite=true&disjunctive.reseau=true" +
        "&dataset=reseau-cyclable&lang=fr&format=json&timezone=Europe%2FBerlin&fields=geo_shape,typologie_simple&geo_simplify=true" +
        "&rows=" +
        rows +
        "&geo_simplify_zoom=" +
        location.substring(0, location.indexOf(",")) +
        "&location=" +
        location +
        "&geofilter.bbox=" +
        bbox;
    let pistes = requestCache.get(endpointURL);
    if (!pistes) {
        const requete = await fetch(endpointURL);
        pistes = await requete.json();
        requestCache.set(endpointURL, pistes);
    }

    const retval = pistes.map((piste) => {
        // les latlong données par l'API sont inversées.
        const positions = piste.fields.geo_shape.coordinates.map((points) => {
            return [points[1], points[0]];
        });
        return {
            coordinates: positions,
            layer: piste.fields.typologie_simple,
            id: piste.recordid,
        };
    });
    return retval;
}
