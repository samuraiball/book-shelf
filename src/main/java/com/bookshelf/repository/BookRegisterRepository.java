package com.bookshelf.repository;

import com.bookshelf.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRegisterRepository extends JpaRepository<BookEntity, String> {
    BookEntity findBookById(String bookId);
}
