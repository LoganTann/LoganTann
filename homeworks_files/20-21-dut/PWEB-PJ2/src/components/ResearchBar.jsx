import React from "react";
import "../styles/ResearchBar.css";

export class ResearchBar extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      resultatsRecherche: null,
    };

    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async handleSubmit(event) {
    //empêche l'action par défaut au moment du submit
    event.preventDefault();
    const neverTrustUserInput = event.target.search.value;
    console.log("UserInput :", neverTrustUserInput);
    const response = await fetch(
      `https://nominatim.openstreetmap.org/search.php?q=${neverTrustUserInput}&limit=4&format=jsonv2`
    );
    const data = await response.json();
    let propositions = data;

    this.setState({
      resultatsRecherche: propositions,
    });
  }

  clearResults() {
    this.setState({
      resultatsRecherche: null,
    });
    this.donnerPosition(0, 0);
  }

  donnerPosition(lattitude, longitude) {
    this.props.onSearch({ lat: lattitude, lon: longitude });
  }

  render() {
    let button = null;
    if (this.state.resultatsRecherche) {
        button = <button onClick={this.clearResults.bind(this)}>Suppr</button>;
    }
    return (
      <div className="rech-bar">
        <form onSubmit={this.handleSubmit}>
          <label className="label-rech-bar">
            <input
              type="text"
              className="imput-rech-bar"
              placeholder="143 avenue de Versailles 75016 Paris"
              name="search"
            />
          </label>
          <input type="submit" className="buttonRech" value="Go !" />
        </form>
        <hr />
        { button }
        {this.state.resultatsRecherche?.map((resultat) => (
          <>
            <div
              key={resultat.osm_id}
              onClick={() => this.donnerPosition(resultat.lat, resultat.lon)}
            >
              {resultat.display_name}
            </div>
            <hr />
          </>
        ))}
      </div>
    );
  }
}
