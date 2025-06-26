import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isIssued;
    private User issuedTo;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
        this.issuedTo = null;
    }

    public void issueBook(User user) {
        isIssued = true;
        issuedTo = user;
    }

    public void returnBook() {
        isIssued = false;
        issuedTo = null;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public User getIssuedTo() {
        return issuedTo;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        if (isIssued) {
            return title + " by " + author + " (Issued to: " + issuedTo + ")";
        } else {
            return title + " by " + author + " (Available)";
        }
    }
}

class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name;
    }
}

public class Librarymanagement {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book);
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("User added: " + user);
    }

    public void showAvailableBooks() {
        System.out.println("Available books:");
        boolean found = false;
        for (Book b : books) {
            if (!b.isIssued()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }

    public void showIssuedBooks() {
        System.out.println("Issued books:");
        boolean found = false;
        for (Book b : books) {
            if (b.isIssued()) {
                System.out.println(b);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books are currently issued.");
        }
    }

    public void showAllBooks() {
        System.out.println("All books in library:");
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void showAllUsers() {
        System.out.println("All users:");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (User u : users) {
            System.out.println(u);
        }
    }

    public User findUserById(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId) {
                return u;
            }
        }
        return null;
    }

    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    public void issueBookToUser(String title, int userId) {
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book is already issued.");
            return;
        }
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        book.issueBook(user);
        System.out.println("Book '" + title + "' issued to " + user);
    }

    public void userIssueBook(String title, int userId) {
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (book.isIssued()) {
            System.out.println("Book is already issued.");
            return;
        }
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("User not found. Please contact admin.");
            return;
        }
        book.issueBook(user);
        System.out.println("Book '" + title + "' issued to you (" + user + ")");
    }

    public void returnBookByUser(String title, int userId) {
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("Book was not issued.");
            return;
        }
        if (book.getIssuedTo().getUserId() != userId) {
            System.out.println("You cannot return a book you did not issue.");
            return;
        }
        book.returnBook();
        System.out.println("Book '" + title + "' returned successfully.");
    }

    public void adminReturnBook(String title) {
        Book book = findBookByTitle(title);
        if (book == null) {
            System.out.println("Book not found.");
            return;
        }
        if (!book.isIssued()) {
            System.out.println("Book was not issued.");
            return;
        }
        book.returnBook();
        System.out.println("Book '" + title + "' returned successfully.");
    }

    public static void main(String[] args) {
        Librarymanagement library = new Librarymanagement();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Welcome to the Library System ---");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Select role: ");

            int roleChoice = -1;
            try {
                roleChoice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }

            if (roleChoice == 3) {
                System.out.println("Goodbye!");
                break;
            }

            switch (roleChoice) {
                case 1:
                    while (true) {
                        System.out.println("\n--- Admin Menu ---");
                        System.out.println("1. Add Book");
                        System.out.println("2. Add User");
                        System.out.println("3. View All Books");
                        System.out.println("4. View All Users");
                        System.out.println("5. Issue Book to User");
                        System.out.println("6. Return Book");
                        System.out.println("7. Logout");
                        System.out.print("Choose an option: ");

                        int adminChoice = -1;
                        try {
                            adminChoice = Integer.parseInt(sc.nextLine());
                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                            continue;
                        }

                        if (adminChoice == 7) {
                            System.out.println("Logging out Admin...");
                            break;
                        }

                        switch (adminChoice) {
                            case 1:
                                System.out.print("Enter book title: ");
                                String title = sc.nextLine();
                                System.out.print("Enter author name: ");
                                String author = sc.nextLine();
                                library.addBook(new Book(title, author));
                                break;
                            case 2:
                                System.out.print("Enter user ID (number): ");
                                int userId = -1;
                                try {
                                    userId = Integer.parseInt(sc.nextLine());
                                } catch (Exception e) {
                                    System.out.println("Invalid user ID.");
                                    break;
                                }
                                System.out.print("Enter user name: ");
                                String userName = sc.nextLine();
                                library.addUser(new User(userId, userName));
                                break;
                            case 3:
                                library.showAllBooks();
                                break;
                            case 4:
                                library.showAllUsers();
                                break;
                            case 5:
                                System.out.print("Enter book title to issue: ");
                                String issueTitle = sc.nextLine();
                                System.out.print("Enter user ID to issue to: ");
                                int issueUserId = -1;
                                try {
                                    issueUserId = Integer.parseInt(sc.nextLine());
                                } catch (Exception e) {
                                    System.out.println("Invalid user ID.");
                                    break;
                                }
                                library.issueBookToUser(issueTitle, issueUserId);
                                break;
                            case 6:
                                System.out.print("Enter book title to return: ");
                                String returnTitle = sc.nextLine();
                                library.adminReturnBook(returnTitle);
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter your user ID: ");
                    int currentUserId = -1;
                    try {
                        currentUserId = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Invalid user ID.");
                        break;
                    }
                    User currentUser = library.findUserById(currentUserId);
                    if (currentUser == null) {
                        System.out.println("User ID not found. Contact admin.");
                        break;
                    }

                    while (true) {
                        System.out.println("\n--- User Menu ---");
                        System.out.println("1. View Available Books");
                        System.out.println("2. View Issued Books");
                        System.out.println("3. Issue (Take) a Book");
                        System.out.println("4. Return a Book");
                        System.out.println("5. Logout");
                        System.out.print("Choose an option: ");

                        int userChoice = -1;
                        try {
                            userChoice = Integer.parseInt(sc.nextLine());
                        } catch (Exception e) {
                            System.out.println("Invalid input.");
                            continue;
                        }

                        if (userChoice == 5) {
                            System.out.println("Logging out user...");
                            break;
                        }

                        switch (userChoice) {
                            case 1:
                                library.showAvailableBooks();
                                break;
                            case 2:
                                library.showIssuedBooks();
                                break;
                            case 3:
                                System.out.print("Enter book title to issue: ");
                                String issueBookTitle = sc.nextLine();
                                library.userIssueBook(issueBookTitle, currentUserId);
                                break;
                            case 4:
                                System.out.print("Enter book title to return: ");
                                String returnBookTitle = sc.nextLine();
                                library.returnBookByUser(returnBookTitle, currentUserId);
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid role option.");
            }
        }

        sc.close();
    }
}
