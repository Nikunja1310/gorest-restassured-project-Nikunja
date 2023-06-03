package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class UserAssertionTest extends TestBase {
    static ValidatableResponse response;

    public UserAssertionTest() {
        response = given()
                .queryParam("page", 1)
                .queryParam("per_page", 20)
                .when()
                .get()
                .then().log().body().statusCode(200);
    }

    @Test
    public void test001() {
        //    1. Verify the if the total record is 20
        response.body("size", equalTo(20));
    }


    //   2. Verify the if the name of id = 2178420,eual to  name Suryakant Nair DC

    @Test
    public void test002() {
        //  response.body("findAll{it.[*].id == '2178420'}.name", equalTo("Suryakant Nair DC"));
        response.body("findAll{it.id == '2178420'}.name", equalTo("Suryakant Nair DC"));
    }

//            3. Check the single ‘Name’ in the Array list (Suryakant Nair DC)
    @Test
    public void test003() {
        response.body("data.name", hasItem("Suryakant Nair DC"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList = names = Vaishvi Ahluwalia,Kanti Deshpande
    @Test
    public void test004() {
        response.body("data.name", hasItems("Vaishvi Ahluwalia", "Kanti Deshpande"));
    }

//5. Verify the email of userid =  2178432,is equal “vaishvi_ahluwalia@ohara.test”
    @Test
    public void test005() {
        response.body("data.find({it.id == 2178432}.email)", hasItem("vaishvi_ahluwalia@ohara.test"));
    }

    //            6. Verify the status is “Active” of  name is “Vaishvi Ahluwalia”
    @Test
    public void test006() {
        response.body("data.findAll{it.name == 'Vaishvi Ahluwalia'}", hasItem(hasEntry("status","active")));
    }
//            7. Verify the Gender = male of user name is “Vaishvi Ahluwalia”
@Test
public void test007() {
    response.body("data.findAll{it.name == 'Vaishvi Ahluwalia'}.gender", hasItem("male"));
}
}