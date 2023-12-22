package org.example;

import org.example.models.Book;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        Db.createTable();
//        Db.creatRow("Барышня крестьянка", "Пушкин");
        List<Book> booksByAuthor = Db.findBookByAuthor("Толстой");
        System.out.println(booksByAuthor);

    }
}