package mwsu.springframework.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by bingyang.wei on 5/8/2017.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("mwsu.springframework")
public class JpaIntegrationConfig {


}
