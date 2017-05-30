package nl.actorcatalogue;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

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
                .param("q", "J")
                .when()
                .get("/conference")
                .then()
                .body("$.collect { it.length() }.sum()", lessThan(10));
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
    public void testAllConference() {
        when().
                get("/conference") //
                .then() //
                .statusCode(200) //
                .body("findAll{it.name.startsWith('J')}.name", hasItems("JBCNConf2017"));

    }
    @Test
    public void testAddConferenceWithObject() {
        Conference conference = new Conference();
        conference.setName("Devoxx");
        conference.setDescription("pretty cool,too!");
        conference.setBegins(LocalDate.parse("2017-11-06").now());
        conference.setEnds(LocalDate.parse("2017-11-10"));
        conference.setCity("Antwerp");
        given().body(conference).contentType(ContentType.JSON).when().post("/conference").then().statusCode(200);
    }

    @Test
    public void testAddConference() throws JSONException {
        String body = new JSONObject()
                .put("name", " Devoxx") //
                .put("description", "pretty cool") //
                .put("begins", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) //
                .put("ends", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) //
                .put("city", "Barcelona") //
                .toString();
        given()
                .body(body) //
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

}
