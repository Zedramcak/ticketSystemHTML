package eu.adamzrc.ticketSystemHTML;

import com.github.javafaker.Faker;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Adam Zrcek on 26.03.2021
 */

@Configuration
public class FillDatabase {
    // == constants ==

    // == fields ==

    // == constructors ==

    // == public methods ==
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository){
        if (userRepository.count()<50) {
            Faker faker = new Faker();
            return args -> {
                for (int i = 0; i < 50; i++) {
                    User user = new User();
                    user.setFirstName(faker.name().firstName());
                    user.setLastName(faker.name().lastName());
                    LocalDate localDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    user.setBirthDate(Date.valueOf(localDate));
                    user.setUserName(generateUsername(user));
                    userRepository.save(user);
                }
            };
        }
        return null;
    }
    // == private methods ==
    private String generateUsername(User user){
        String firstName = Normalizer.normalize(user.getFirstName(), Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]","").replaceAll("[^a-zA-Z]","").toLowerCase().substring(0,2);
        String lastName = Normalizer.normalize(user.getLastName(), Normalizer.Form.NFD).replaceAll("[\\p{InCombiningDiacriticalMarks}]","").replaceAll("[^a-zA-Z]","").toLowerCase().substring(0,3);

        return firstName + lastName + LocalDateTime.now().getSecond();
    }
}
