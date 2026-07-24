import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        boolean running = true;
        while (running) {
            System.out.println("\n=========================================");
            System.out.println("      📖 LIBRARY MANAGEMENT SYSTEM 📖      ");
            System.out.println("=========================================");
            System.out.println("  1 - Add Book");
            System.out.println("  2 - Display Book");
            System.out.println("  3 - Search Book");
            System.out.println("  4 - Exit");
            System.out.println("=========================================");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
            } catch (InputMismatchException e) {
                System.out.println("\n❌ Error: Invalid input! Please enter a valid number (1-4).");
                scanner.nextLine(); // clear the invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n------------- ➕ Add Book -------------");
                    String title = "";
                    while (title.trim().isEmpty()) {
                        System.out.print("Enter title: ");
                        title = scanner.nextLine();
                        if (title.trim().isEmpty()) {
                            System.out.println("❌ Error: Title cannot be empty.");
                        }
                    }

                    String author = "";
                    while (author.trim().isEmpty()) {
                        System.out.print("Enter author: ");
                        author = scanner.nextLine();
                        if (author.trim().isEmpty()) {
                            System.out.println("❌ Error: Author cannot be empty.");
                        }
                    }

                    int year = -1;
                    while (year < 0) {
                        System.out.print("Enter year: ");
                        try {
                            year = scanner.nextInt();
                            scanner.nextLine(); // consume newline
                            if (year < 0 || year > 2100) {
                                System.out.println("❌ Error: Please enter a realistic year.");
                                year = -1;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("❌ Error: Invalid input! Year must be a number.");
                            scanner.nextLine(); // clear the invalid input
                        }
                    }

                    library.addBook(new Book(title, author, year));
                    System.out.println("✅ Book added successfully!");
                    break;

                case 2:
                    System.out.println("\n------------- 📚 Book List -------------");
                    if (library.getBooks().isEmpty()) {
                        System.out.println("No books in the library yet.");
                    } else {
                        System.out.printf("%-30s %-20s %-10s\n", "Title", "Author", "Year");
                        System.out.println("------------------------------------------------------------------");
                        for (Book book : library.getBooks()) {
                            System.out.printf("%-30s %-20s %-10d\n", 
                                truncate(book.getTitle(), 28), 
                                truncate(book.getAuthor(), 18), 
                                book.getYear());
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n------------ 🔍 Search Book ------------");
                    System.out.print("Enter a book to search: ");
                    String searchTitle = scanner.nextLine();

                    if (searchTitle.trim().isEmpty()) {
                        System.out.println("❌ Error: Search title cannot be empty.");
                        break;
                    }

                    Book foundBook = library.searchBookByTitle(searchTitle);
                    if (foundBook != null) {
                        System.out.println("✅ Book found!");
                        System.out.println("Title: " + foundBook.getTitle());
                        System.out.println("Author: " + foundBook.getAuthor());
                        System.out.println("Year: " + foundBook.getYear());
                    } else {
                        System.out.println("❌ Book not found!");
                    }
                    break;

                case 4:
                    System.out.println("\nExiting Library Management System. Goodbye! 👋\n");
                    scanner.close();
                    running = false;
                    break;

                default:
                    System.out.println("\n❌ Error: Invalid option. Please select 1, 2, 3, or 4.");
            }
        }
    }

    // Helper method to gracefully truncate long strings for the table display
    private static String truncate(String str, int length) {
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length - 3) + "...";
    }
}
