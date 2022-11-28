import React from "react";
import ReactDOM from "react-dom";
import { Route } from "wouter";
import {Dashboard, Login, Register} from "./pages";
import "./index.css";

ReactDOM.render(
  <React.StrictMode>
    <Route path="/" component={Dashboard}/>
    <Route path="/login" component={Login}/>
    <Route path="/register" component={Register}/>
    <Route path="/books/:book"/>
  </React.StrictMode>,
  document.getElementById("root")
);
