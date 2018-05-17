package com.github.bogdanovmn.${projectKey}.web.app;

import com.github.bogdanovmn.${projectKey}.core.TranslateService;
import com.github.bogdanovmn.${projectKey}.core.TranslateServiceException;
import com.github.bogdanovmn.${projectKey}.service.google.GoogleTranslate;
import com.github.bogdanovmn.${projectKey}.web.orm.EntityFactory;
import com.github.bogdanovmn.${projectKey}.web.orm.EntityRepositoryMapFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.github.bogdanovmn.${projectKey}.web.orm")
@EntityScan(basePackages = "com.github.bogdanovmn.${projectKey}.web.orm")
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

	@Bean
	public TranslateService getTranslateService() throws TranslateServiceException {
		return new GoogleTranslate();
	}
}

