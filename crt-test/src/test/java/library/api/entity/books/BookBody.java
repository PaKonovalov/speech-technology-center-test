package library.api.entity.books;

import library.api.entity.BaseBodyEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookBody extends BaseBodyEntity {

    private String author;
    private int id;
    private Boolean isElectronicBook;
    private String name;
    private int year;
}
