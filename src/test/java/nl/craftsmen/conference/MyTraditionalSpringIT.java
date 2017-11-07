package nl.craftsmen.conference;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ConferenceApplication.class})
@WebAppConfiguration
public class MyTraditionalSpringIT {

    //insert appcontext

    @Before
    public void
    rest_assured_is_initialized_with_the_web_application_context_before_each_test() {
        //tell restassured to use the app context
    }

    @After
    public void
    rest_assured_is_reset_after_each_test() {
       //teardown restassured

    }

    @Test
    @Sql({"/insert-test-conference.sql"})
    @Rollback
    public void conferenceWithId1ShouldReturnTheTestConference() {
        //write test through restassuredmockmvc
    }

    @Test
    @Sql({"/insert-test-conference.sql"})
    @Rollback
    public void secureConferenceGivenCredentialsShouldReturnAllConferences() {
        //write same test with basic auth
    }
}
