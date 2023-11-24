package peaksoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("peaksoft")
public class LmsBootFinal1Application {

    public static void main(String[] args) {
        SpringApplication.run(LmsBootFinal1Application.class, args);
    }

}
