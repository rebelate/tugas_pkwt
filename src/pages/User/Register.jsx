import { useLocation } from "wouter";
import "./style.css";

export default function Register() {
  const [location, setLocation] = useLocation();
  return (
    <div id="user-page">
      <div className="box-form reveal">
        <div className="left">
          <div className="overlay">
            <h2 className="text-bold">
              Book is a window <br />
              to the world.
            </h2>
            <span
              className="hide-xl"
              style={{ position: "absolute", bottom: 0 }}
            >
              <p>Photo by Mark Pan4ratte on Unsplash</p>
            </span>
          </div>
        </div>
        <div className="right">
          <div className="columns" style={{ justifyContent: "end" }}>
            <div className="col">
              <img src="assets/bookshelf.png" />
            </div>
          </div>
          <h2 className="text-bold">Sign Up</h2>
          <p>Welcome, Let's register your account now</p>
          <div className="container">
            <form onSubmit={()=>setLocation("/login")}>
              <div className="inputBox">
                <input type="text" required="required" />
                <span>Username</span>
              </div>
              <div className="inputBox">
                <input type="text" required="required" />
                <span>Full Name</span>
              </div>
              <div className="inputBox">
                <input type="text" required="required" />
                <span>Email Address</span>
              </div>
              <div className="inputBox">
                <input type="password" required="required" />
                <span>password</span>
              </div>

              <div className="remember-me--forget-password">
                <label className="form-checkbox">
                  <input type="checkbox" />
                  <i className="form-icon"></i> Remember me
                </label>
              </div>
              <br />
              <div className="columns" style={{ gap: "12px", margin: 0 }}>
                <button className="btn bg-dark" type="submit">
                  Sign Up
                </button>
                <button
                  onClick={(e) => {
                    e.preventDefault();
                    setLocation("/login")
                  }}
                  className="btn text-dark"
                >
                  Login
                </button>
              </div>
              <br />
              <p>
                By signing up, you agree to Book’s Terms and Conditions &
                Privacy Policy
              </p>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}
