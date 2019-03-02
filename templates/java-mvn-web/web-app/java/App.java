package ${pkgProjectPrefix}.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "${pkgProjectPrefix}.web.orm")
@EntityScan(basePackages = "${pkgProjectPrefix}.web.orm")
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

