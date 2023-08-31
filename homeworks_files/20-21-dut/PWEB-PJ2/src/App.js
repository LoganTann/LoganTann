import "./App.css";
import React from "react";
import { AppMap } from "./components/AppMap";
import { ResearchBar } from "./components/ResearchBar";
import { LoginForm } from "./components/LoginForm";
import logo from "./images/logo.svg";
/**
 * Composant principal de notre application
 */
export class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      lat: 0,
      lon: 0,
      connected: false,
    };
  }

  componentDidMount() {
    document.title = "Des Cartes (projet Pweb)";
  }

  onSearch(coord) {
    this.setState({
      lat: coord.lat,
      lon: coord.lon,
    });
  }

  onReceiveUserPosition(position) {
    this.setState({
      lat: position.coords.latitude,
      lon: position.coords.longitude,
    });
  }

  onSuccessfulLogin() {
    this.setState({ connected: true });
  }

  render() {
    if (!this.state.connected) {
      return (
        <div className="App">
          <LoginForm onSuccessfulLogin={this.onSuccessfulLogin.bind(this)} />
        </div>
      );
    }
    return (
      <div className="App">
        <AppMap lat={this.state.lat} lon={this.state.lon}></AppMap>
        <div style={{ padding: "10px", overflowY: "auto", width: "400px" }}>
          <img src={logo} class="logo-bar" alt="logo" />
          <p>Où allons-nous ?</p>
          <ResearchBar onSearch={this.onSearch.bind(this)} />
        </div>
      </div>
    );
  }
}
