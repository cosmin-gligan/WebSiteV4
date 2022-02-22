package siit.notes.spring.beans;

import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

//    @Bean
//    public DataSource dataSourceConfig() {
//        JdbcDataSource dataSource = new JdbcDataSource();
//        dataSource.setUrl("jdbc:h2:file:./db/store;AUTO_SERVER=true");
//        dataSource.setUser("sa");
//        dataSource.setPassword("");
//        return dataSource;
//    }
//
//    @Bean("myJdbcTemplate")
//    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}
