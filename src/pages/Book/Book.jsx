import { useSetAtom } from "jotai";
import { useAtom } from "jotai";
import { useAtomValue } from "jotai";
import { useState } from "react";
import { useLocation } from "wouter";
import booksAtom from "../../books";
import { Backdrop } from "../../components";
import { EditBookModal, DeleteBookModal } from "../../components/Modals";
import "./style.css";

export default function Book({ params: { book: currentBook } }) {
  const [location, setLocation] = useLocation();
  const [booksMap,setBooksMap] = useAtom(booksAtom);
  const data = Object.assign({}, booksMap.get(currentBook));
  const navigateToIndex = () => {
    setLocation("/");
  };
  const [backdropOption, setBackdrop] = useState({
    visible: false,
    zIndex: 10,
  });
  const [editBookModalVisibility, setEditBookModalVisibility] = useState(false);
  const [deleteBookModalVisibility, setDeleteBookModalVisibility] =
    useState(false);
  const backdrop = (visible, zIndex = backdropOption.zIndex) => {
    if (editBookModalVisibility) setEditBookModalVisibility(false);
    if (deleteBookModalVisibility) setDeleteBookModalVisibility(false);
    setBackdrop({ visible, zIndex });
  };
  const deleteBook = () =>
    setBooksMap((map) => {
      map.delete(currentBook);
      return map;
    });
  return (
    <div id="book-page">
      <Backdrop option={backdropOption} onClick={() => backdrop(false)} />
      <EditBookModal
        visible={editBookModalVisibility}
        bookKey={currentBook}
        book={data}
        onClick={() => backdrop(false)}
      />
      <DeleteBookModal
        title={data.title}
        visible={deleteBookModalVisibility}
        onClick={() => backdrop(false)}
      />
      <div className="book-box reveal">
        <div
          className="top"
          style={{
            position: "relative",
            backgroundImage: `url(${data.image_url1})`,
          }}
        >
          <div style={{ position: "absolute", right: "0" }}>
            <a
              onClick={() => {
                backdrop(true);
                setEditBookModalVisibility(true);
              }}
              className="btn edit-btn"
            >
              Edit
            </a>
            &nbsp;
            <a
              onClick={() => {
                backdrop(true);
                setDeleteBookModalVisibility(true);deleteBook()
              }}
              className="btn delete-btn"
            >
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
                "btn borrow-btn " + (!data.image_url2 ? " borrow-btn-up" : "")
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
