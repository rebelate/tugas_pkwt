import React from "react";
import ReactDOM from "react-dom";
import { Route } from "wouter";
import {Book, Dashboard, Login, Register} from "./pages";
import "./index.css";

ReactDOM.render(
  <React.StrictMode>
    <Route path="/" component={Dashboard}/>
    <Route path="/login" component={Login}/>
    <Route path="/register" component={Register}/>
    <Route path="/books/:book" component={Book}/>
  </React.StrictMode>,
  document.getElementById("root")
);
