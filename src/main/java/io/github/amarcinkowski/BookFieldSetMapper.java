package io.github.amarcinkowski;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import io.github.amarcinkowski.book.domain.Book;

public class BookFieldSetMapper implements FieldSetMapper<Book> {

	@Override
	public Book mapFieldSet(FieldSet fs) throws BindException {
		if (fs == null) {
			return null;
		}

		Book book = new Book();
		book.setAuthors(fs.readString("authors"));
		book.setTitle(fs.readString("title"));
		book.setSeries(fs.readString("series"));
		book.setSeriesIndex(fs.readInt("series_index"));
		book.setIsbn(fs.readString("isbn"));
		book.setPublisher(fs.readString("publisher"));
		String bool = fs.readString("#przeczytane").toLowerCase();
		book.setRead(Boolean.parseBoolean(bool));
		String date = fs.readString("#kiedy");
		if (date.length() > 0) {
			TemporalAccessor ta = DateTimeFormatter.ISO_OFFSET_DATE_TIME.parse(date);
			book.setReadDate(Date.from(Instant.from(ta)));
		}
		book.setCanon(fs.readInt("#kanon"));

		return book;
	}

}
