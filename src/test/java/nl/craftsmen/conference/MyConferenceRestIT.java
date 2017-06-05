package nl.craftsmen.conference;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONObject;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import io.restassured.builder.ResponseSpecBuilder;

public class MyConferenceRestIT {

    @Test
    public void testStatusCode() {

    }

    @Test
    public void testStatusCodeXml() {
        //show status code with xml parsing
        given().contentType("application/xml").accept("application/xml")
                .log().all()
                .when()
                .get("/conference")
                .then()
                .log().all()
                .statusCode(200)
                .body("ArrayList.item[0].name", is("JBCNConf2017"));
    }


    @Test
    public void testSpecificConference() {
        //show use of path parameter
        given().pathParam("id", 1).when().get("conference/{id}").then().statusCode(200).and().body("name", equalTo("JBCNConf2017"));
        //show use of path parameter inlined
        given().when().get("conference/{id}", 1).then().statusCode(200).and().body("name", equalTo("JBCNConf2017"));
        //show use of query parameters
        given().param("id", "1").when().get("conference").then().statusCode(200).and().body("name", equalTo("JBCNConf2017"));
        //force use of query parameters
        given().queryParam("id", "1").when().get("conference").then().statusCode(200).and().body("name", equalTo("JBCNConf2017"));
        //test not found
        given().queryParam("id", "2300").when().get("conference").then().log().all().statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void extractData() {
        //show how data is extracted
        System.out.println(when().get("/conference").then().extract().path("name[0]").toString());
    }

    @Test
    public void postData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "Another Conference");
        jsonObject.put("description", "also pretty cool conference");
        jsonObject.put("begins", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        jsonObject.put("ends", LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        jsonObject.put("city", "ToBeDetermined");
        String id = given().contentType("application/json").body(jsonObject.toJSONString()).when().post("/conference").then().log().all().statusCode(200).extract().path("id").toString();
        System.out.println(id);
        ResponseSpecBuilder happyResponse = new ResponseSpecBuilder();
        happyResponse.expectStatusCode(200).expectContentType("application/json");
        when().get("conference/{id}", id).then().spec(happyResponse.build()).body("name", equalTo("Another Conference"));
        when().delete("/conference/{id}", id).then().statusCode(200);
    }

    @Test
    public void testSecure() {
        given().auth().basic("admin", "admin").when().get("secure/conference").then().statusCode(200).log().all();
    }


}
