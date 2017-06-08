package nl.craftsmen.conference;

import cucumber.api.java.bm.Dan;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RestSteps {

    private RestInterface restInterface;

    @When("request conference \\d")
    public void requestConference(int id) {
        Map<String,String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        restInterface.setURIParameters(map);
        restInterface.callRestService();
    }

    @Then("it should return a conference")
    public void thenConferenceIsReturned() {
        assertEquals(200, restInterface.getStatusCode());
    }

    @And("the attribute \\s should be \\s")
    public void theAttributeShouldBe(String name, String value) {
        String json = restInterface.getJSON();
        //parsing and asserting stuff
    }

}
