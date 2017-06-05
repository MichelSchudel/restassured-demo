package nl.craftsmen.conference;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConferenceApplication.class})
@WebAppConfiguration
public class TraditionalSpringIT {

    @Autowired
    private WebApplicationContext context;

    @Before
    public void
    rest_assured_is_initialized_with_the_web_application_context_before_each_test() {
        RestAssuredMockMvc.webAppContextSetup(context);
    }

    @After
    public void
    rest_assured_is_reset_after_each_test() {
        RestAssuredMockMvc.reset();
    }

    @Test
    public void testJBCNConference() {
        RestAssuredMockMvc.when().
                get("/conference/{id}", 1).
                then().
                statusCode(200).
                body("name", equalTo("JBCNConf2017"));
    }

    @Test
    public void testAllConferencesSecure() {
        RestAssuredMockMvc.given()
                .auth()
                .with(httpBasic("admin", "admin")).
                when().
                get("/secure/conference") //
                .then() //
                .statusCode(200) //
                .body("findAll{it.name.startsWith('J')}.name", hasItems("JBCNConf2017"),
                        "name[0]", equalTo("JBCNConf2017"));
    }
}