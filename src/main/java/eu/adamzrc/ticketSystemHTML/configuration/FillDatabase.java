package eu.adamzrc.ticketSystemHTML.configuration;

import com.github.javafaker.Faker;
import eu.adamzrc.ticketSystemHTML.models.Role;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.repositories.RoleRepository;
import eu.adamzrc.ticketSystemHTML.repositories.UserRepository;
import eu.adamzrc.ticketSystemHTML.service.RoleService;
import eu.adamzrc.ticketSystemHTML.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

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
    CommandLineRunner initDatabase(UserRepository userRepository, UserService userService, RoleService roleService){
        while (userRepository.count()<100) {
            Faker faker = new Faker();

            User user = new User();
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            LocalDate localDate = faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            user.setBirthDate(Date.valueOf(localDate));
            Role userRole = roleService.findRoleByName("USER");
            user.setRoles(new HashSet<Role>(Collections.singletonList(userRole)));
            user.setActive(1);
            user.setPassword("password");
            userService.saveUser(user);


        }
        return null;
    }
}
