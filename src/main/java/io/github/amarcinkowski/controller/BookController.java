package io.github.amarcinkowski.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.amarcinkowski.book.domain.Book;
import io.github.amarcinkowski.book.service.BookService;

@Controller
public class BookController {

	@Autowired
	public BookService bookService;

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("books", bookService.findAll());
		return "index";
	}

	@RequestMapping("/add")
	public String add(Model model) {
		Book book = new Book();
		book.setAuthor("T. Pratchett");
		book.setTitle("Blask Fantastyczny");
		book.setSeries("Świat Dysku");
		bookService.add(book);
		model.addAttribute("books", bookService.findAll());
		return "index";
	}

}
