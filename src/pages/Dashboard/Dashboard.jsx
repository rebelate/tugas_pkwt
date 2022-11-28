import { useEffect, useState } from "react";
import styled from "@emotion/styled";
import { keyframes } from "@emotion/react";
import { Backdrop } from "../../components";
import Slider from "react-slick";
import "./style.css";
import { books } from "../../books";

function BooksCarousel() {
  const sliderSetting = {
    slidesToShow: 3,
    slidesToScroll: 1,
    initialSlide: 0,
    centerMode: true,
    variableWidth: true,
    arrows: true,
    dots: false,
    speed: 300,
    centerPadding: "0px",
    infinite: true,
    autoplaySpeed: 2500,
    autoplay: true,
  };

  return (
    <div id="book-carousel">
      <Slider {...sliderSetting} >
        {books.map((book, index) => (
          <div key={index} className="book-carousel-item">
            <div
              className="image"
              style={{ backgroundImage: `url(${book.image_url1})` }}
            >
              <div className="description">
                <h3 className="title text-bold">{book.title}</h3>
                <p className="author">{book.author}</p>
              </div>
            </div>
          </div>
        ))}
      </Slider>
    </div>
  );
}

function BooksList() {
  return (
    <div id="book-list">
      <h2 className="text-bold ml-3">Book List</h2>
      <div className="book-list-container">
        {books.map((book, index) => (
          <>
            <div className="book-list-item">
              <div
                className="image"
                style={{backgroundImage: `url(${book.image_url1})`, cursor: 'pointer'}}
                // onClick="location.href='dilan.html'"
              >
                <div className="description">
                  <h5 className="title text-bold text-center">{book.title}</h5>
                  <p className="short">
                    {book.description}
                  </p>
                </div>
              </div>
            </div>
          </>
        ))}
      </div>
    </div>
  );
}

const slideRight = keyframes`
  0% {
    transform:translateX(-300px) !important;
  }

  100%{
    opacity: 1;
  }
`;
const Section = styled.section(({ visible }) => {
  return {
    transition: ".3s cubic-bezier(.86,0,.07,1)",
    "@media (max-width: 1280px)": {
      animation: visible ? slideRight + ".3s " : "none",
      transform: !visible ? "translateX(-300px)" : "none",
      visibility: visible ? "visible" : "hidden",
    },
  };
});

export default function Dashboard() {
  const [backdropOption, setBackdrop] = useState({
    visible: false,
    zIndex: 10,
  });
  const visibility = backdropOption.visible;

  const backdrop = (visible, zIndex = backdropOption.zIndex) => {
    setBackdrop({ visible, zIndex });
  };
  return (
    <>
      <Backdrop option={backdropOption} onClick={() => backdrop(false)} />
      <header
        className="column navbar"
        style={{ transition: "width .3s ease" }}
      >
        <div className="navbar-center ml-3">
          <div className="side-menu show-xl">
            <a
              className="text-dark text-bold btn btn-link mr-2"
              onClick={() => backdrop(true, 10)}
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
      <Section id="sidebar" visible={visibility}>
        <div className="container">
          <div className="profile">
            {/* <img src="assets/profile.png" /> */}
            <img src="https://pbs.twimg.com/media/D7ShRPYXoAA-XXB.jpg" />
            <h3>NIKI ZEFANYA</h3>
            <a href="login.html">&#xf08b; Logout</a>
          </div>

          <div className="nav">
            <a href="#" className="text-dark text-bold btn btn-link">
              Explore
            </a>
            <a href="#" className="text-dark text-bold btn btn-link">
              History
            </a>

            <a
              onClick={() => backdrop(true, 11)}
              id="add-book"
              className="text-dark text-bold btn btn-link"
            >
              Add book *
            </a>
          </div>
        </div>
      </Section>
      <main>
        <BooksCarousel />
        <BooksList />
      </main>
    </>
  );
}
