package ${pkgPrefix}.${projectKey}.web.app;

import ${pkgPrefix}.${projectKey}.core.TranslateService;
import ${pkgPrefix}.${projectKey}.core.TranslateServiceException;
import ${pkgPrefix}.${projectKey}.service.google.GoogleTranslate;
import ${pkgPrefix}.${projectKey}.web.orm.EntityFactory;
import ${pkgPrefix}.${projectKey}.web.orm.EntityRepositoryMapFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "${pkgPrefix}.${projectKey}.web.orm")
@EntityScan(basePackages = "${pkgPrefix}.${projectKey}.web.orm")
@EnableTransactionManagement
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public EntityFactory getEntityFactory() {
		return new EntityFactory();
	}

	@Bean(initMethod = "init")
	public EntityRepositoryMapFactory getEntityMapFactory() {
		return new EntityRepositoryMapFactory();
	}
}

