package library.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import library.api.entity.books.BookBody;
import library.api.entity.books.BookByIdResp;
import library.api.helpers.RequestSpecificationCreator;
import library.api.helpers.RequestUtil;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static library.api.common.ApiTags.BOOKS;
import static library.api.common.BaseUrl.BASE_HOST;
import static library.api.common.BaseUrl.BOOKS_API;
import static library.api.common.BaseUrl.ACTION_WITH_BOOK_BY_ID_API;
import static library.api.common.Tags.POSITIVE;
import static library.api.util.AssertMessages.getAssertMatchMessage;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Tag(BOOKS)
public class AddBookToLibraryTest {

    private static RequestSpecification specification;

    @BeforeAll
    public static void setUp() {
        specification = RequestSpecificationCreator.getReqSpec(BASE_HOST, BOOKS_API);
    }

    @Tag(ACTION_WITH_BOOK_BY_ID_API)
    @Tag(POSITIVE)
    @DisplayName("Добавить книгу в библиотеку")
    @Test
    public void addNewBook() throws IOException {
        File updatedBookData = new File("src/test/resources/created-book-data.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BookBody actRes = BookBody.builder()
                .author("Молодая гваридия")
                .isElectronicBook(true)
                .name("Игральные вечера")
                .year(1931)
                .build();

        RequestUtil.doPostRequest(actRes, HTTP_CREATED, specification);

        BookByIdResp expRes = objectMapper.readValue(updatedBookData, BookByIdResp.class);

        assertAll(
                () -> assertEquals(expRes.getBook().getYear(),
                        actRes.getYear(),
                        getAssertMatchMessage("Год издания отличается от ожидаемого")),
                () -> assertEquals(expRes.getBook().getName(),
                        actRes.getName(),
                        getAssertMatchMessage("Название книги отличается от ожидаемого")),
                () -> assertEquals(expRes.getBook().getIsElectronicBook(),
                        actRes.getIsElectronicBook(),
                        getAssertMatchMessage("Вид издания отличается от ожидаемого")),
                () -> assertEquals(expRes.getBook().getAuthor(),
                        actRes.getAuthor(),
                        getAssertMatchMessage("Автор отличается от ожидаемого"))
        );
    }
}
