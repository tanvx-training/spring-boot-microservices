package tanvx.fsoft;

import fsoft.tanvx.config.CommonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {CommonConfig.class})
public class DestinationServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(DestinationServiceApplication.class, args);
    }
}
