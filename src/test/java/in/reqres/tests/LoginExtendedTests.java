package in.reqres.tests;

import in.reqres.models.*;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;

import static in.reqres.helpers.CustomAllureListener.withCustomTemplates;
import static in.reqres.specs.LoginSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginExtendedTests {

    @Test
    void successfulLoginBadPracticeTest() {
        String authData = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }"; // BAD PRACTICE

        LoginResponseModel loginResponse = step("Make request", () ->
                given(loginRequestSpec)
                        .body(authData)
                        .filter(withCustomTemplates())
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Check response token", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }


    @Test
    void successfulLoginTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel loginResponse = step("Make request", () ->
                given(loginRequestSpec)
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class));

        step("Check response token", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }

    @Test
    void successfulLoginWithAllureTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel loginResponse = step("Make request", () ->
                given(loginRequestSpec)
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class));

        step("Check response token", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }

    @Test
    void successfulLoginWithCustomAllureTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel loginResponse = step("Make request", () ->
                given(loginRequestSpec)
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(authData)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseModel.class));

        step("Check response token", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }

    @Test
    void successfulLoginWithStepsTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel loginResponse = step("Make request", () ->
                given(loginRequestSpec)
                        .filter(withCustomTemplates())
                        .contentType(JSON)
                        .body(authData)
                        .when()
                        .post("https://reqres.in/api/login")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseModel.class));

        step("Check response token", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }

    @Test
    void successfulLoginWithSpecsTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel loginResponse = step("Make request", () ->
                given(loginRequestSpec)
                        .body(authData)
                        .filter(withCustomTemplates())
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Check response token", () ->
                assertEquals("QpwL5tke4Pnpja7X4", loginResponse.getToken()));
    }

    @Test
    void missingPasswordTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("peter@klaven");

        MissingPasswordModel missingPasswordResponse = step("Make request", () ->
                given(loginRequestSpec)
                        .filter(withCustomTemplates())
                        .body(authData)
                        .when()
                        .post("/login")
                        .then()
                        .spec(missingPassword400Spec)
                        .extract().as(MissingPasswordModel.class));

        step("Check response 400", () ->
                assertEquals("Missing password", missingPasswordResponse.getError()));
    }

    @Test
    void successfulCreateUserWithAllureTest() {
        CreateUserRequestModel userData = new CreateUserRequestModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        CreateUserResponseModel createUserResponse = step("Make request", () ->
                given(createUserRequestSpec)
                        .filter(new AllureRestAssured())
                        .contentType(JSON)
                        .body(userData)
                        .when()
                        .post("https://reqres.in/api/users")
                        .then()
                        .spec(createUserResponseSpec)
                        .log().status()
                        .log().body()
                        .statusCode(201)
                        .extract().as(CreateUserResponseModel.class));

        step("Check response name", () ->
                assertEquals("morpheus", createUserResponse.getName()));
    }
}
