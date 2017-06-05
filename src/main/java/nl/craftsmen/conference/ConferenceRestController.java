package nl.craftsmen.conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class ConferenceRestController {

    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceRestController(final ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @GetMapping(value = "/conference")
    public ResponseEntity<Iterable<Conference>> getAllConferences(@CookieValue(value="mycookie", required = false) String fooCookie) {
        if (fooCookie != null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok(conferenceRepository.findAll());
    }

    @GetMapping("/conference/{id}")
    public Conference getConference(@PathVariable Long id) {
        return conferenceRepository.findOne(id);
    }

    @PostMapping("/conference")
    public void saveConference(@RequestBody Conference conference) {
        conferenceRepository.save(conference);
    }

    @DeleteMapping("/conference/{id}")
    public void deleteConference(@PathVariable Long id) {
        conferenceRepository.delete(id);
    }


}
