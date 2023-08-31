import { useEffect } from "react";
import L from "leaflet";
import { getColor } from "../../utils/parisOpenData";

export default function Legende({ map }) {
    useEffect(() => {
        const legend = L.control({ position: "bottomleft" });
        legend.onAdd = () => {
            const div = L.DomUtil.create("details", "info legend");
            const labels = [];
            for (let layer in getColor) {
                if (layer === "of") continue;
                labels.push(
                    `<i style="background: ${getColor[layer]};"></i>${layer}`
                );
            }
            div.innerHTML = "<summary>Légende</summary>" + labels.join("<br>");
            return div;
        };
        legend.addTo(map);
    }, [map]);
    return null;
}
