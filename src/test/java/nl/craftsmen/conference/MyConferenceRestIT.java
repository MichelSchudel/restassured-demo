package nl.craftsmen.conference;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.builder.RequestSpecBuilder;

public class MyConferenceRestIT {

    @Before
    public void setup() {
    }

    @Test
    public void getToConferenceServiceShouldProduceHappyResponse() {
        //test status code
        //logging
        //extract
        //extract path
    }

    @Test
    public void getToConferenceServiceShouldRetrieveAllConferences() {
        //test list length
    }

    @Test
    public void getToConferenceServiceShouldContainJBCNConference() {
        //test by find or first elem
    }

    @Test
    public void getToConferenceServiceWithXmlContentTypeShouldRetrieveAllConferences() {
        //test with content type or header
        //assert on ArrayList
    }

    @Test
    public void getConferenceWithPathParamIdOneShouldProduceJBCNConf() {
        //explicit and inline
    }

    @Test
    public void getConferenceWithQueryParamIdOneShouldProduceJBCNConf() {
    }

    @Test
    public void getConferenceWithQueryParamIdUnknownShouldProduce404() {
    }

    @Test
    public void getConferenceResponseSpecBuilder() {
    }

    @Test
    public void getConferenceWithPathParamIdOneShouldNotTakeLongerThanOneSecond() {
    }

    @Test
    public void getConferencesOnSecurePathNeedsAuthentication() {
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
