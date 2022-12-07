import { useLocation } from "@/hooks/locationHook";

export function BookCard({ book }) {
  const [, setLocation] = useLocation();
  return (
    <div key={book.id} className="book-card-item">
      <div
        style={{ cursor: "pointer" }}
        onClick={() => setLocation("/books/" + book.id)}
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
  );
}
