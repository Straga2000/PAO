package libUtilities;
import java.util.*;

public class library {

    private List<reader> users = new ArrayList<>();
    private List<book> books = new ArrayList<>();
    private Map<String, section> sectionByGenre = new HashMap<String, section>();
    private Map<String, author> sectionByAuthor = new HashMap<String, author>();

    ////we need to create a map of author name with his books
    public Map<String, author> divideBooksByAuthor(List<book> listOfBooks)
    {
        for(book obj : listOfBooks)
        {
            String author = obj.getAuthor();
            author authorLibrary = sectionByAuthor.get(author);

            if(authorLibrary != null)
            {
                List<book> renewList = authorLibrary.getBooks();
                renewList.add(obj);
                authorLibrary.setBooks(renewList);

                sectionByAuthor.put(author, authorLibrary);
            }
            else
            {
                List<book> newList = new ArrayList<book>();
                newList.add(obj);

                authorLibrary = new author(author, newList);
                sectionByAuthor.put(author, authorLibrary);
            }
        }

        return sectionByAuthor;
    }


    public Map<String, section> divideBooksByGenre(List<book> listOfBooks)
    {
        for(book obj : listOfBooks)
        {
            String genre = obj.getGenre();
            section genreSection = sectionByGenre.get(genre);

            if(genreSection != null)
            {
                List<book> renewList = genreSection.getBookList();
                renewList.add(obj);
                genreSection.setBookList(renewList);

                sectionByGenre.put(genre, genreSection);
            }
            else
            {
                List<book> newList = new ArrayList<book>();
                newList.add(obj);

                genreSection = new section(genre, newList);
                sectionByGenre.put(genre, genreSection);
            }
        }

        return sectionByGenre;
    }

    public List<book> getBooks() {
        return books;
    }

    public void setBooks(List<book> books) {
        this.books = books;
    }

    public List<reader> getUsers() {
        return users;
    }

    public void setUsers(List<reader> users) {
        this.users = users;
    }

    public List<book> searchCategory(String category, List<book> listOfBooks)
    {
        List<book> newListOfBooks = new ArrayList<book>();
        for(book x : listOfBooks)
        {
            book result = x.searchCategory(category);
            if(result != null)
                newListOfBooks.add(result);
        }

        return newListOfBooks;
    }

    public List<book> searchPublisher(String publisher, List<book> listOfBooks)
    {
        List<book> newListOfBooks = new ArrayList<book>();
        for(book x : listOfBooks)
        {
            book result = x.searchPublisher(publisher);
            if(result != null)
                newListOfBooks.add(result);
        }

        return newListOfBooks;
    }

    ///use to create account, returns account
    ///membership 1,2,3
    public reader makeAccount(String name,Integer typeOfMembership, Boolean isVIP) throws Exception {

        reader newReader = new reader(name, new membership());
        newReader.setSubscription(newReader.createSubscription(typeOfMembership, isVIP));
        users.add(newReader);

        return newReader;
    }

    public reader upgradeAccount(Integer typeOfMembership, Boolean isVIP, reader user) throws Exception {

        membership subscription = user.upgradeSubscription(typeOfMembership, isVIP);
        user.setSubscription(subscription);
        return user;
    }

    public reader cancelSubscription(reader user) throws Exception {

                user.cancelSubscription();
                return user;
    }

    public boolean isSubscriptionPaid(reader user) throws Exception {
        return user.isSubscriptionNeededToPaid();
    }

    public List<reader> deleteAccount(reader user)
    {
        this.users.remove(user);
        return users;
    }

    public reader addBookToHistory(reader user, book newBook)
    {
        user.addBookToHistory(newBook);
        return user;
    }

    public List<book> addBook(book addedBook)
    {
        books.add(addedBook);
        return books;
    }

    public List<book> deleteBook(book deletedBook)
    {
        books.remove(deletedBook);
        return books;
    }

    public List<book> deleteBook(Integer deletedId)
    {
        books.remove(deletedId);
        return books;
    }

//    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//    Date date = new Date();


}
