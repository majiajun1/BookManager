package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Book {
    int bid;
    String title;
    String desc;
    double price;

    public Book(String title, String description, double price) {
        this.title = title;
        this.desc = description;
        this.price = price;
    }
}
