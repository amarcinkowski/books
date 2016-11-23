package io.github.amarcinkowski.controller;

import java.util.List;
import java.util.TreeSet;

import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.amarcinkowski.book.domain.Book;
import io.github.amarcinkowski.book.service.BookService;
import io.github.amarcinkowski.util.csv.BooksFlatFileReader;

@Controller
public class BookController {

	@Autowired
	public BookService bookService;

	private void getSortedBooks(Model model) {
		TreeSet<Book> sortedBooks = new TreeSet<>(bookService.findAll());
		model.addAttribute("books", sortedBooks);
	}

	@RequestMapping("/")
	public String index(Model model) {
		getSortedBooks(model);
		return "index";
	}

	@RequestMapping("/del")
	public String index() {
		bookService.deleteAll();
		return "index";
	}

	@RequestMapping("/add")
	public String add(Model model) throws UnexpectedInputException, ParseException, Exception {
		if (bookService.findAll().isEmpty()) {
			String file = "static/books.csv";
			
			List<Book> books = BooksFlatFileReader.fillFromCSV(new ClassPathResource(file));
			bookService.addAll(books);
		}
		getSortedBooks(model);
		return "index";
	}

}
