package kangnamuniv.elibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElibraryApplication.class, args);
		//인철테스트
	}

}
