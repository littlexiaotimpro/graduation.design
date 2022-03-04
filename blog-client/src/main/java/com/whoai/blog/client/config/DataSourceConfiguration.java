package com.whoai.blog.client.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * DataSourceConfiguration
 * 数据源配置，数据事务管理配置
 *
 * @date 2022/03/04
 */
@Configuration
@EnableConfigurationProperties({DataSourceProperties.class})
// 启用事务管理
@EnableTransactionManagement(proxyTargetClass = true)
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource(DataSourceProperties properties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }

    /**
     * 创建事务管理器对象
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }

}
