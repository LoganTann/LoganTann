import React, { useEffect } from "react";
import { Marker, Popup, useMap } from "react-leaflet";

/**
 * Marqueur dynamique de recherche
 * @param {[number, number]} position 
 */
export default function Recherche(props) {
    const validPosition = () => props.position[0] !== 0 && props.position[1] !== 0
    const map = useMap();
    useEffect(() => {
        if (validPosition()) {
            map.setView(props.position, 13);
        }
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [props.position]);
    return props.position === null || !validPosition() ? null : (
        <Marker position={[props.position[0], props.position[1]]}>
            <Popup>Votre recherche</Popup>
        </Marker>
    );
}
