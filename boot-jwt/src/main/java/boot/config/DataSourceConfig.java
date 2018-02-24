package boot.config;


import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "ds1")
    @Primary
    @ConfigurationProperties(prefix = "cwbb.datasource.db1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }




   /* @Bean(name = "ds2")
    @ConfigurationProperties(prefix = "cwbb.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }



    @Bean(name = "ds3")
    @ConfigurationProperties(prefix = "cwbb.datasource.db3")
    public DataSource dataSource3() {
        return DataSourceBuilder.create().build();
    }*/

}
