package crs;

import java.util.Arrays;

public class Book {
    private String title;
    private Publisher publisher;
    private int year;
    private String[] authors;

    public String getTitle() {
        return title;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public int getYear() {
        return year;
    }

    public String[] getAuthors() {
        return authors;
    }

    public int getAuthorsCount() {
        if (authors == null) {
            return 0;
        } else {
            return authors.length;
        }
    }

    public String getAuthors(int index) {
        if (index >= authors.length || index < 0) {
            throw new IndexOutOfBoundsException("Несуществующий автор");
        }
        return authors[index];
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Название книги не может быть пустым");
        }
        this.title = title;
    }

    public void setPublisher(Publisher publisher) {
        if (publisher == null) {
            throw new IllegalArgumentException("Издательство не может быть пустым");
        }
        this.publisher = publisher;
    }

    public void setYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("Некорректный год издания");
        }
        this.year = year;
    }

    public void setAuthor(String author) {
        if (authors == null) {
            throw new IllegalArgumentException("Массив авторов не инициализирован");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя автора не может быть пустым");
        }
        String[] newAuthors = Arrays.copyOf(authors, authors.length + 1);
        newAuthors[authors.length] = author;
        this.authors = newAuthors;
    }

    public void setAuthors(String[] authors) {
        if (authors == null) {
            throw new IllegalArgumentException("Массив авторов не может быть null");
        }
        for (String author : authors) {
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Имя автора не может быть пустым");
            }
        }
        this.authors = Arrays.copyOf(authors, authors.length);
    }


    public Book(String title, int year, Publisher publisher) {
        setTitle(title);
        setYear(year);
        setPublisher(publisher);
        this.authors = null;
    }

    public Book(String title, String author, int year, Publisher publisher) {
        setTitle(title);
        setYear(year);
        setPublisher(publisher);
        this.authors = new String[]{author};
    }

    public Book(String title, String[] authors, int year, Publisher publisher) {
        setTitle(title);
        setYear(year);
        setPublisher(publisher);
        setAuthors(authors);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Название: ").append(title).append("\n");

        if (authors != null && authors.length > 0) {
            sb.append("Автор(ы): ").append(String.join(", ", authors)).append("\n");
        }

        sb.append("Издательство: ").append(publisher).append("\n");
        sb.append("Год: ").append(year);
        return sb.toString();
    }

    public void print() {
        System.out.println(this);
    }
}