package org.example;

import book.manage.entity.Book;
import book.manage.sql.SqlUtil;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class MainTest
{
    @Test
    public void test1()
    {
        SqlUtil.doSqlWork(bookMpapper -> {
            bookMpapper.getBorrowList().forEach(System.out::println);
        });
    }
    @Test
    public void test2()
    {
        SqlUtil.doSqlWork(bookMpapper -> {
            Book book = new Book();
            book=bookMpapper.getBookByBid(5);
            System.out.println(bookMpapper.getBookByBid(5));
        });
    }
    @Test
      public void test3()
    {
        SqlUtil.doSqlWork(bookMpapper -> {
            bookMpapper.getBookList().forEach(System.out::println);
        });
    }
    @Test
      public void test4()
    {
        SqlUtil.doSqlWork(bookMpapper -> {
            bookMpapper.getStudentList().forEach(System.out::println);
        });
    }
}
