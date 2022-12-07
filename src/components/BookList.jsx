import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useLocation } from "@/hooks/locationHook";
import { BookCard } from "./BookCard";
import { SelectSort } from "./SelectSort";
import ReactPaginate from "react-paginate";

function Items({ currentItems }) {
  return (
    <>
      {currentItems &&
        currentItems.map((book) => <BookCard key={book.id} book={book} />)}
    </>
  );
}

export function BookList() {
  const [, setLocation, query] = useLocation();
  let books = useSelector((state) => state.book.list);

  const [itemOffset, setItemOffset] = useState(0);
  const itemsPerPage = 4;
  const endOffset = itemOffset + itemsPerPage;
  const handlePageClick = (event) => {
    setLocation(`?search=${query.search}&sort=${query.sort}&page=${event.selected+1}`);
  };
  const [forcePage, setForcePage] = useState(0);
  useEffect(() => {
    if (query.page) {
      const newOffset = ((query.page -1) * itemsPerPage) % books.length;
      setItemOffset(newOffset);
      setForcePage(query.page -1);
    }
  }, [query.page]);

  if (query.base) {
    if (query.search) {
      books = books.filter((book) =>
        book.title.toLowerCase().includes(query.search)
      );
    }
    if (query.sort === "oldest") {
      books = books.slice().sort((a, b) => new Date(a.date) - new Date(b.date));
    } else if (query.sort === "latest") {
      books = books.slice().sort((a, b) => new Date(b.date) - new Date(a.date));
    }

    if (query.sort === "name_asc") {
      books = books.slice().sort(
        (a, b) => a.title.localeCompare(b.title)
        // if (a.title < b.title) return -1;
        // if (a.title > b.title) return 1;

        // return 0;
      );
    } else if (query.sort === "name_desc") {
      books = books.slice().sort(
        (a, b) => -1 * a.title.localeCompare(b.title)
        // if (a.title > b.title) return -1;
        // if (a.title < b.title) return 1;

        // return 0;
      );
    }
  }
  const currentItems = books.slice(itemOffset, endOffset);
  const pageCount = Math.ceil(books.length / itemsPerPage);
  return (
    <section id="book-card">
      <div className="columns flex-centered">
        <div className="column">
          <h2 className="text-bold ml-3">Book List</h2>
        </div>
        <div className="column" style={{ flex: "0 0 15%" }}>
          <SelectSort />
        </div>
      </div>
      <div className="book-card-container">
        <div className="grid">
          <Items currentItems={currentItems} />
        </div>
        <ReactPaginate
          pageClassName="page-item"
          pageLinkClassName="page-link"
          previousClassName="page-item"
          previousLinkClassName="page-link"
          nextClassName="page-item"
          nextLinkClassName="page-link"
          breakClassName="page-item"
          breakLinkClassName="page-link"
          containerClassName="pagination"
          activeClassName="active"
          breakLabel="..."
          nextLabel="next >"
          onPageChange={handlePageClick}
          pageRangeDisplayed={5}
          pageCount={pageCount}
          previousLabel="< previous"
          renderOnZeroPageCount={null}
          forcePage={forcePage}
        />
      </div>
    </section>
  );

  return (
    <section id="book-card">
      <div className="columns flex-centered">
        <div className="column">
          <h2 className="text-bold ml-3">Book List</h2>
        </div>
        <div className="column" style={{ flex: "0 0 15%" }}>
          <SelectSort />
        </div>
      </div>
      <div className="book-card-container">
        <div className="grid">{books}</div>
      </div>
    </section>
  );
}
