package nl.craftsmen.conference;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.anyUrl;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class SpeakerIT {

    @Test
    public void testSpeakerGet() {
        WireMockServer wireMockServer = new WireMockServer(options().port(8081));
        wireMockServer.start();
        JSONArray jsonArray = new JSONArray();
        ResponseDefinitionBuilder responseDefinitionBuilder = new ResponseDefinitionBuilder();
        JSONObject speaker = new JSONObject();
        speaker.put("name", "Michel Schudel");
        jsonArray.add(speaker);
        responseDefinitionBuilder.withBody(jsonArray.toString()).withStatus(200).withHeader("Content-Type", "application/json");
        wireMockServer.stubFor(get(anyUrl()).willReturn(responseDefinitionBuilder));

        when().get("/conferencewithspeakers/{id}", 1).then().log().all().body("speakers[0].name", equalTo("Michel Schudel"));
        wireMockServer.stop();
    }
}
