package nl.craftsmen.conference;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ConferenceControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ConferenceRepository conferenceRepository;

    @Test
    public void exampleTest() throws Exception {
        Conference conference = new Conference();
        conference.setId(1L);
        conference.setName("testConference");
        conference.setDescription("just for test");
        when(conferenceRepository.findAll()).thenReturn(Arrays.asList(conference));
        this.mvc.perform(get("/conference").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().string("[{\"id\":1,\"name\":\"testConference\",\"description\":\"just for test\"}]"));
    }
}
