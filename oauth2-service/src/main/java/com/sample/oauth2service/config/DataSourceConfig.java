package com.sample.oauth2service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

//--------------------------------------------------------------
// configure mysql for jdbc datasource
//--------------------------------------------------------------
@Configuration
public class DataSourceConfig {

    @Value("classpath:schema.sql")
    private Resource schema;
    @Value("classpath:data.sql")
    private Resource data;

    private DataSource dataSource;

    @Autowired
    public DataSourceConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer() {
        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        return dataSourceInitializer;
    }

    //--------------------------------------------------------------
    // Strategy used to populate, initialize, or clean up a database.
    //--------------------------------------------------------------
    private DatabasePopulator databasePopulator() {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(schema);
        resourceDatabasePopulator.addScript(data);
        return resourceDatabasePopulator;
    }
}
