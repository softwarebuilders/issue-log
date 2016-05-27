package cz.softwarebuilders.issuelog.persistence.dao;

import org.hsqldb.jdbc.JDBCDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PreDestroy;

@Repository
@Transactional
@Profile("!test")
public class ShutdownHsqlDbDao {
    @Autowired
    private JDBCDataSource jdbcDataSource;

    @PreDestroy
    public void shutdownDb() {
        System.out.println("SHUTTING DOWN HSQLDB!");
        new JdbcTemplate(jdbcDataSource).execute("SHUTDOWN");
        System.out.println("HSQLDB SUCCESSFULLY SHUT DOWN!");
    }
}
