package nl.actorcatalogue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ActorRestController {

    private ActorRepository actorRepository;

    @Autowired
    public ActorRestController(final ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }


    @RequestMapping(value = "actor", method = RequestMethod.GET)
    public Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    @RequestMapping(value = "actor/{id}", method = RequestMethod.GET)
    public Actor getActor(@PathVariable Long id) {
        return actorRepository.findOne(id);
    }


    @RequestMapping(value = "actor", method = RequestMethod.POST)
    public void saveActor(@RequestBody Actor actor) {
        actorRepository.save(actor);
    }

    @RequestMapping(value = "actor{id}", method = RequestMethod.DELETE)
    public void deleteActor(@PathVariable Long id) {
        actorRepository.delete(id);
    }

}
