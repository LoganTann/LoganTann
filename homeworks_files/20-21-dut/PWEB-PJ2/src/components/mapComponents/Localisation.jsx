import React, { useState, useEffect } from "react";
import { Marker, Popup, useMapEvents } from "react-leaflet";
import L from "leaflet";
import logoGeoloc from "../../images/geoloc.svg";

export default function Localisation() {
    const [position, setPosition] = useState(null);
    const Map = useMapEvents({
        locationfound(e) {
            setPosition(e.latlng);
            Map.flyTo(e.latlng, Map.getZoom());
        },
        locationerror(e) {
            console.error("Erreur de localisation", e);
        },
    });

    useEffect(() => {
        const legend = L.control({ position: "topright" });
        legend.onAdd = () => {
            const div = L.DomUtil.create("div", "info locate");
            div.innerHTML = `<img src="${logoGeoloc}" alt="Localiser" width="20" height="auto"/>`;
            div.onclick = () => Map.locate();
            return div;
        };
        legend.addTo(Map);
    }, [Map]);

    return position === null ? null : (
        <Marker position={position}>
            <Popup>Votre position actuelle</Popup>
        </Marker>
    );
}
