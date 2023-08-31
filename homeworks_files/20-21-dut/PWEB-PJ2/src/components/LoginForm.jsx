import React from "react";
import logo from '../images/logo.svg';
import styles from '../styles/LoginForm.css';


export function LoginForm(props) {
    async function login(event) {
        event.preventDefault();
        const mail = event.target.mail.value;
        const password = event.target.password.value;
        const response = await fetch("https://api.pjs4.jiveoff.fr/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ mail, password }),
        });
        let data = null;
        try {
            data = await response.json();
        } catch (error) {
            alert("Impossible de récupérer de la donnée depuis le serveur distant : " + error);
            return;
        }
        if (response.status !== 201) {
            alert(`Erreur de connection (${response.status}) : ${data.message}`);
            return;
        }
        props.onSuccessfulLogin();
    }
    return (
        <form onSubmit={login} id="loginForm">
            <img src={logo} alt="logo" class="logo" />
            <input name="mail" type="email" placeholder="e-mail" required></input>
            <input name="password" type="password" placeholder="mot de passe" required></input>
            <input type="submit"></input>
        </form>
    )
}