package nl.craftsmen.conference;

        import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class ConferenceApplication {

    public static void main(String args[]) {
        SpringApplication.run(ConferenceApplication.class);
    }



}
