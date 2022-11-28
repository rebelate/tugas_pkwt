import './style.css'
export default function Login() {
    return (<div id="login-page">
    <div className="box-form reveal">
      <div className="left">
        <div className="overlay">
          <h2 className="text-bold">
            Book is a window <br />
            to the world.
          </h2>
          <span className="hide-xl" style={{position: 'absolute', bottom: 0}}>
            <p>Photo by Mark Pan4ratte on Unsplash</p>
          </span>
        </div>
      </div>
      <div className="right">
        <div className="columns" style={{justifyContent: 'end'}}>
          <div className="col">
            <img src="assets/bookshelf.png" />
          </div>
        </div>
        <h2 className="text-bold">Sign Up</h2>
        <p>Welcome, Let's register your account now</p>
        <div className="container">
          <form>
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
          </form>

          <br /><br />
          <div className="remember-me--forget-password">
            <label className="form-checkbox">
              <input type="checkbox" />
              <i className="form-icon"></i> Remember me
            </label>
          </div>
          <br />
          <div className="columns" style={{gap: '12px', margin: 0}}>
            <div>
              <a href="login.html" className="btn bg-dark">Sign Up</a>
            </div>
            <div>
              <a href="login.html" className="btn text-dark">Login</a>
            </div>
            <p>
              By signing up, you agree to Book’s Terms and Conditions &
              Privacy Policy
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>)
}