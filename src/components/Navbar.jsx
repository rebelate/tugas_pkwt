import { useCallback, useEffect, useMemo, useState } from "react";
import { useLocation } from "@/hooks/locationHook";
import { debounce } from "@/helper";
import "./style.css";

export function Navbar({ onClick: clickEvent }) {
  const [, setLocation, query] = useLocation();
  const [searchValue, setSearchValue] = useState("");
  const handleQuery = debounce((value) => {
    setLocation(`?search=${value}&sort=${query.sort}&page=${query.page}`);
  }, 400);

  const onSearchChange = useCallback(
    (q) => {
      let value = q.target.value;
      setSearchValue(value);
      handleQuery(value);
    },
    [query.base]
  );
  useEffect(() => {
    if (query.search) setSearchValue(query.search);
  }, []);
  return (
    <header className="column navbar" style={{ transition: "width .3s ease" }}>
      <div className="navbar-center ml-3">
        <div className="side-menu show-xl">
          <a
            className="text-dark text-bold btn btn-link mr-2"
            onClick={clickEvent}
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
            onChange={onSearchChange}
            value={searchValue}
            className="form-input"
            type="text"
            placeholder="&#xF002;  Search book"
            onMouseDown={() => {
              let bookCardElement = document.getElementById("book-card");
              if (bookCardElement)
                window.scrollTo({
                  top: bookCardElement.offsetTop - 100,
                  behavior: "smooth",
                });
            }}
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
        <h2 className="text-dark text-bold mt-3" style={{ cursor: "default" }}>
          Library
        </h2>
      </div>
    </header>
  );
}
