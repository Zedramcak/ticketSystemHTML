package eu.adamzrc.ticketSystemHTML.configuration;

import com.github.javafaker.Faker;
import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.service.RoleService;
import eu.adamzrc.ticketSystemHTML.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.ZoneId;
import java.util.Collections;
import java.util.HashSet;


@Configuration
public class FillDatabase {

    private final String PASSWORD = "password";
    Faker faker = new Faker();
    @Autowired
    RoleService roleService;

    @Bean
    CommandLineRunner addUsersToDatabaseIfThereAreLessThan100Users(UserService userService){
        while (userService.findAll().size()<100) {
            userService.saveUser(createUser());
        }
        return null;
    }

    private User createUser(){
        User user = new User();
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setBirthDate(createRandomBirthday());
        user.setActive(1);
        user.setPassword(PASSWORD);
        setUsersRoleToUSER(user);
        return user;
    }

    private Date createRandomBirthday(){
        return Date.valueOf(faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    private void setUsersRoleToUSER(User user){
        user.setRoles(new HashSet<>(Collections.singletonList(roleService.findRoleByName("USER"))));
    }
}
