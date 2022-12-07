import { Formik } from "formik";
import { useDispatch } from "react-redux";
import { useLocation } from "wouter";
import { LoadingSpinner } from "@/components";
import { makeid } from "@/helper";
import { register as registerAction } from "@/slices/authSlice";
import "./style.css";

export default function Register() {
  const [location, setLocation] = useLocation();
  const dispatch = useDispatch();
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
            <Formik
              initialValues={{ name: "", email: "", password: "" }}
              validate={(values) => {
                const errors = {};
                if (!values.name) {
                  errors.name = "Required full name";
                }
                if (!values.email) {
                  errors.email = "Required email address";
                } else if (
                  !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
                ) {
                  errors.email = "Invalid email address";
                }
                if (!values.password) {
                  errors.password = "Required password";
                }
                return errors;
              }}
              onSubmit={(values, { setSubmitting }) => {
                setTimeout(() => {
                  setSubmitting(false);
                  const data = values;
                  data.id = makeid();
                  data.admin = false;
                  console.log(dispatch(registerAction(data)))
                  setLocation("/login")
                }, 1500);
              }}
            >
              {({
                values,
                errors,
                touched,
                handleChange,
                handleBlur,
                handleSubmit,
                isSubmitting,
              }) => (
                <form onSubmit={handleSubmit}>
                  <div
                    className={`inputBox${
                      errors.name && touched.name ? " error" : ""
                    }`}
                  >
                    <input
                      type="text"
                      name="name"
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.name}
                      required
                    />
                    <span>Full Name</span>
                  </div>
                  <div
                    style={{ color: "red", fontSize: "12px", margin: "4px" }}
                  >
                    {errors.name && touched.name && errors.name}
                  </div>
                  <div
                    className={`inputBox${
                      errors.email && touched.email ? " error" : ""
                    }`}
                  >
                    <input
                      type="text"
                      name="email"
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.email}
                      required
                    />
                    <span>Email address</span>
                  </div>
                  <div
                    style={{ color: "red", fontSize: "12px", margin: "4px" }}
                  >
                    {errors.email && touched.email && errors.email}
                  </div>
                  <div
                    className={`inputBox${
                      errors.password && touched.password ? " error" : ""
                    }`}
                  >
                    <input
                      type="password"
                      name="password"
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.password}
                      required
                    />
                    <span>Password</span>
                  </div>
                  <div
                    style={{ color: "red", fontSize: "12px", margin: "4px" }}
                  >
                    {errors.password && touched.password && errors.password}
                  </div>
                  <br />
                  <div className="columns" style={{ gap: "12px", margin: 0 }}>
                    <button
                      className="btn bg-dark"
                      type="submit"
                      disabled={isSubmitting}
                    >
                      {isSubmitting ? <LoadingSpinner /> : "Sign Up"}
                    </button>
                    <button
                      onClick={(e) => {
                        e.preventDefault();
                        setLocation("/login");
                      }}
                      className="btn text-dark"
                    >
                      Login
                    </button>
                  </div>
                  <br />
                </form>
              )}
            </Formik>
            <br />
            <p>
              By signing up, you agree to Book’s Terms and Conditions & Privacy
              Policy
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
