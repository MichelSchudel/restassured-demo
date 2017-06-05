package nl.craftsmen.conference;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.json.simple.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class PerfIT {

    @Rule
    public ContiPerfRule contiPerfRule = new ContiPerfRule();

    @Test
    @PerfTest(invocations = 10)
    @Required(percentile95 = 1000)
    public void testPerformance() {
        given()
                .when()
                .get("/conference");

    }
}
