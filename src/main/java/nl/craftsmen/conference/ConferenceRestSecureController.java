package nl.craftsmen.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceRestSecureController {

    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceRestSecureController(final ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }


    @GetMapping(value = "/secure/conference")
    public Iterable<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }
}
