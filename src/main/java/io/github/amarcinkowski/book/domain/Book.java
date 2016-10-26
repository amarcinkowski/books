package io.github.amarcinkowski.book.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Book Entity
 * 
 * Calibre CSV fileds: authors series series_index timestamp tags formats isbn
 * identifiers languages #kanon comments library_name rating cover pubdate
 * #przeczytane size author_sort title title_sort publisher #chomik #format id
 * #kiedy uuid
 * 
 * @author amarcinkowski
 *
 */
@Entity
public class Book {

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String authors;

	@Column
	private String isbn;

	@Column
	private String title;

	@Column
	private String publisher;

	@Column
	private String series;

	@Column
	private Integer seriesIndex;

	@Column
	private Integer canon;

	@Column
	private boolean read = false;

	@Column
	private Date readDate;

	public Book() {
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthors() {
		return authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getSeriesIndex() {
		return seriesIndex;
	}

	public void setSeriesIndex(Integer seriesIndex) {
		this.seriesIndex = seriesIndex;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public String getTitle() {
		return title;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public boolean isRead() {
		return read;
	}
	
	public void setCanon(Integer canon) {
		this.canon = canon;
	}
	
	public Integer getCanon() {
		return canon;
	}

}
