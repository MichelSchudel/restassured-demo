package nl.actorcatalogue;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * @author Michel Schudel
 */
public class ActorRestIT {

    @Test
    public void test() {
        when().
                get("/actor/{id}", 1).
                then().
                statusCode(200).
                body("name", equalTo("Mark Hamill"),
                        "id", equalTo(1));

    }

    @Test
    public void test2() {
        when().
                get("/actor").then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));

    }
}
