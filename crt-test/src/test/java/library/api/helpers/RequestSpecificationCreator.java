package library.api.helpers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestSpecificationCreator {

    public static RequestSpecification getReqSpec(String host, String path) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(path)
                .setContentType(ContentType.JSON)
                .build();

        log.info("Сформирована спецификация для запроса");

        return requestSpecification;
    }

    public static RequestSpecification getReqSpec(String host, String path, int pathId) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri(host)
                .setBasePath(String.format(path, pathId))
                .setContentType(ContentType.JSON)
                .build();

        log.info("Сформирована спецификация для запроса");

        return requestSpecification;
    }
}