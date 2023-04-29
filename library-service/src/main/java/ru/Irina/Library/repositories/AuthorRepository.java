package ru.Irina.Library.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.Irina.Library.entities.Author;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    Page<Author> findAll(Pageable pageRequest);

    @Query("SELECT a FROM Author a WHERE LOWER(a.authorName) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Author> findByNamePage(@Param("name") String name, Pageable pageRequest);

    @Query("SELECT a FROM Author a WHERE LOWER(a.authorName) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Author> findByName(@Param("name") String name);
}
