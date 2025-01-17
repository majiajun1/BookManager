package book.manage.sql;

import book.manage.mapper.BookMpapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;

public class SqlUtil {
    private SqlUtil(){}


    private static SqlSessionFactory factory;

    static {
        try {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void doSqlWork(Consumer<BookMpapper> consumer)
    {
        try (SqlSession session = factory.openSession(true)) {
            BookMpapper mapper = session.getMapper(BookMpapper.class);
            consumer.accept(mapper);
        }
    }
}
