import libUtilities.*;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        library Lib = library.getInstance();

        //addNewBook(scan, Lib);

        makeAccount(scan, Lib);
        upgradeAccount(scan, Lib);

        orderBooksByTitle(Lib);
        searchCategory(scan, Lib);
        searchPublisher(scan, Lib);
        addBookToHistory(scan, Lib);

        isSubscriptionPaid(scan, Lib);
        cancelSubscription(scan, Lib);
        deleteAccount(scan, Lib);
    }

    private static reader makeAccount(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();
        Integer typeOfMembership = scan.nextInt();
        scan.nextLine();

        Boolean isVIP = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;
        //System.out.println(isVIP);

        reader user = Lib.findUserByName(name);

        if(user == null)
            user = Lib.makeAccount(name, typeOfMembership, isVIP);
        else
            throw new Exception("User already exist");

        System.out.println(user.toString());
        return user;
    }

    private static reader upgradeAccount(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();

        Integer typeOfMembership = scan.nextInt();
        scan.nextLine();

        Boolean isVIP = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;
        //System.out.println(isVIP);

        reader user = Lib.findUserByName(name);

        if(user != null)
            user = Lib.upgradeAccount(typeOfMembership, isVIP, user);
        else
            throw new Exception("User not found");

        Lib.updateUser(user);
        System.out.println(user.toString());
        return user;
    }

    private static List<reader> deleteAccount(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();

        reader user = Lib.findUserByName(name);

        if(user == null)
            throw new Exception("User not found");
        else
            Lib.deleteAccount(user);

        System.out.println(user.toString());
        return Lib.getUsers();
    }


    private static reader cancelSubscription(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();
        Boolean confirmation = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;

        reader user = Lib.findUserByName(name);

        if (confirmation){

            if(user == null)
                throw new Exception("User not found");
            else
                Lib.cancelSubscription(user);
        }

        return user;
    }

    private static Boolean isSubscriptionPaid(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();
        Boolean confirmation = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;

        reader user = Lib.findUserByName(name);

        if (confirmation){
            if(user == null)
                throw new Exception("User not found");
            else
                return Lib.isSubscriptionPaid(user);
        }

        throw new Exception("Subscription not found");
    }

    private static List<book> orderBooksByTitle(library Lib)
    {
        List<book> newList = Lib.getBooks();
        Collections.sort(newList);

        for(book b : newList)
        {
            System.out.println(b.toString());
        }

        return newList;
    }

    private static List<book> searchCategory(Scanner scan, library Lib)
    {
        String category = scan.nextLine();
        return Lib.searchCategory(category, Lib.getBooks());
    }

    private static List<book> searchPublisher(Scanner scan, library Lib)
    {
        String publisher = scan.nextLine();
        return Lib.searchPublisher(publisher, Lib.getBooks());
    }

    private static reader addBookToHistory(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();
        String bookName = scan.nextLine();

        for(reader user : Lib.getUsers())
        {
            if (user.getFullName().equals(name)) {

                for(book b : Lib.getBooks())
                {
                    if(b.getTitle().equals(bookName))
                    {
                        Lib.addBookToHistory(user, b);
                        return user;
                    }
                }
            }
        }

        throw new Exception("User or book not found.");
    }

    private static List<book> addNewBook(Scanner scan, library Lib) throws Exception {
        String bookName = scan.nextLine();
        String author = scan.nextLine();
        String publisher = scan.nextLine();
        String genre = scan.nextLine();

        Integer number = scan.nextInt();
        scan.nextLine();

        book newBook = new book(bookName, author, publisher, genre, number);
        book findBook = Lib.findBookByObject(newBook);

        if(findBook == null)
            return Lib.addBook(newBook);
        else
            throw  new Exception("Book already exists");
    }
}
