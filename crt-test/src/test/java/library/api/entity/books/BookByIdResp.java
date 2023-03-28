package library.api.entity.books;

import library.api.entity.BaseBodyEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookByIdResp extends BaseBodyEntity {

    private BookBody book;
}
