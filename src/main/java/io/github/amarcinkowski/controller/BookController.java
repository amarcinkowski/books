package io.github.amarcinkowski.controller;

import java.util.Date;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import io.github.amarcinkowski.BookFieldSetMapper;
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

	@RequestMapping("/del")
	public String index() {
		bookService.deleteAll();
		return "index";
	}

	@RequestMapping("/add")
	public String add(Model model) throws UnexpectedInputException, ParseException, Exception {
		// Book book = new Book();
		// book.setAuthors("T. Pratchett");
		// book.setTitle("Blask Fantastyczny");
		// book.setSeries("Świat Dysku");
		// bookService.add(book);
		FlatFileItemReader<Book> itemReader = new FlatFileItemReader<Book>();
		itemReader.setResource(new FileSystemResource("/home/amarcinkowski/Pulpit/books.csv"));
		// for multiline reviews
		itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
		itemReader.setLinesToSkip(1);
		DefaultLineMapper<Book> lineMapper = new DefaultLineMapper<Book>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] { "authors", "series", "series_index", "timestamp", "tags", "formats",
				"isbn", "identifiers", "languages", "#kanon", "comments", "library_name", "rating", "cover", "pubdate",
				"#przeczytane", "size", "author_sort", "title", "title_sort", "publisher", "#chomik", "#format", "id",
				"#kiedy", "uuid" });
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(new BookFieldSetMapper());
		itemReader.setLineMapper(lineMapper);
		itemReader.open(new ExecutionContext());
		Book book;
		while((book = itemReader.read()) != null) {
			bookService.add(book);
		}
		model.addAttribute("books", bookService.findAll());
		return "index";
	}

}
