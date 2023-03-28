package library.api.tests;

import io.restassured.specification.RequestSpecification;
import library.api.helpers.RequestSpecificationCreator;
import library.api.helpers.RequestUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static library.api.common.ApiTags.BOOKS;
import static library.api.common.BaseUrl.BASE_HOST;
import static library.api.common.BaseUrl.ACTION_WITH_BOOK_BY_ID_API;
import static library.api.common.Tags.POSITIVE;

@Tag(BOOKS)
public class DeleteBookTest {

    private static RequestSpecification specification;
    private static final int BOOK_ID = 3;

    @BeforeAll
    public static void setUp() {
        specification = RequestSpecificationCreator.getReqSpec(BASE_HOST, ACTION_WITH_BOOK_BY_ID_API, BOOK_ID);
    }

    @Tag(ACTION_WITH_BOOK_BY_ID_API)
    @Tag(POSITIVE)
    @DisplayName("Удалить книгу из библиотеки")
    @Test
    public void deleteBookFromLibrary() {

        RequestUtil.doDeleteRequest(HTTP_OK, specification);
    }
}
