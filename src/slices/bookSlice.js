import { createSlice } from "@reduxjs/toolkit";
import {books as booksInit} from "@/datas";

const getInitialBooks = () => {
  // console.log(booksInit);
  const localBooks = window.localStorage.getItem("bookList");
  if (localBooks) {
    return JSON.parse(localBooks);
  }

  window.localStorage.setItem("bookList", JSON.stringify(booksInit));
  return booksInit;
};

const initalValue = {
  list: getInitialBooks(),
};

export const bookSlice = createSlice({
  name: "book",
  initialState: initalValue,
  reducers: {
    addBook: (state, action) => {
      state.list.push(action.payload);
      const books = window.localStorage.getItem("bookList");
      if (books) {
        const booksArr = JSON.parse(books);
        booksArr.push(action.payload);
        window.localStorage.setItem("bookList", JSON.stringify(booksArr));
      } else {
        window.localStorage.setItem(
          "bookList",
          JSON.stringify([{ ...action.payload }])
        );
      }
    },
    deleteBook: (state, action) => {
      const books = window.localStorage.getItem("bookList");
      if (books) {
        const booksArr = JSON.parse(books).filter(
          (book) => action.payload !== book.id
        );
        window.localStorage.setItem("bookList", JSON.stringify(booksArr));
        state.list = booksArr;
      }
    },
    updateBook: (state, action) => {
      const books = window.localStorage.getItem("bookList");
      if (books) {
        const booksArr = JSON.parse(books);
        booksArr.forEach((book) => {
          if (action.payload.id === book.id) {
            // console.log("found", book.id);
            book.title = action.payload.title;
            book.author = action.payload.author;
            book.description = action.payload.description;
            book.image_url1 = action.payload.image_url1;
            book.image_url2 = action.payload.image_url2;
            book.date = action.payload.date
          }
        });
        window.localStorage.setItem("bookList", JSON.stringify(booksArr));
        state.list = booksArr;
      }
    },
  },
});

export const { addBook, deleteBook, updateBook } =
  bookSlice.actions;
export const bookReducer = bookSlice.reducer;
