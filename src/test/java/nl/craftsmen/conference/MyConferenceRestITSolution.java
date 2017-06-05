package nl.craftsmen.conference;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class MyConferenceRestITSolution {

    private ResponseSpecBuilder defaultAsserts;
    @Before
    public void setup() {

        defaultAsserts = new ResponseSpecBuilder();
        defaultAsserts.expectStatusCode(200)
                .expectBody("name", equalTo("JBCNConf2017"));
    }

    @Test
    public void getToConferenceServiceShouldProduceHappyResponse() {
        Response response = given()
              .log()
                .all().


        when().
                get("conference").
        then().
                log().all().
                statusCode(HttpStatus.OK.value())
        .extract().response();

        System.out.println(response.asString());
        //test status code
        //logging
        //extract
        //extract path
    }

    @Test
    public void getToConferenceServiceShouldRetrieveAllConferences() {
        when().get("conference").then().body("size()", greaterThan(2));
        //test list length
    }

    @Test
    public void getToConferenceServiceShouldContainJBCNConference() {
        when().get("conference").then().body("name[0]", equalTo("JBCNConf2017"));
        when().get("conference").then().body("findAll{it.name.startsWith('J')}.name", hasItems("JBCNConf2017"));
        //test by find or first elem
    }

    @Test
    public void getToConferenceServiceWithXmlContentTypeShouldRetrieveAllConferences() {
        given().contentType("application/xml")
                .accept("application/xml")
                .when()
                .get("conference")
                .then()
                .statusCode(200)
                .log()
                .all()
                .body("ArrayList.item[0].name", equalTo("JBCNConf2017"));
        //test with content type or header
        //assert on ArrayList
    }

    @Test
    public void getConferenceWithPathParamIdOneShouldProduceJBCNConf() {
        //explicit and inline
        given().when().get("conference/{id}", 1).then()
                .body("name", equalTo("JBCNConf2017"));
    }

    @Test
    public void getConferenceWithQueryParamIdOneShouldProduceJBCNConf() {
        given().param("id", 1).when().get("conference/{id}", 1).then()
                .body("name", equalTo("JBCNConf2017"));
    }

    @Test
    public void getConferenceWithQueryParamIdUnknownShouldProduce404() {
        given().queryParam("id", 1).when().get("conference/{id}", 1).then()
                .body("name", equalTo("JBCNConf2017"));
    }

    @Test
    public void getConferenceResponseSpecBuilder() {
        given().queryParam("id", 1).when()
                .get("conference/{id}", 1)
                .then()
                .spec(defaultAsserts.build());
    }

    @Test
    public void getConferenceWithPathParamIdOneShouldNotTakeLongerThanOneSecond() {
        given().queryParam("id", 1).when().get("conference/{id}", 1).then()
                .time(lessThan(1000L), TimeUnit.MILLISECONDS);
    }

    @Test
    public void getConferencesOnSecurePathNeedsAuthentication() {
        when().get("/secure/conference").then().statusCode(401);
        given().auth().basic("admin", "admin").when().get("secure/conference").then().statusCode(200);
    }

    @Test
    public void postConferenceShouldResultInStatusOk() {
        Conference conference = new Conference();
        conference.setBegins(LocalDate.now());
        conference.setEnds(LocalDate.now());
        conference.setName("JBCNConf2017");
        conference.setDescription("decription");
        conference.setCity("dontknow");

        int id = given().contentType("application/json").log().all().body(conference).when().log().all().post("/conference").then().statusCode(200)
        .extract().path("id");
        when().get("conference/{id}", id).then().spec(defaultAsserts.build());
        when().delete("conference/{id}", id).then().statusCode(200);
        //json object
        //post, statuscode
        //invalid content-type, fix with header
        //replace header with content-type
        //write with domain object
        //extract id
        //get
        //delete
    }

    @Test
    public void postIncompleteConferenceShouldResultInStatusBadRequest() {
        Conference conference = new Conference();
        conference.setBegins(LocalDate.now());
        conference.setEnds(LocalDate.now());
        conference.setDescription("decription");
        conference.setCity("dontknow");

        given().contentType("application/json").when()
                .post("/conference").then().statusCode(400);
    }

}
