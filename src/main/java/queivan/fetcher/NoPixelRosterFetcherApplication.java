package queivan.fetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NoPixelRosterFetcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoPixelRosterFetcherApplication.class, args);
    }

}
