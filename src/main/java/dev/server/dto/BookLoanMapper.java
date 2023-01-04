package dev.server.dto;

import dev.server.entity.BookLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookLoanMapper {
    BookLoanMapper INSTANCE = Mappers.getMapper(BookLoanMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "bookTitle")
    BookLoanDto bookLoanDto(BookLoan bookLoan);
}

