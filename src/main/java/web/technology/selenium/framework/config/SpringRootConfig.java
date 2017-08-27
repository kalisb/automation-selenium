package web.technology.selenium.framework.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by kalisb on 28.06.17.
 */
@Configuration
@EnableTransactionManagement

@ComponentScan(basePackages = "web.technology.selenium.framework")
@PropertySource({"classpath:application-config.properties"})
public class SpringRootConfig {

    @Autowired
    private Environment enviornment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] {"web.technology.selenium.framework.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(enviornment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(enviornment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(enviornment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(enviornment.getRequiredProperty("jdbc.password"));
        return dataSource;

    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",enviornment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",enviornment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",enviornment.getRequiredProperty("hibernate.format_sql"));
        //properties.put("hibernate.id.new_generator_mapping", false);
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;

    }

    @SuppressWarnings("deprecation")
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
