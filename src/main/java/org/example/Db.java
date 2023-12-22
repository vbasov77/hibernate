package org.example;

import org.example.models.Book;
import org.hibernate.Session;

import java.sql.*;
import java.util.List;

public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static List<Book> findBookByAuthor(String author) {
        Connector connector = new Connector();
        try(Session session = connector.getSession()) {
            return session.createQuery("from Book where author = '" + author + "'", Book.class).getResultList();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void creatRow(String name, String author) {

        Connector connector = new Connector();
        Session session = connector.getSession();
        Book book = new Book();
        book.setName(name);
        book.setAuthor(author);
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();

    }

    public static void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Statement statement = connection.createStatement();
            statement.execute("drop schema `books`");
            statement.execute("create schema `books`");
            statement.execute("create table `books` . `books` (" +
                    "`id` bigint not null auto_increment, " +
                    "`name` varchar(100) not null," +
                    "`author` varchar(100) not null," +
                    "primary key (`id`));");
            statement.execute("insert into `books` . `books` (`id`, `name`, `author`)\n" +
                    "values  (1, 'Война и мир', 'Толстой')," +
                    "(2, 'Евгений Онегин', 'Пушкин')," +
                    "(3, 'Тихий Дон', 'Шолохов')," +
                    "(4, 'Мастер и Маргарита', 'Булгаков')," +
                    "(5, 'Идиот', 'Достоевский')," +
                    "(6, 'Сто лет одиночества', 'Габриэль Гарсиа Маркес')," +
                    "(7, 'Преступление и наказание', 'Достоевский')," +
                    "(8, 'Робинзон Крузо', 'Даниэль Дефо')," +
                    "(9, 'Анна Каренина', 'Толстой')," +
                    "(10, 'Дон Кихот', 'Мигель де Сервантес Сааведра');");

            ResultSet set = statement.executeQuery("select * from books.books where author = 'Толстой'");
            while (set.next()) {
                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }


}
