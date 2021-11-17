package com.zzb.tutorial.mahoutdemo.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public DataModel getMySQLDataModel() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName("xxxx");
        dataSource.setUser("xxxx");
        dataSource.setPassword("xxxx");
        dataSource.setDatabaseName("movie_test");

        JDBCDataModel dataModel = new MySQLJDBCDataModel(dataSource, "ratings", "userId", "movieId", "rating", "timestamp");

        return dataModel;
    }
}
