package config;

public class ConfigProvider {

    public static final TestConfiguration TEST_CONFIGURATION = org.aeonbits.owner.ConfigFactory.create(TestConfiguration.class);
    public static final DbConfigurartion TEST_CONFIGURATION_DB = org.aeonbits.owner.ConfigFactory.create(DbConfigurartion.class);

}
