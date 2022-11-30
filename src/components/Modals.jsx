import styled from "@emotion/styled";
import { useSetAtom } from "jotai";
import booksAtom from "../books";
import { useState } from "react";
import { delay, makeid } from "../helper";

const InputBox = styled.div`
  display: flex;
  justify-content: flex-end;
  padding: 0.5em;

  & textarea {
    font-size: inherit;
    font-family: inherit;
    height: 120px;
  }
  & label {
    font-size: 1 rem;
    padding: 0.5em 1em 0.5em 0;
    flex: 1;
    @media (max-width: 768px) {
      display: none;
    }
  }
  & button {
    border: 1px solid gray !important;
    border-radius: 5px;
    padding-bottom: 20px;
    width: 100px;
    background-color: rgb(128, 157, 173) !important;
  }
  & input,
  & textarea {
    width: 100%;
    font-weight: inherit;
    border-radius: 6px;
    border: 1px solid grey;
    padding: 0.5em;
    flex: 2;
  }
`;

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
  const setBooks = useSetAtom(booksAtom);

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
      setBooks((map) =>
        map.set(makeid(), {
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
        <InputBox>
          <label>Title</label>
          <input name="title" type="text" placeholder="Book title" required />
        </InputBox>
        <InputBox>
          <label>Author</label>
          <input name="author" type="text" placeholder="Book Author" required />
        </InputBox>
        <InputBox>
          <label>Image Url 1</label>
          <input
            name="image_url1"
            type="text"
            placeholder="Image url"
            required
          />
        </InputBox>
        <InputBox>
          <label>Image Url 2 (Optional)</label>
          <input name="image_url2" type="text" placeholder="Image url" />
        </InputBox>
        <InputBox>
          <label>Description</label>
          <textarea
            name="description"
            placeholder="Book description"
            required
          ></textarea>
        </InputBox>
        <InputBox>
          <button href="#" className="btn text-white">
            {buttonStatus}
          </button>
        </InputBox>
      </form>
    </Modal>
  );
}
export function EditBookModal({ bookKey, book, visible, onClick: clickEvent }) {
  const [buttonStatus, setButtonStatus] = useState("Save");
  const setBooks = useSetAtom(booksAtom);

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
      setBooks((map) =>
        map.set(bookKey, {
          title,
          author,
          description,
          image_url1: imageUrl1,
          image_url2: imageUrl2,
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
        <h2>Edit Book</h2>
        <a onClick={clickEvent} className="btn btn-link text-dark">
          X
        </a>
      </div>
      <form onSubmit={handleForm}>
        <InputBox>
          <label>Title</label>
          <input
            name="title"
            type="text"
            placeholder="Book title"
            required
            value={title}
            onChange={({ target }) => setTitle(target.value)}
          />
        </InputBox>
        <InputBox>
          <label>Author</label>
          <input
            name="author"
            type="text"
            placeholder="Book Author"
            required
            value={author}
            onChange={({ target }) => setAuthor(target.value)}
          />
        </InputBox>
        <InputBox>
          <label>Image Url 1</label>
          <input
            name="image_url1"
            type="text"
            placeholder="Image url"
            required
            value={imageUrl1}
            onChange={({ target }) => setImageUrl1(target.value)}
          />
        </InputBox>
        <InputBox>
          <label>Image Url 2 (Optional)</label>
          <input
            name="image_url2"
            type="text"
            placeholder="Image url"
            value={imageUrl2}
            onChange={({ target }) => setImageUrl2(target.value)}
          />
        </InputBox>
        <InputBox>
          <label>Description</label>
          <textarea
            name="description"
            placeholder="Book description"
            required
            value={description}
            onChange={({ target }) => setDescription(target.value)}
          ></textarea>
        </InputBox>
        <InputBox>
          <button href="#" className="btn text-white">
            {buttonStatus}
          </button>
        </InputBox>
      </form>
    </Modal>
  );
}

export function DeleteBookModal({ title,  visible, onClick: clickEvent }) {
  return (
    <Modal visible={visible}>
      <div style={{ textAlign: "center" }}>
        <div className="columns" style={{ justifyContent: "space-between" }}>
          <h2></h2>
          <a onClick={clickEvent} className="btn btn-link text-dark">
            X
          </a>
        </div>

        <img style={{paddingTop: "50px"}} src="/assets/checked.png" />
        <h3 style={{paddingTop: "50px"}}>
          Buku <b> {title} </b>
          berhasil dihapus !
        </h3>
      </div>
    </Modal>
  );
}
