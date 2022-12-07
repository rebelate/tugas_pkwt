import { useSelector } from "react-redux";
import Slider from "react-slick";

export function BookCarousel() {
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
  const books = useSelector((state) => state.book.list);
  // console.log(books);
  return (
    <section id="book-carousel">
      <Slider {...sliderSetting}>
        {books.map((book) => (
          <div key={book.id} className="book-carousel-item">
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
    </section>
  );
}
