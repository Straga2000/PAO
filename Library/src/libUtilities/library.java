package libUtilities;
import javax.print.DocFlavor;
import java.util.*;

public class library {

    private List<reader> users = new ArrayList<>();
    private List<book> books = new ArrayList<>();
    private Map<String, section> sectionByGenre = new HashMap<String, section>();
    private Map<String, author> sectionByAuthor = new HashMap<String, author>();
    private List<action> controlLog = new ArrayList<>();

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

    public List<action> getControlLog() {
        return controlLog;
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

        addAction("search category " + category);

        return newListOfBooks;
    }

    public List<book> searchPublisher(String publisher, List<book> listOfBooks)
    {
        List<book> newListOfBooks = new ArrayList<>();
        for(book x : listOfBooks)
        {
            book result = x.searchPublisher(publisher);
            if(result != null)
                newListOfBooks.add(result);
        }

        addAction("search publisher " + publisher);

        return newListOfBooks;
    }

    ///use to create account, returns account
    ///membership 1,2,3
    public reader makeAccount(String name,Integer typeOfMembership, Boolean isVIP) throws Exception {

        reader newReader = new reader(name);
        newReader.createSubscription(typeOfMembership, isVIP);
        users.add(newReader);

        addAction("make account " + name);

        return newReader;
    }

    public reader upgradeAccount(Integer typeOfMembership, Boolean isVIP, reader user) throws Exception {

        user.upgradeSubscription(typeOfMembership, isVIP);
        addAction("upgrade account " + user.getFullName());

        return user;
    }

    public reader cancelSubscription(reader user) throws Exception {

        user.cancelSubscription();
        addAction("cancel subscription " + user.getFullName());

        return user;
    }

    public boolean isSubscriptionPaid(reader user) throws Exception {
        return user.isSubscriptionNeededToPaid();
    }

    public List<reader> deleteAccount(reader user)
    {
        this.users.remove(user);
        addAction("delete account " + user.getFullName());
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
        addAction("add book " + addedBook.getTitle());

        return books;
    }

    public List<book> deleteBook(book deletedBook)
    {
        books.remove(deletedBook);
        addAction("delete book " + deletedBook.getTitle());

        return books;
    }

    public List<book> deleteBook(Integer deletedId)
    {
        books.remove(deletedId);
        return books;
    }

    public reader findUserByName(String name)
    {
        for(reader searchReader : users)
        {
            if(searchReader.getFullName().equals(name))
                return searchReader;
        }

        return null;
    }

    public reader updateUser(reader user)
    {
        for(int i = 0; i < users.size(); i++)
            if(users.get(i).getFullName().equals(user.getFullName()))
            {
                users.set(i, user);
                return user;
            }

        return null;
    }

    public book findBookByObject(book findBook)
    {
        for(book newBook : books)
        {
            if(newBook.equals(findBook))
                return findBook;
        }

        return null;
    }

    public List<action> addAction(String actionName)
    {
        controlLog.add(new action(actionName));
        return controlLog;
    }
}
