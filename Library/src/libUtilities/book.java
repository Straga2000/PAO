package libUtilities;

import java.util.Objects;

public class book implements Comparable<book>{

    private static Integer counter = 0;
    private Integer id;

    protected String title = "", author = "", publisher = "", genre = "";
    protected Integer numberOfPages;

    public book(String title, String author, String publisher, String genre, Integer numberOfPages) {
        this.id = counter;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.numberOfPages = numberOfPages;

        counter++;
    }

    public book() {
        this.id = counter++;
    }

    book searchCategory(String category)
    {
        if(genre.equals(category))
            return this;
        return null;
    }

    book searchPublisher(String publisher)
    {
        if(this.publisher.equals(publisher))
            return this;
        return null;
    }

    //Getter and setter start
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }
    //Getter and setter end


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof book)) return false;
        book book = (book) o;
        return  Objects.equals(getTitle(), book.getTitle()) &&
                Objects.equals(getAuthor(), book.getAuthor()) &&
                Objects.equals(getPublisher(), book.getPublisher()) &&
                Objects.equals(getGenre(), book.getGenre()) &&
                Objects.equals(getNumberOfPages(), book.getNumberOfPages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTitle(), getAuthor(), getPublisher(), getGenre(), getNumberOfPages());
    }

    @Override
    public int compareTo(book o) {
        return this.getTitle().compareTo(o.getTitle());
    }

    @Override
    public String toString() {
        return "Book number #" + id + " with title \'" + title + "\' has " + numberOfPages + " pages, is written by "
                + author + " and published at " + publisher + "\n";
    }
}

