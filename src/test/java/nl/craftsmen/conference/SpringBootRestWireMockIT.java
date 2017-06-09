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
@TestPropertySource("classpath:test.properties")
public class SpringBootRestWireMockIT {

    @LocalServerPort
    private int randomServerPort;

    @Value("${service.speaker.endpoint}")
    private String speakerEndpoint;

    private WireMockServer wireMockServer;

    @Before
    public void setup() {
        URI uri = URI.create(speakerEndpoint);
        wireMockServer = new WireMockServer(options().port(uri.getPort()));
        wireMockServer.start();
        JSONArray jsonArray = new JSONArray();
        ResponseDefinitionBuilder responseDefinitionBuilder = new ResponseDefinitionBuilder();
        JSONObject speaker = new JSONObject();
        speaker.put("name", "Michel Schudel");
        jsonArray.add(speaker);
        responseDefinitionBuilder.withBody(jsonArray.toString()).withStatus(200).withHeader("Content-Type", "application/json");
        wireMockServer.stubFor(get(anyUrl()).willReturn(responseDefinitionBuilder));

    }

    @After
    public void tearDown() {
        wireMockServer.stop();
    }

    @Sql({"/insert-test-conference.sql"})
    @Test
    @Rollback
    public void testJBCNConference() {
        given().
            port(randomServerPort).
            log().all().
        when().
            get("/conference/{id}", 1).
        then().
            statusCode(200).
            body("name", equalTo("TestConference"));

    }
}
