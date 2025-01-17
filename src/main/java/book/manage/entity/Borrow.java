package book.manage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@Data
public class Borrow {
    int id;
    Student student;
    Book book;
}
