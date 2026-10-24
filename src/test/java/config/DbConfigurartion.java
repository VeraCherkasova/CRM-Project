package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:configs/db.properties")
public interface DbConfigurartion extends Config {

    String dbUrl();
    String dbUser();
    String dbPassword();
}
