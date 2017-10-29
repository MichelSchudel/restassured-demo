package nl.craftsmen.conference;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:test.properties")
public class SpringBootRestITSolution {

    @LocalServerPort
    private int randomServerPort;

    @Sql({"/insert-test-conference.sql"})
    @Test
    @Rollback
    public void conferenceWithId1ShouldReturnTheTestConference() {
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
