import React, { useEffect, useState } from "react";
import { useMapEvents, LayerGroup, Polyline } from "react-leaflet";
import { getPistesCyclables, getColor } from "../../utils/parisOpenData";
import Legende from "./Legende";

/**
 * LocationMarker component : Marqueur automatique affichant la position actuelle de l'utilisateur.
 * Pour le mettre à jour, utiliser l'event LocateBtnClicked via EventEmitter.
 */
export default function PistesCyclables() {
    const [positions, setPositions] = useState(null);

    function onMapChange(map) {
        const { lat, lng } = map.getCenter();
        const { _northEast, _southWest } = map.getBounds();
        const location = `${map.getZoom()},${lat},${lng}`;
        const bbox = `${_southWest.lat},${_southWest.lng},${_northEast.lat},${_northEast.lng}`;
        getPistesCyclables(location, bbox).then((pistes) => {
            setPositions(pistes);
        });
    }

    const map = useMapEvents({
        moveend() {
            onMapChange(map);
        }
    });
    useEffect(() => {
        if (positions === null) {
            onMapChange(map);
        }
    }, [map, positions]);

    function pathStyle(layer) {
        const zoom = map.getZoom();
        return {
            color: getColor.of(layer),
            weight: zoom > 13 && zoom < 15 ? 2 : 5,
            opacity: 0.5,
        };
    }

    return positions === null ? null : (
        <LayerGroup>
            {positions.map((piste) => (
                <Polyline
                    pathOptions={pathStyle(piste.layer)}
                    positions={piste.coordinates}
                    key={piste.id}
                />
            ))}
            <Legende map={map} />
        </LayerGroup>
    );
}
