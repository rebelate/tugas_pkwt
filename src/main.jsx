import React from "react";
import ReactDOM from "react-dom";
import { Route } from "wouter";
import {Dashboard} from "./pages";
import "./index.css";

ReactDOM.render(
  <React.StrictMode>
    <Route path="/" component={Dashboard}/>
    <Route path="/login"/>
    <Route path="/signup"/>
    <Route path="/books/:book"/>
  </React.StrictMode>,
  document.getElementById("root")
);
