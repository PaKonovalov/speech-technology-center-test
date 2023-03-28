package library.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import library.api.entity.books.BookBody;
import library.api.entity.books.BookByIdResp;
import library.api.helpers.RequestSpecificationCreator;
import library.api.helpers.RequestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static java.net.HttpURLConnection.HTTP_OK;
import static library.api.common.ApiTags.BOOKS;
import static library.api.common.BaseUrl.BASE_HOST;
import static library.api.common.BaseUrl.ACTION_WITH_BOOK_BY_ID_API;
import static library.api.common.Tags.POSITIVE;
import static library.api.util.AssertMessages.getAssertMatchMessage;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(BOOKS)
public class ChangeBookTest {

    private static RequestSpecification specification;
    private static final int BOOK_ID = 3;

    @BeforeAll
    public static void setUp() {
        specification = RequestSpecificationCreator.getReqSpec(BASE_HOST, ACTION_WITH_BOOK_BY_ID_API, BOOK_ID);
    }

    @Tag(ACTION_WITH_BOOK_BY_ID_API)
    @Tag(POSITIVE)
    @DisplayName("Обновить информацию о книге по её id")
    @Test
    public void updateBook() throws IOException {
        File updatedBookData = new File("src/test/resources/updated-book-data.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BookBody actRes = BookBody.builder()
                .author("Молодая гваридия")
                .id(3)
                .isElectronicBook(false)
                .name("Игральные вечера")
                .year(1930)
                .build();

        RequestUtil.doPutRequest(actRes, HTTP_OK, specification);

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