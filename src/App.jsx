import { useSelector } from "react-redux";
import { Switch } from "wouter";
import { useLocation } from "wouter";
import { Route, Redirect } from "wouter";
import { Book, Dashboard, Login, Register } from "./pages";

function ProtectedRoute({ path, component }) {
  const [location] = useLocation();
  const userStatus = useSelector((state) => state.auth.status);
  const basePath = path.match(".*/")[0];
  const baseLocation = location.match(".*/")[0];
  // return user === "logged" ? component : <Redirect to="/login" />;

  return userStatus === "logged" ? (
    <Route path={path} component={component} />
  ) : (
    baseLocation === basePath && <Redirect to="/login" />
  );
}

export default function App() {
  return (
    <Switch>
      <Route path="/">
        <Redirect to="/dashboard" />
      </Route>
      <Route path="/login" component={Login} />
      <Route path="/register" component={Register} />
      <ProtectedRoute path="/dashboard" component={Dashboard} />
      <ProtectedRoute path="/books" component={Dashboard} />
      <ProtectedRoute path="/books/:book" component={Book} />
      <Route>
        <h1>404 Not Found</h1>
      </Route>
    </Switch>
  );
}
