package crs;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Publisher[] publishers = new Publisher[0];
    private static Book[] books = new Book[0];

    public static void printAll(Book[] books) {
        if (books == null || books.length == 0) {
            System.out.println("Список книг еще пуст");
            return;
        }
        for (Book book : books) {
            book.print();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите команду: create, print, exit: ");
            String command = scanner.nextLine().trim();

            switch (command) {
                case "create":
                    System.out.print("Какой объект необходимо создать (p/b): ");
                    String type = scanner.nextLine().trim();

                    switch (type) {
                        case "p":
                            System.out.print("Введите название издательства: ");
                            String name = scanner.nextLine().trim();

                            System.out.print("Введите город издательства: ");
                            String city = scanner.nextLine().trim();

                            try {
                                Publisher publisher = new Publisher(name, city);
                                publishers = Arrays.copyOf(publishers, publishers.length + 1);
                                publishers[publishers.length - 1] = publisher;
                                System.out.println("Издательство добавлено: " + publisher.getName());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Ошибка: " + e.getMessage());
                            }
                            break;

                        case "b":
                            System.out.print("Какому издательству принадлежит книга? ");
                            String pubName = scanner.nextLine().trim();

                            Publisher publisher = null;
                            for (Publisher p : publishers) {
                                if (p.getName().equals(pubName)) {
                                    publisher = p;
                                    break;
                                }
                            }

                            if (publisher == null) {
                                System.out.println("Издательство не найдено: " + pubName);
                                break;
                            }

                            System.out.print("Введите название книги: ");
                            String title = scanner.nextLine().trim();

                            System.out.print("Введите год издания: ");
                            int year = 0;
                            while (true) {
                                try {
                                    year = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.print("Некорректный ввод. Введите год числом: ");
                                    scanner.nextLine();
                                }
                            }

                            System.out.print("Введите имя автора (или пусто): ");
                            String author = scanner.nextLine().trim();

                            try {
                                Book book;
                                if (author.isEmpty()) {
                                    book = new Book(title, year, publisher);
                                } else {
                                    book = new Book(title, author, year, publisher);
                                }

                                books = Arrays.copyOf(books, books.length + 1);
                                books[books.length - 1] = book;
                                System.out.println("Книга добавлена: " + book.getTitle());
                            } catch (IllegalArgumentException e) {
                                System.out.println("Ошибка: " + e.getMessage());
                            }
                            break;

                        default:
                            System.out.println("Неизвестный тип объекта. Ожидалось p или b.");
                    }
                    break;

                case "print":
                    printAll(books);
                    break;

                case "exit":
                    System.out.println("Программа завершена.");
                    return;

                default:
                    System.out.println("Неизвестная команда. Доступные: create, print, exit.");
            }
        }
    }
}