package nl.craftsmen.conference;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MySpringBootRestWireMockIT {

    @LocalServerPort
    private int randomServerPort;

    //inject speaker service endpoint

    private WireMockServer wireMockServer;

    @Before
    public void setup() {
        //setup wiremock
    }

    @After
    public void tearDown() {
        //eardown wiremock
    }

    @Sql({"/insert-test-conference.sql"})
    @Test
    @Rollback
    public void conferenceWithSpeakersShouldReturnSpeaker() {
        given().
                port(randomServerPort).
        when().
            get("/conferencewithspeakers/{id}", 1).
        then().
            log().all().
            statusCode(200).
            body("speakers[0].name", equalTo("Michel Schudel"));

    }
}
