package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {

        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .when()
                .get("/posts")
           .then().log().body().statusCode(200);
    }

    //Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("size", equalTo(24));
    }

    //Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto
    //centum.”
    @Test
    public void test002() {
        response.body("find{it.id == 39288}.title", equalTo("Vomer defaeco concido nam coniuratio."));
    }

    //Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004() {
        response.body("id", hasItems(39288, 39296, 39295));
    }

    //Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
    //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”
    @Test
    public void test005() {
        response.body("find{it.id == 39296}.body", equalTo("Caterva adsuesco crepusculum. Aestus ea tantillus. Aggero demo summopere. Benevolentia strues timidus. Cena absens trans. Adfero iure curis. Ut vulnero cum. Dolorem cogito ut. Cupiditas acquiro benevolentia. Color armo terebro. Aqua defero cernuus. Asperiores explicabo aegre. Commodi caste curia. Blanditiis constans velit. Expedita laborum vaco. Dens anser attollo. Cerno quis quis. Eum armarium consequatur."));
    }

}
