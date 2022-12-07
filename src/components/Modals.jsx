import styled from "@emotion/styled";
import { useState } from "react";
import { useDispatch } from "react-redux";
import { delay, getDate, makeid } from "@/helper";
import { addBook, updateBook } from "@/slices/bookSlice";

const Modal = styled.div(({ visible }) => {
  return {
    transition: "300ms ",
    opacity: visible ? 1 : 0,
    visibility: !visible && "hidden",
    width: "800px",
    height: "550px",
    boxShadow: "#0003 0px 0px 10px 3px",
    borderRadius: "20px",
    padding: "24px 24px",
    backgroundColor: "white",
    fontWeight: "700",
    position: "fixed",
    left: "0",
    right: "0",
    top: "0",
    bottom: "0",
    margin: "auto",
    // maxWidth: "100%",
    // maxHeight: "100%",
    zIndex: "12",
    "@media (max-width: 1280px)": {
      maxWidth: "80%",
    },
  };
});
export function AddBookModal({ visible, onClick: clickEvent }) {
  const [buttonStatus, setButtonStatus] = useState("Save");
  // const setBooks = useSetAtom(booksAtom);
  const dispatch = useDispatch();
  const handleForm = (ev) => {
    const toggleInputDisable = (bool) => {
      for (let i = 0; i < 5; i++) {
        ev.target[i].disabled = bool;
      }
      !bool && ev.target.reset();
      setButtonStatus("Save");
    };

    ev.preventDefault();
    const datas = new FormData(ev.target);
    const title = datas.get("title");
    const author = datas.get("author");
    const description = datas.get("description");
    const image_url1 = datas.get("image_url1");
    const image_url2 = datas.get("image_url2");
    (async function () {
      toggleInputDisable(true);
      setButtonStatus("Saving");
      await delay(500);
      dispatch(
        addBook({
          id: makeid(),
          date:getDate(),
          title,
          author,
          description,
          image_url1,
          image_url2,
        })
      );
      await delay(250);
      setButtonStatus("Saved");
      await delay(250);
      clickEvent();
      await delay(250);
      toggleInputDisable(false);
    })();
  };
  return (
    <Modal visible={visible}>
      <div className="columns" style={{ justifyContent: "space-between" }}>
        <h2>Add Book</h2>
        <a onClick={clickEvent} className="btn btn-link text-dark">
          X
        </a>
      </div>
      <form onSubmit={handleForm}>
        <div className="inputBox">
          <label>Title</label>
          <input name="title" type="text" placeholder="Book title" required />
        </div>
        <div className="inputBox">
          <label>Author</label>
          <input name="author" type="text" placeholder="Book Author" required />
        </div>
        <div className="inputBox">
          <label>Image Url 1</label>
          <input
            name="image_url1"
            type="text"
            placeholder="Image url"
            required
          />
        </div>
        <div className="inputBox">
          <label>Image Url 2 (Optional)</label>
          <input name="image_url2" type="text" placeholder="Image url" />
        </div>
        <div className="inputBox">
          <label>Description</label>
          <textarea
            name="description"
            placeholder="Book description"
            required
          ></textarea>
        </div>
        <div className="inputBox">
          <button href="#" className="btn text-white">
            {buttonStatus}
          </button>
        </div>
      </form>
    </Modal>
  );
}
export function EditBookModal({ book, visible, onClick: clickEvent }) {
  const [buttonStatus, setButtonStatus] = useState("Save");
  // const setBooks = useSetAtom(booksAtom);
  const dispatch = useDispatch();
  const [title, setTitle] = useState(book.title);
  const [author, setAuthor] = useState(book.author);
  const [imageUrl1, setImageUrl1] = useState(book.image_url1);
  const [imageUrl2, setImageUrl2] = useState(book.image_url2);
  const [description, setDescription] = useState(book.description);
  const handleForm = (ev) => {
    const toggleInputDisable = (bool) => {
      for (let i = 0; i < 5; i++) {
        ev.target[i].disabled = bool;
      }
      !bool && ev.target.reset();
      setButtonStatus("Save");
    };

    ev.preventDefault();
    (async function () {
      toggleInputDisable(true);
      setButtonStatus("Saving");
      await delay(500);
      dispatch(
        updateBook({
          id: book.id,
          date:getDate(),
          title,
          author,
          description,
          image_url1: imageUrl1,
          image_url2: imageUrl2,
        })
      );
      // setBooks((map) =>
      //   map.set(book.id, {
      //     title,
      //     author,
      //     description,
      //     image_url1: imageUrl1,
      //     image_url2: imageUrl2,
      //   })
      // );
      await delay(250);
      setButtonStatus("Saved");
      await delay(250);
      clickEvent();
      await delay(250);
      toggleInputDisable(false);
    })();
  };
  return (
    <Modal visible={visible}>
      <div className="columns" style={{ justifyContent: "space-between" }}>
        <h2>Edit Book</h2>
        <a onClick={clickEvent} className="btn btn-link text-dark">
          X
        </a>
      </div>
      <form onSubmit={handleForm}>
        <div className="inputBox">
          <label>Title</label>
          <input
            name="title"
            type="text"
            placeholder="Book title"
            required
            value={title}
            onChange={({ target }) => setTitle(target.value)}
          />
        </div>
        <div className="inputBox">
          <label>Author</label>
          <input
            name="author"
            type="text"
            placeholder="Book Author"
            required
            value={author}
            onChange={({ target }) => setAuthor(target.value)}
          />
        </div>
        <div className="inputBox">
          <label>Image Url 1</label>
          <input
            name="image_url1"
            type="text"
            placeholder="Image url"
            required
            value={imageUrl1}
            onChange={({ target }) => setImageUrl1(target.value)}
          />
        </div>
        <div className="inputBox">
          <label>Image Url 2 (Optional)</label>
          <input
            name="image_url2"
            type="text"
            placeholder="Image url"
            value={imageUrl2}
            onChange={({ target }) => setImageUrl2(target.value)}
          />
        </div>
        <div className="inputBox">
          <label>Description</label>
          <textarea
            name="description"
            placeholder="Book description"
            required
            value={description}
            onChange={({ target }) => setDescription(target.value)}
          ></textarea>
        </div>
        <div className="inputBox">
          <button href="#" className="btn text-white">
            {buttonStatus}
          </button>
        </div>
      </form>
    </Modal>
  );
}

export function DeleteBookModal({ title, visible, onClick: clickEvent }) {
  return (
    <Modal visible={visible}>
      <div style={{ textAlign: "center" }}>
        <div className="columns" style={{ justifyContent: "space-between" }}>
          <h2></h2>
          <a onClick={clickEvent} className="btn btn-link text-dark">
            X
          </a>
        </div>

        <img style={{ paddingTop: "50px" }} src="/assets/checked.png" />
        <h3 style={{ paddingTop: "50px" }}>
          Buku <b> {title} </b>
          berhasil dihapus !
        </h3>
      </div>
    </Modal>
  );
}
