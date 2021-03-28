package eu.adamzrc.ticketSystemHTML;

import eu.adamzrc.ticketSystemHTML.models.User;
import eu.adamzrc.ticketSystemHTML.repositories.RoleRepository;
import eu.adamzrc.ticketSystemHTML.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TicketSystemHtmlApplication {


	public static void main(String[] args) {
		SpringApplication.run(TicketSystemHtmlApplication.class, args);
	}



}
