package io.github.amarcinkowski.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.amarcinkowski.book.domain.Book;
import io.github.amarcinkowski.book.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		List<Book> target = new ArrayList<>();
		bookRepository.findAll().forEach(target::add);
		return target;
	}

	public Book findById(final Integer id) {
		return bookRepository.findOne(id);
	}

	public void add(Book book) {
		bookRepository.save(book);
	}
	
	public void deleteAll() {
		bookRepository.deleteAll();
	}
}
