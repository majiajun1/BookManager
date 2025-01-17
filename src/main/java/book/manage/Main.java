package book.manage;

import book.manage.entity.Book;
import book.manage.entity.Student;
import book.manage.mapper.BookMpapper;

import book.manage.sql.SqlUtil;
import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.LogManager;

@Log
public class Main {
    public static void main(String[] args)   {
       try(Scanner scanner = new Scanner(System.in);) {
           LogManager manager = LogManager.getLogManager();

           manager.readConfiguration(Resources.getResourceAsStream("logging.properties"));
           while (true) {
               System.out.println("===================================");
               System.out.println("1、录入学生信息");
               System.out.println("2、录入书籍信息");
               System.out.println("3、添加借阅信息");
               System.out.println("4、查看借阅信息");
               System.out.println("5、查看学生信息");
               System.out.println("6、查看书籍信息");
               System.out.println("输入您想执行的操作，按其他任意键退出");
               int input = scanner.nextInt();
               scanner.nextLine();
               switch (input) {
                   case 1:
                       addStudent(scanner);
                       break;
                   case 2:
                       addBook(scanner);
                       break;
                   case 3:
                        addBorrow(scanner);
                       break;
                   case 4:
                       showBorrow();
                       break;
                   case 5:
                       showStudent();
                       break;
                   case 6:
                       showBook();
                       break;
                   default:
                       return;
               }

           }
       }catch (Exception e) {

           return;
       }


    }

    private static void addBorrow(Scanner scanner) {
        System.out.print("请输入书籍号:");
        String a = scanner.nextLine();
        int bid=Integer.parseInt(a);
        System.out.print("请输入学生号");
        String b=scanner.nextLine();
        int sid=Integer.parseInt(b);
        SqlUtil.doSqlWork(bookMpapper -> {
           bookMpapper.addBorrow(sid,bid);
        });
    }

    private static void showBook() {
        SqlUtil.doSqlWork(bookMpapper -> {
            bookMpapper.getBookList().forEach(book -> {
                System.out.println(book.toString());
            });
        });

    }

     private static void showStudent() {
          SqlUtil.doSqlWork(bookMpapper -> {
            bookMpapper.getStudentList().forEach(System.out::println);
        });
    }

    private static void addStudent(Scanner scanner)
        {
            System.out.print("请输入名字:");
            String name = scanner.nextLine();
            System.out.print("请输入学生性别:");
            String sex=scanner.nextLine();
            System.out.print("请输入年级");
            int age=Integer.parseInt(scanner.nextLine());
            Student student=new Student(name,sex,age);
            SqlUtil.doSqlWork(bookMpapper -> {
               int i= bookMpapper.addStudent(student);
               if(i>0)
               {
                   System.out.println("学生信息录入成功");
                   log.info("新添加了一条学生信息"+student.toString());
               }
               else {
                   System.out.println("失败，请重试");
               }
            });

        }

        private static void showBorrow()
        {
           SqlUtil.doSqlWork(bookMpapper -> {
            bookMpapper.getBorrowList().forEach(borrow -> {
                System.out.println(borrow.getStudent().getName()+" -> "+borrow.getBook().getTitle());
            });
        });
        }

        private static void addBook(Scanner scanner)
        {
            System.out.print("请输入书籍标题:");
            String name = scanner.nextLine();
            System.out.println("请输入书籍信息:");
            String desc=scanner.nextLine();
            System.out.println("请输入价格");
            double price=Double.parseDouble(scanner.nextLine());
            Book book=new Book(name,desc,price);
            SqlUtil.doSqlWork(bookMpapper -> {
               int i= bookMpapper.addBook(book);
               if(i>0)
               {
                   System.out.println("书籍信息录入成功");
                   log.info("新添加了一条书籍信息"+book.toString());
               }
               else {
                   System.out.println("失败，请重试");
               }
            });

        }
}
