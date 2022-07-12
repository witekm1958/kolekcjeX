package pl.wszib.kolekcje;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db.properties")
public class KolekcjeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KolekcjeApplication.class, args);
	}

}
