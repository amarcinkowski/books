package io.github.amarcinkowski.util.csv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.separator.DefaultRecordSeparatorPolicy;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.Resource;

import io.github.amarcinkowski.book.domain.Book;

public class BooksFlatFileReader {

	private final static String[] COLUMNS = new String[] { "authors", "series", "series_index", "timestamp", "tags",
			"formats", "isbn", "identifiers", "languages", "#kanon", "comments", "library_name", "rating", "cover",
			"pubdate", "#przeczytane", "size", "author_sort", "title", "title_sort", "publisher", "#chomik", "#format",
			"id", "#kiedy", "uuid" };

	public static List<Book> fillFromCSV(Resource resource) throws UnexpectedInputException, ParseException, Exception {
		List<Book> books = new ArrayList<>();
		FlatFileItemReader<Book> itemReader = new FlatFileItemReader<Book>();
		itemReader.setResource(resource);
		itemReader.setLinesToSkip(1);
		itemReader.setRecordSeparatorPolicy(new DefaultRecordSeparatorPolicy());
		DefaultLineMapper<Book> lineMapper = new DefaultLineMapper<Book>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(COLUMNS);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(new BookFieldSetMapper());
		itemReader.setLineMapper(lineMapper);
		itemReader.open(new ExecutionContext());
		Book book;
		while ((book = itemReader.read()) != null) {
			books.add(book);
		}
		return books;
	}

}
