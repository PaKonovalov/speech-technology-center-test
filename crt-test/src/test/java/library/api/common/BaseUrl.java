package library.api.common;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BaseUrl {

    public final static String BASE_HOST = "http://localhost:5000";
    public final static String BOOKS_API = "/api/books";
    public final static String ACTION_WITH_BOOK_BY_ID_API = "/api/books/%s";
}
