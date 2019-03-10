package ${pkgProjectPrefix}.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "${pkgProjectPrefix}.web.orm")
@EntityScan(basePackages = "${pkgProjectPrefix}.web.orm")
@ComponentScan(basePackages = {
	"${pkgProjectPrefix}.web.app",
	"${pkgProjectPrefix}.web.orm.common"
})
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

