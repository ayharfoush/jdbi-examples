package book;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        try (Handle handle = jdbi.open()) {
            BookDao dao = handle.attach(BookDao.class);
            dao.createTable();
            dao.insert();
            dao.insert();
            System.out.println(dao.find()); //(isbn id)
            Book book = dao.find().get();
            dao.delete(book);
            dao.findAll().stream().map(book::getpublicationdate).sorted().forEach(System.out::println);
    }
}