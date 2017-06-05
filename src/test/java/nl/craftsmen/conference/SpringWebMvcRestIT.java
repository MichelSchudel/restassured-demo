package nl.craftsmen.conference;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvcBuilder;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class SpringWebMvcRestIT {

    @Test
    public void testJBCNConference() {
        when().
                get("/conference/{id}", 1).
                then().
                statusCode(200).
                body("name", equalTo("JBCNConf2017"));

    }
}
