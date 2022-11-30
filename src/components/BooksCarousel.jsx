import booksAtom from "../books";
import { useAtomValue } from "jotai";
import Slider from "react-slick";

export default function BooksCarousel() {
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
  const books = useAtomValue(booksAtom);

  return (
    <div id="book-carousel">
      <Slider {...sliderSetting}>
        {Array.from(books).map(([key, book]) => (
          <div key={key} className="book-carousel-item">
            <div
              className="image"
              style={{ backgroundImage: `url(${book.image_url1})` }}
            >
              <div className="description">
                <h2 className="title text-bold">{book.title}</h2>
                <p className="author">{book.author}</p>
              </div>
            </div>
          </div>
        ))}
      </Slider>
    </div>
  );
}
