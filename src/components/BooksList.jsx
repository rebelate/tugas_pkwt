
import booksAtom from "../books";
import { useAtomValue } from "jotai";
import { useLocation } from "wouter";
export default 
function BooksList() {
  const [location, setLocation] = useLocation();
  const books = useAtomValue(booksAtom);
  return (
    <div id="book-list">
      <h2 className="text-bold ml-3">Book List</h2>
      <div className="book-list-container">
        {Array.from(books).map(([key, book]) => (
          <div key={key} className="book-list-item">
            <div
              style={{ cursor: "pointer" }}
              onClick={() => (setLocation("/books/" + key))}
            >
              <div
                className="image"
                style={{
                  backgroundImage: `url(${book.image_url1})`,
                }}
              />
              <div className="description">
                <h5 className="title text-bold text-center">{book.title}</h5>
                <p className="short">{book.description}</p>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
