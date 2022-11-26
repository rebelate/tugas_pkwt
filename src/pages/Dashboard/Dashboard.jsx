import { useEffect, useState } from "react";
import { Backdrop } from "../../components";
import "./style.css";

function Navbar() {
  const [backdrop, setBackdrop] = useState(false);

  return (
    <>
      <Backdrop visible={backdrop} onClick={() => setBackdrop(false)} />
      <header className="column navbar">
        <div className="navbar-center ml-3">
          <div className="side-menu show-xl">
            <a
              className="text-dark text-bold btn btn-link mr-2"
              onClick={() => setBackdrop(true)}
            >
              &#xf0c9;
            </a>
          </div>
          <a href="#" className="text-dark text-bold btn btn-link mr-2">
            All Categories &#xF0d7;
          </a>
          <a href="#" className="text-dark text-bold btn btn-link">
            All Time &#xF0d7;
          </a>
        </div>
        <div className="navbar-section">
          <div className="container grid-xs">
            <input
              className="form-input"
              type="text"
              placeholder="&#xF002;  Search book"
              style={{
                borderRadius: "15px",
                backgroundColor: "#ebedf5",
                paddingLeft: "20px",
              }}
            />
          </div>
        </div>
        <div className="navbar-center mr-3">
          <img src="assets/bookshelf.png" />
          <h2
            className="text-dark text-bold mt-3"
            style={{ cursor: "default" }}
          >
            Library
          </h2>
        </div>
      </header>
    </>
  );
}

export default function Dashboard() {
  const [count, setCount] = useState(0);
  useEffect(() => {
    console.log("ONCE or is it?");
  });

  return (
    <>
      <Navbar />
    </>
  );
}
