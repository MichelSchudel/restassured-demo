package nl.actorcatalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConferenceRestController {

    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceRestController(final ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }


    @GetMapping("/conference")
    public Iterable<Conference> getAllActors() {
        return conferenceRepository.findAll();
    }

    @GetMapping("/conference/{id}")
    public Conference getActor(@PathVariable Long id) {
        return conferenceRepository.findOne(id);
    }


    @PostMapping("/conference")
    public void saveActor(@RequestBody Conference conference) {
        conferenceRepository.save(conference);
    }

    @DeleteMapping("/conference/{id}")
    public void deleteActor(@PathVariable Long id) {
        conferenceRepository.delete(id);
    }

}
