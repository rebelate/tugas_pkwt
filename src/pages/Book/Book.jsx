import { useAtomValue } from "jotai";
import { useLocation } from "wouter";
import booksAtom from "../../books";
import "./style.css";

export default function Book({ params: { book: currentBook } }) {
  const [location, setLocation] = useLocation();
  const navigateToIndex = () => {
    setLocation("/");
  };
  const books = useAtomValue(booksAtom);
  const data = books.get(currentBook);
  return (
    <div id="book-page">
      <div className="book-box reveal">
        <div
          className="top"
          style={{
            position: "relative",
            backgroundImage: `url(${data.image_url1})`,
          }}
        >
          <div style={{ position: "absolute", right: "0" }}>
            <a href="#book-modal" className="btn edit-btn">
              Edit
            </a>
            <a href="#delete-modal" className="btn delete-btn">
              Delete
            </a>
          </div>
          <a
            onClick={navigateToIndex}
            className="btn back-btn"
            style={{ fontFamily: "Fontawesome" }}
          >
            &#xF060;
          </a>
        </div>
        <div className="columns bottom">
          <div className="column">
            <button className="tags">Novel</button>
            <div className="columns">
              <div className="title column">{data.title}</div>
              <h2 className="status column">Available</h2>

              <button className="btn borrow-btn show-xl">Borrow</button>
            </div>
            <div className="date">30 Juni 2019</div>
            <p className="description">{data.description}</p>
          </div>
          <div
            className="column hide-xl"
            style={{ flex: "0 0 20%", position: "relative" }}
          >
            {data.image_url2 && (
              <div className="book-cover">
                <img src={data.image_url2} />
              </div>
            )}
            <button
              className={
                "btn borrow-btn " + (!data.image_url2 ? " borrow-btn-up" :"")
              }
            >
              Borrow
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
