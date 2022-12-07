import { useState } from "react";
import {
  AddBookModal,
  Backdrop,
  BookCarousel,
  BookList,
  Navbar,
} from "@/components";
import "./style.css";
import { Sidebar } from "../../components";

export default function Dashboard() {
  const [bookModalVisibility, setBookModalVisibility] = useState(false);
  const [backdropOption, setBackdrop] = useState({
    visible: false,
    zIndex: 10,
  });
  const sidebarVisibility = backdropOption.visible;
  const backdrop = (visible, zIndex = backdropOption.zIndex) => {
    if (bookModalVisibility) setBookModalVisibility(false);
    setBackdrop({ visible, zIndex });
  };
  return (
    <>
      <Backdrop option={backdropOption} onClick={() => backdrop(false)} />
      <AddBookModal
        visible={bookModalVisibility}
        onClick={() => backdrop(false)}
      />
      <Navbar onClick={() => backdrop(true, 10)} />
      <Sidebar visibility={sidebarVisibility}
        onClick={() => {
          backdrop(true, 11);
          setBookModalVisibility(true);
        }}
      />
      <main>
        <BookCarousel />
        <BookList />
      </main>
    </>
  );
}
