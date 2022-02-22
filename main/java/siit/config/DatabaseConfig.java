package siit.config;

import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;

public class DatabaseConfig {
    private static DatabaseConfig instance = null;

    DataSource dataSource;

    private DatabaseConfig() {
        this.dataSource = getDataSourceConfig();
    }

    public static DataSource getInstance(){
        if(instance == null){
            instance = new DatabaseConfig();
        }
        return instance.dataSource;
    }


    private DataSource getDataSourceConfig() {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:file:./db/store;AUTO_SERVER=true");
        dataSource.setUser("sa");
        dataSource.setPassword("");
        return dataSource;
    }
}
