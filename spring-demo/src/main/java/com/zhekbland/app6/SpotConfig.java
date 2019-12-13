package com.zhekbland.app6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Using class like a configuration file without XML (no XML).
 * And we are using @Bean classes and their dependency without annotations in classes!
 * Also we use property configuration for fields in a SwimCoach class!
 *
 * @author Evgeny Shpytev (mailto:eshpytev@mail.ru).
 * @version 1.
 * @since 12.12.2019.
 */
@Configuration
@PropertySource("classpath:sport.properties")
public class SpotConfig {

    /**
     * Define bean for our sad fortune service
     * @return new service
     */
    @Bean
    public FortuneService sadFortuneService() {
        return new SadFortuneService();
    }

    /**
     * Define bean for our swim coach and inject dependency
     * @return new ready SwimCoach with injected dependency!
     */
    @Bean
    public Coach swimCoach() {
        return new SwimCoach(sadFortuneService());
    }
}
