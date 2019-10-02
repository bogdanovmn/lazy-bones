package ${pkgProjectPrefix}.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.ComponentScan;
import com.github.bogdanovmn.common.spring.menu.MenuConfiguration;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "${pkgProjectPrefix}.model")
@EntityScan(basePackages = "${pkgProjectPrefix}.model")
@ComponentScan(basePackages = {
	"${pkgProjectPrefix}.web",
	"${pkgProjectPrefix}.model.common"
})
@EnableConfigurationProperties({
	MenuConfiguration.class
})
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

