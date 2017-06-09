package nl.craftsmen.conference;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api
@RestController
public class ConferenceRestController {

    private ConferenceRepository conferenceRepository;

    @Value("${service.speaker.endpoint}")
    private String speakerServiceEndpoint;


    @Autowired
    public ConferenceRestController(final ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;

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


}
