package book;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());
        try (Handle handle = jdbi.open()) {
            BookDao dao = handle.attach(BookDao.class);
            dao.createTable();
            dao.insert("9781416","Scott","Leviathan",Book.Format.HARDBACK,"Simon & Schuster",LocalDate.parse("2009-10-06"),448,true);
            dao.insert("8865452","Tom","ABC",Book.Format.HARDBACK,"Ester & Michale",LocalDate.parse("2009-06-15"),135,true);
            dao.insert("2461672","Jennie","Pass",Book.Format.HARDBACK,"Jerry",LocalDate.parse("2009-12-24"),465,true);
            System.out.println(dao.find("9781416"));
            Book book = dao.find("2461672").get();
            dao.delete(book);
            dao.findAll().stream().forEach(System.out::println);
        }

    }
}