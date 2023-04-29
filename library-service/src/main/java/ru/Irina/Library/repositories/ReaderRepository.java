package ru.Irina.Library.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.Irina.Library.entities.Reader;

import java.util.List;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Page<Reader> findAll(Pageable pageRequest);

    @Query("SELECT r FROM Reader r WHERE LOWER(r.readerName) LIKE LOWER(CONCAT('%',:name,'%'))")
    Page<Reader> findByNamePage(String name, Pageable pageRequest);

    @Query("SELECT r FROM Reader r WHERE LOWER(r.readerName) LIKE LOWER(CONCAT('%',:name,'%'))")
    List<Reader> findByName(String name);
}

