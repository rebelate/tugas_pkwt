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
        <h2 className="text-bold">Login</h2>
          <p>Welcome Back, Please Login to your account</p>
        <div className="container">
          <form action='/'>
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
              <p>forget password?</p>
          </div>
          <br />
          <div className="columns" style={{gap: '12px', margin: 0}}>
              <button className="btn bg-dark" type='submit'>Login</button>
              <button onClick={(e)=>{e.preventDefault();location.href='register'}} className="btn text-dark">Sign Up</button>
          </div><br/>
          </form>
        </div>
      </div>
    </div>
  </div>)
}