package dev.server.dto;

import dev.server.entity.BookLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookLoanMapper {
    BookLoanMapper INSTANCE = Mappers.getMapper(BookLoanMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "book.id", target = "bookId")
    BookLoanDto bookLoanDto(BookLoan bookLoan);
}

