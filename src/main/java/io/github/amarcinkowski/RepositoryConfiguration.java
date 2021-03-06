package io.github.amarcinkowski;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "io.github.amarcinkowski.book.domain" })
@EnableJpaRepositories(basePackages = { "io.github.amarcinkowski.book.repository" })
@EnableTransactionManagement
public class RepositoryConfiguration {

}
