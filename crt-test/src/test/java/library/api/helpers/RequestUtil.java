package library.api.helpers;

import io.restassured.specification.RequestSpecification;
import library.api.entity.BaseBodyEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestUtil {

    public static <T> List<T> doRequestGetList(Class<T> clazz,
                                               int httpStatusCode,
                                               RequestSpecification specification) {
        return List.of(given()
                .spec(specification)
                .log().all()
                .when().get().then()
                .statusCode(httpStatusCode)
                .log().all()
                .extract().body()
                .as(clazz));
    }

    public static <T> T doGetRequest(Class<T> clazz,
                                     int httpStatusCode,
                                     RequestSpecification specification) {
        return given()
                .spec(specification)
                .when()
                .log().all()
                .get().then()
                .statusCode(httpStatusCode)
                .log().all()
                .extract().body().as(clazz);
    }

    public static void doPostRequest(BaseBodyEntity baseBodyEntity,
                                     int httpStatusCode,
                                     RequestSpecification specification) {
        given()
                .spec(specification)
                .body(baseBodyEntity)
                .when()
                .log().all()
                .post().then()
                .statusCode(httpStatusCode)
                .log().all();
    }

    public static void doPutRequest(BaseBodyEntity baseBodyEntity,
                                    int httpStatusCode,
                                    RequestSpecification specification) {
        given()
                .spec(specification)
                .body(baseBodyEntity)
                .when()
                .log().all()
                .put().then()
                .statusCode(httpStatusCode)
                .log().all();
    }

    public static void doDeleteRequest(int httpStatusCode,
                                       RequestSpecification specification) {
        given()
                .spec(specification)
                .when()
                .log().all()
                .delete().then()
                .statusCode(httpStatusCode)
                .log().all();
    }
}
