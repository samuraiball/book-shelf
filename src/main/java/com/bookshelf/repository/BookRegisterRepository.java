package com.bookshelf.repository;

import com.bookshelf.entity.BookEntity;
import org.seasar.doma.Dao;
import org.seasar.doma.Insert;
import org.seasar.doma.boot.ConfigAutowireable;

@Dao
@ConfigAutowireable
public interface BookRegisterRepository {
    @Insert
    int insert(BookEntity bookEntity);
}
