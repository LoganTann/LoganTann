import React from "react";
import { MapContainer, TileLayer } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import PistesCyclables from "./mapComponents/PistesCyclables";
import Localisation from "./mapComponents/Localisation";
import Recherche from "./mapComponents/Recherche";

/**
 * Composant d'affichage des cartes de notre application
 */
export class AppMap extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            pistes: null,
        };
    }

    render() {
        return (
            <MapContainer center={[48.8420233, 2.267862]} zoom={13}>
                <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
                
                <Recherche position={[this.props.lat, this.props.lon]} />
                <Localisation />
                <PistesCyclables />
            </MapContainer>
        );
    }
}
