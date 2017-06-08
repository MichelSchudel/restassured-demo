package nl.craftsmen.conference;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import java.io.File;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

public class ConferenceRestIT {

    @Test
    public void testJBCNConference() {
        when().
                get("/conference/{id}", 1).
                then().
                statusCode(200).
                body("name", equalTo("JBCNConf2017"));

    }

    @Test
    public void testGetwithParams() {
        given()
            .param("param1", "value")
        .when()
            .get("/conference")
        .then()
            .statusCode(200);
    }

    @Test
    public void extractJBCNConference() {
        String response = when()
                .get("/conference/{id}", 1)
                .then()
                .extract()
                .asString();
        System.out.println(response);

    }

    @Test
    public void testHeaders() {
        given()
                .header("Content-type", "text/html")
        .when()
                .get("/conference")
        .then()
                .header("Content-type", "application/json")
                .statusCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
    }

    @Test
    public void testCookies() {
        given().contentType("application/json").cookie("mycookie").when().get("/conference").then().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    @Test
    public void testAllConference() {
        given().
            contentType("application/json").
        when().
            get("/conference") //
        .then() //
            .statusCode(200) //
        .body("findAll{it.name.startsWith('J')}.name", hasItems("JBCNConf2017"),
                "name[0]", equalTo("JBCNConf2017"));

    }

    @Test
    public void testAllConferencesSecure() {
        given()
                .auth()
                .basic("admin", "admin")
                .contentType("application/json").
                when().
                get("/secure/conference") //
                .then() //
                .statusCode(200) //
                .body("findAll{it.name.startsWith('J')}.name", hasItems("JBCNConf2017"),
                        "name[0]", equalTo("JBCNConf2017"));
    }

    @Test
    public void testAllConferencesSecureWrongCredentials() {
        given()
                .auth()
                .basic("admin", "dontknow")
                .contentType("application/json").
                when().
                get("/secure/conference") //
                .then() //
                .statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void testAddConferenceWithObject() {
        Conference conference = new Conference();
        conference.setName("JFall2107");
        conference.setDescription("best conference in the Netherlands!");
        conference.setBegins(LocalDate.parse("2017-11-02"));
        conference.setEnds(LocalDate.parse("2017-11-02"));
        conference.setCity("Ede");
        given().body(conference).contentType(ContentType.JSON).when().post("/conference").then().statusCode(200);
    }

    @Test
    public void testAddConference() throws JSONException {
        JSONObject conference = new JSONObject()
                .put("name", " JFall2017") //
                .put("description", "best conference in the Netherlands!") //
                .put("begins", LocalDate.parse("2017-11-02")) //
                .put("ends", LocalDate.parse("2017-11-02")) //
                .put("city", "Ede");
        given()
                .body(conference) //
                .contentType(ContentType.JSON) //
                .when() //
                .post("/conference") //
                .then() //
                .statusCode(200);
    }

    @Test
    public void testDeleteConference() throws JSONException {
        given().
                when() //
                .delete("/conference/3") //
                .then() //
                .statusCode(200);
    }

    @Test
    public void testFileUpload() throws URISyntaxException {
        given().
            multiPart("file", new File(getClass().getResource("/file.txt").toURI())).
        when().
            post("/upload").
        then()
            .assertThat().body(equalTo("succeeded!"));
    }

    @Test
    public void testAllConferencesUsingRequestSpecBuilder() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setContentType("application/json");
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        given().
            spec(requestSpecBuilder.build()).
        when().
            get("/conference").
        then().
            spec(responseSpecBuilder.build());
    }

    @Test
    public void testWithLogging() {
        given().
            log().all().
        when().
            get("/conference/{id}", 1).
        then().
            log().ifValidationFails().
            statusCode(200).
            body("name", equalTo("JBCNConf2017"));
    }

}
