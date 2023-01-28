package ru.malichenko.test;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class TariffResourceTest {

//    @Test
//    public void pagedList() {
//        given()
//                .when().get("/tariffs?pageNum=0&pageSize=2")
//                .then()
//                .statusCode(200)
//                .body(
//                        "$.size()", is(2),
//                        "name", containsInAnyOrder("Super", "Old")
//                );
//    }

//    @Test
//    public void testHelloEndpoint() {
//        given()
//                .when().get("/hello")
//                .then()
//                .statusCode(200)
//                .body(is("Hello RESTEasy"));
//    }

}