package p1.teamtodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TeamtodoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamtodoApplication.class, args);
	}

}
