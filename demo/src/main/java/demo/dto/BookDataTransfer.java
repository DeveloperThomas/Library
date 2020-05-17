package demo.dto;

import java.time.LocalDate;
import java.util.List;

public class BookDataTransfer {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public boolean getRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    private Long id;
    private String title;
    private LocalDate publicationDate;
    private boolean rented;

    public List<AuthorDataTransfer> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDataTransfer> authors) {
        this.authors = authors;
    }

    private List<AuthorDataTransfer> authors;

    public BookDataTransfer(Long id, String title, LocalDate publicationDate, boolean rented, List<AuthorDataTransfer> authors) {
        this.id = id;
        this.title = title;
        this.publicationDate = publicationDate;
        this.rented = rented;
        this.authors = authors;
    }
}
