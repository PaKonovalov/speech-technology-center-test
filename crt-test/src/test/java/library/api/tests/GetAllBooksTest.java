package library.api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import library.api.entity.library.LibraryResp;
import library.api.helpers.RequestSpecificationCreator;
import library.api.helpers.RequestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_OK;
import static library.api.common.ApiTags.LIBRARY;
import static library.api.common.ApiTags.GET_ALL_BOOKS;
import static library.api.common.BaseUrl.BASE_HOST;
import static library.api.common.BaseUrl.BOOKS_API;
import static library.api.common.Tags.POSITIVE;
import static library.api.util.AssertMessages.getAssertMatchMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag(LIBRARY)
public class GetAllBooksTest {

    private static RequestSpecification specification;

    @BeforeAll
    public static void setUp() {
        specification = RequestSpecificationCreator.getReqSpec(BASE_HOST, BOOKS_API);
    }

    @Tag(GET_ALL_BOOKS)
    @Tag(POSITIVE)
    @DisplayName("Получить все книги из библиотеки")
    @Test
    public void getAllBooks() throws IOException {
        File allBooksListPath = new File("src/test/resources/all-books.json");
        ObjectMapper objectMapper = new ObjectMapper();

        List<LibraryResp> expRes = Arrays.asList(objectMapper.readValue(allBooksListPath, LibraryResp.class));
        List<LibraryResp> actRes = RequestUtil.doRequestGetList(LibraryResp.class, HTTP_OK, specification);

       assertEquals(expRes, actRes, getAssertMatchMessage("Полученый список книг отличается от ожидаемого"));
    }
}