package cn.failbetter.daybreak.config;

import cn.failbetter.daybreak.common.repository.ExtendedRepositoryImpl;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author sun 2019/4/25
 */
@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"cn.failbetter.daybreak"}, repositoryBaseClass = ExtendedRepositoryImpl.class)
public class JpaConfig {
}
