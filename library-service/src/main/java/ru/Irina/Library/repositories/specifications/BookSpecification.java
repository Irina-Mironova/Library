package ru.Irina.Library.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.Irina.Library.entities.Book;

public class BookSpecification {

    public static Specification<Book> titleContains(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("title")), String.format("%%%s%%", title).toLowerCase());
    }

    public static Specification<Book> authorNameContains(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.join("author").get("authorName")),
                        String.format("%%%s%%", name).toLowerCase()
                );
    }
}


