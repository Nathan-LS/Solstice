package solstice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class EsieApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsieApplication.class, args);
    }

}

