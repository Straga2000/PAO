package libUtilities;

final class bookOnline extends book {

    private static String readerLink;

    public bookOnline(String title, String author, String url,String publisher, String genre, Integer numberOfPages) {
        super(title, author, publisher, genre, numberOfPages);
        readerLink = url;
    }

    public static String getReaderLink() {
        return readerLink;
    }

    public static void setReaderLink(String readerLink) {
        bookOnline.readerLink = readerLink;
    }
}
