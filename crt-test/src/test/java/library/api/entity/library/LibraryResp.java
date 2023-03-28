package library.api.entity.library;

import library.api.entity.BaseBodyEntity;
import library.api.entity.books.BookBody;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LibraryResp extends BaseBodyEntity {

    private List<BookBody> books;
}