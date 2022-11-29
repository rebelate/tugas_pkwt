import styled from "@emotion/styled";
import { keyframes } from "@emotion/react";
import { useSetAtom } from "jotai";
import { books as booksAtom } from "../books";
import { useState } from "react";

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
    width:100%;
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
    const image_url1 = datas.get("image_url1");
    const image_url2 = datas.get("image_url2");
    const description = datas.get("description");
    toggleInputDisable(true);
    setTimeout(() => {
      setBooks((old) => [
        ...old,
        {
          title,
          author,
          description,
          image_url1,
          image_url2,
        },
      ]);
      setButtonStatus("Saved");
      setTimeout(() => {
        clickEvent();
        toggleInputDisable(false);
      }, 500);
    }, 350);
  };
  return (
    <Modal visible={visible}>
      <div className="columns" style={{ justifyContent: "space-between" }}>
        <h2>Add Book</h2>
        <a onClick={clickEvent} className="btn btn-link text-dark">
          X
        </a>
      </div>
      <form id="add-book" onSubmit={handleForm}>
        <InputBox>
          <label>Title</label>
          <input name="title" type="text" placeholder="Book title" required />
        </InputBox>{" "}
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
