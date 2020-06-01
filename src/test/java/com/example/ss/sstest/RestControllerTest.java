package com.example.ss.sstest;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "security.user.password=pass")
class RestControllerTest {

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void initRestAssured() {
        RestAssured.port = serverPort;
        RestAssured.filters(new ResponseLoggingFilter());
        RestAssured.filters(new RequestLoggingFilter());
    }

    @Test
    void testBasicAuth() {
        given()
                .auth().preemptive().basic("admin", "admin")
                .when()
                .get("/test/basic")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void testBearerAuth() {
        given()
                .auth().preemptive().oauth2("admin")
                .when()
                .get("/test/bearer")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
