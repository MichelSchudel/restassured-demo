package nl.craftsmen.conference;

import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.lessThan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ProxySpecification;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;

public class MyConferenceRestIT {

    private static final String DEVOXX2017 = "Devoxx2017";

    @Before
    public void setup() {
    }

    @Test
    public void getToConferenceServiceShouldProduceCorrectResponse() {
    }

    @Test
    public void getConferenceWithDifferentParamsProduceHappyResponse() {

    }

    @Test
    public void getConferencesOnSecurePathNeedsAuthentication() {
    }


    @Test
    public void getConferenceWithPathParamIdOneShouldNotTakeLongerThanOneSecond() {
    }

    @Test
    public void getConferenceResponseSpecBuilderForParam() {
    }


    @Test
    public void postConferenceShouldResultInStatusOk() {
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
    }



}
