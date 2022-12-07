import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { useLocation } from "wouter";
import { login as loginAction } from "@/slices/authSlice";
import { Formik } from "formik";
import "./style.css";
import { useEffect, useState } from "react";
import { LoadingSpinner } from "@/components";
import { clearStatus } from "../../slices/authSlice";

export default function Login() {
  const [location, setLocation] = useLocation();
  const dispatch = useDispatch();
  const userStatus = useSelector((state) => state.auth.status);
  useEffect(() => {
    if (userStatus === "logged") {
      setLocation("/dashboard");
    }
    return () => {
      if (userStatus === "denied") {
        dispatch(clearStatus());
      }
    };
  }, [userStatus]);
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
          <h2 className="text-bold">Login</h2>
          <p>Welcome Back, Please Login to your account</p>
          <div className="container">
            <Formik
              initialValues={{ email: "", password: "" }}
              validate={(values) => {
                const errors = {};

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
                dispatch(clearStatus());
                setTimeout(() => {
                  setSubmitting(false);
                  dispatch(loginAction(values));
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
                    {userStatus === "denied" &&
                      "Sorry, we don't recognize this user"}
                  </div>
                  <div
                    style={{ color: "red", fontSize: "12px", margin: "4px" }}
                  >
                    {errors.password && touched.password && errors.password}
                  </div>
                  <div className="remember-me--forget-password">
                    <label className="form-checkbox">
                      <input type="checkbox" />
                      <i className="form-icon"></i> Remember me
                    </label>
                    <p>forget password?</p>
                  </div>
                  <br />
                  <div className="columns" style={{ gap: "12px", margin: 0 }}>
                    <button
                      className="btn bg-dark"
                      type="submit"
                      disabled={isSubmitting}
                    >
                      {isSubmitting ? <LoadingSpinner /> : "Login"}
                    </button>
                    <button
                      onClick={(e) => {
                        e.preventDefault();
                        setLocation("/register");
                      }}
                      className="btn text-dark"
                    >
                      Sign Up
                    </button>
                  </div>
                  <br />
                </form>
              )}
            </Formik>
          </div>
        </div>
      </div>
    </div>
  );
}
