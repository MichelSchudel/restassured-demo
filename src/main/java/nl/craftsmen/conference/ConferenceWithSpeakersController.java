package nl.craftsmen.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ConferenceWithSpeakersController {

    @Value("${service.speaker.endpoint}")
    private String speakerServiceEndpoint;
    private ConferenceRepository conferenceRepository;
    private RestTemplate restTemplate;

    @Autowired
    public ConferenceWithSpeakersController(final ConferenceRepository conferenceRepository, RestTemplate restTemplate) {
        this.conferenceRepository = conferenceRepository;
        this.restTemplate = restTemplate;
    }
    @GetMapping("/conferencewithspeakers/{id}")
    public Conference getConferenceWithSepakersWithPathParam(@PathVariable Long id) {
        Conference conference = getConference(id);

        ResponseEntity<List<Speaker>> response = restTemplate.exchange(speakerServiceEndpoint + "/speaker",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Speaker>>() {
                });
        conference.setSpeakers(response.getBody());
        return conference;
    }

    private Conference getConference(Long id) {
        Conference conference = conferenceRepository.findOne(id);
        if (conference == null) {
            throw new NotFoundException();
        } else {
            return conference;
        }
    }
}
