import libUtilities.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);  // Create a Scanner object
        library Lib = new library();

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
        Boolean isVIP = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;

        reader user = Lib.makeAccount(name, typeOfMembership, isVIP);
        System.out.println(user.toString());

        return user;
    }

    private static reader upgradeAccount(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();
        Integer typeOfMembership = scan.nextInt();
        Boolean isVIP = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;

        for(reader user : Lib.getUsers())
        {
            if (user.getFullName().equals(name)) {
                reader newUser = Lib.upgradeAccount(typeOfMembership, isVIP, user);
                System.out.println(newUser.toString());
                return newUser;
            }
        }

        throw new Exception("There is no user with this name.");
    }

    private static List<reader> deleteAccount(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();

        for(reader user : Lib.getUsers())
        {
            if (user.getFullName().equals(name)) {
                return Lib.deleteAccount(user);
            }
        }

        throw new Exception("There is no user with this name.");
    }


    private static reader cancelSubscription(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();
        Boolean confirmation = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;

        if (confirmation){

            for(reader user : Lib.getUsers())
            {
                if (user.getFullName().equals(name))
                    return Lib.cancelSubscription(user);
            }

            throw new Exception("There is no user with this name.");
        }

        return null;
    }

    private static Boolean isSubscriptionPaid(Scanner scan, library Lib) throws Exception {
        String name = scan.nextLine();
        Boolean confirmation = scan.nextLine().equals("NO") ? Boolean.FALSE : Boolean.TRUE;

        if (confirmation){

            for(reader user : Lib.getUsers())
            {
                if (user.getFullName().equals(name))
                    return Lib.isSubscriptionPaid(user);
            }

            throw new Exception("There is no user with this name.");
        }

        return null;
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

        for(book b : Lib.getBooks())
        {
            if(b.equals(newBook))
                throw new Exception("This book already exists");
        }

        return Lib.addBook(newBook);
    }
}
