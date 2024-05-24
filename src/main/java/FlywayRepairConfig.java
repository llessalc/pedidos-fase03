import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayRepairConfig {

    private final Flyway flyway;

    public FlywayRepairConfig(Flyway flyway) {
        this.flyway = flyway;
    }

    @Bean
    public CommandLineRunner repairFlyway() {
        return args -> {
            flyway.repair();
        };
    }
}