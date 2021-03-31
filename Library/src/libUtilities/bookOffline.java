package libUtilities;

final class bookOffline extends book {

    private section bookSection;

    public bookOffline(String title, String author, section sec, String publisher, String genre, Integer numberOfPages) {
        super(title, author, publisher, genre, numberOfPages);
        bookSection = sec;
    }

    public section getBookSection() {
        return bookSection;
    }

    public void setBookSection(section bookSection) {
        this.bookSection = bookSection;
    }
}
