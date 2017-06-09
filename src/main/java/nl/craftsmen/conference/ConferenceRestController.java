package nl.craftsmen.conference;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Api
@RestController
public class ConferenceRestController {

    private ConferenceRepository conferenceRepository;

    @Value("${service.speaker.endpoint}")
    private String speakerServiceEndpoint;

    private RestTemplate restTemplate;

    @Autowired
    public ConferenceRestController(final ConferenceRepository conferenceRepository, RestTemplate restTemplate) {
        this.conferenceRepository = conferenceRepository;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/conference")
    public Iterable<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    @GetMapping(value = "/conference", params = {"id"})
    public Conference getConferenceWithRequestParam(@RequestParam Long id) {
        return getConference(id);
    }

    @GetMapping("/conference/{id}")
    public Conference getConferenceWithPathParam(@PathVariable Long id) {
        return getConference(id);
    }

    private Conference getConference(Long id) {
        Conference conference = conferenceRepository.findOne(id);
        if (conference == null) {
            throw new NotFoundException();
        } else {
            return conference;
        }
    }

    @PostMapping("/conference")
    public Conference saveConference(@Valid @RequestBody Conference conference) {
        conferenceRepository.save(conference);
        return conference;
    }

    @DeleteMapping("/conference/{id}")
    public void deleteConferenceWithPathParam(@PathVariable Long id) {
        conferenceRepository.delete(id);
    }

    @DeleteMapping(value = "/conference", params = {"id"})
    public void deleteConferenceWithRequestParam(@RequestParam Long id) {
        conferenceRepository.delete(id);
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
}
