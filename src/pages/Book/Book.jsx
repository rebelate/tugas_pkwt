import { useState } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { useLocation } from "wouter";
import { Backdrop } from "@/components";
import { EditBookModal, DeleteBookModal } from "@/components/Modals";
import { deleteBook } from "@/slices/bookSlice";
import "./style.css";

export default function Book({ params: { book: currentBook } }) {
  const [location, setLocation] = useLocation();
  const userIsAdmin = useSelector((state) => state.auth.user.admin);
  const dispatch = useDispatch();
  const data = {
    ...useSelector((state) =>
      state.book.list.find((book) => book.id === currentBook)
    ),
  };
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
  const deleteBookHandle = () =>
    setTimeout(() => {
      dispatch(deleteBook(currentBook));
      setLocation("/");
    }, 1500);
  return (
    <div id="book-page">
      <Backdrop option={backdropOption} onClick={() => backdrop(false)} />
      <EditBookModal
        visible={editBookModalVisibility}
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
          {userIsAdmin && (
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
                  setDeleteBookModalVisibility(true);
                  deleteBookHandle();
                }}
                className="btn delete-btn"
              >
                Delete
              </a>
            </div>
          )}

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
            <div className="date">{data.date}</div>
            <p className="description">{data.description}</p>
          </div>
          <div
            className="column hide-xl"
            style={{ flex: "0 0 20%", position: "relative" }}
          >
            {data.image_url2 && (
              <div
                className="book-cover"
                style={{ backgroundImage: `url(${data.image_url2})` }}
              >
                {/* <img src={data.image_url2} /> */}
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
