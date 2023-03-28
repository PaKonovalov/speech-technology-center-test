package library.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(BOOKS)
public class GetBookByIdTest {

    private static RequestSpecification specification;

    private static final int BOOK_ID = 1;

    @BeforeAll
    public static void setUp() {
        specification = RequestSpecificationCreator.getReqSpec(BASE_HOST, ACTION_WITH_BOOK_BY_ID_API, BOOK_ID);
    }

    @Tag(ACTION_WITH_BOOK_BY_ID_API)
    @Tag(POSITIVE)
    @DisplayName("Получить книгу по её Id")
    @Test
    public void getBookById() throws IOException {
        File bookFromLibrary = new File("src/test/resources/book-from-library.json");
        ObjectMapper objectMapper = new ObjectMapper();

        BookByIdResp expRes = objectMapper.readValue(bookFromLibrary, BookByIdResp.class);
        BookByIdResp actRes = RequestUtil.doGetRequest(BookByIdResp.class, HTTP_OK, specification);

        assertEquals(expRes, actRes, getAssertMatchMessage("Полученая книга отличается от ожидаемой"));
    }
}