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
            dao.insert("8745748","Jay","Stone",Book.Format.HARDBACK,"Bloomburg",LocalDate.parse("1997-06-26"),500,true);
            dao.insert("9856971","Kay","Phoenix",Book.Format.HARDBACK,"Blb",LocalDate.parse("2009-06-15"),479,true);
            dao.insert("2468785","Rowling","DH",Book.Format.HARDBACK,"Max",LocalDate.parse("2007-06-21"),450,true);
            System.out.println(dao.find("8745748"));
            Book book = dao.find("2468785").get();
            dao.delete(book);
            dao.findAll().stream().forEach(System.out::println);
        }

    }
}