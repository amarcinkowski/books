package io.github.amarcinkowski.book.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import io.github.amarcinkowski.book.domain.Book;

@Transactional
public interface BookRepository extends CrudRepository<Book, Long> {

}
