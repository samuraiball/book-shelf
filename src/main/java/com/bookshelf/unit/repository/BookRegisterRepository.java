package com.bookshelf.unit.repository;

import com.bookshelf.unit.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRegisterRepository extends JpaRepository<BookEntity, Integer> {
    BookEntity findById(long bookId);
}
