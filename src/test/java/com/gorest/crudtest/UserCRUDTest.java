package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class UserCRUDTest extends TestBase {

    static String name = "Nikunja" + TestUtils.getRandomValue();
    static String email = "Nikunja" +TestUtils.getRandomValue()+"@gmail.com";
    static String gender = "Female";
    static String status = "active";
    static int id;
    static String token = "d35d603d347355f72624a03ba750f758b0689f9a42ade094511ed820951d9eab";
    @Test
    public void test001(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        ValidatableResponse response= given()
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .header("Authorization","Bearer d35d603d347355f72624a03ba750f758b0689f9a42ade094511ed820951d9eab")
                .when()
                .body(userPojo)
                .post()
                .then().log().body().statusCode(201);
        id = response.extract().path("id");
    }
    @Test
    public void test002() {

          int uId=given()
                  .header("Authorization","Bearer d35d603d347355f72624a03ba750f758b0689f9a42ade094511ed820951d9eab")
                .pathParams("id",id)
                .when()
                .get("/{id}")
                .then().statusCode(200)
                .extract()
                .path("id");
        Assert.assertEquals(uId,id);
    }

    @Test
    public void test003(){
    UserPojo userPojo = new UserPojo();
        userPojo.setName("Nirali");
        userPojo.setEmail("nirali"+TestUtils.getRandomValue()+"@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("inactive");

        Response response= given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer d35d603d347355f72624a03ba750f758b0689f9a42ade094511ed820951d9eab")
                .pathParams("id", id)
                .when()
                .body(userPojo)
                .put("/{id}");
        response.then().log().body().statusCode(200);
    }

    @Test
    public void test004(){
        given()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer d35d603d347355f72624a03ba750f758b0689f9a42ade094511ed820951d9eab")
                .pathParam("id", id)
                .when()
                .delete("/{id}")
                .then()
                .statusCode(204);

        given()
                .pathParam("id", id)
                .when()
                .get("/{id}")
                .then().statusCode(404);
    }
    }

