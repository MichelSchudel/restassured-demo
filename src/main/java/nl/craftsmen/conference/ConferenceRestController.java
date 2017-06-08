package nl.craftsmen.conference;

import javax.security.auth.login.Configuration;
import javax.validation.Valid;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class ConferenceRestController {

    private ConferenceRepository conferenceRepository;

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

    @DeleteMapping(value = "/conference",params = {"id"})
    public void deleteConferenceWithRequestParam(@RequestParam Long id) {
        conferenceRepository.delete(id);
    }

}
