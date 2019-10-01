package ch.makery.util;

import ch.makery.model.Interface.ISQLFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector implements ISQLFactory {

    public final String databaseDriver = "org.postgresql.Driver";
    private final static String databaseURL = "jdbc:postgresql://host:5432/AddressApp";
    private final static String user = "postgres";
    private final static String password = "123456";

    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(databaseDriver);
        return DriverManager.getConnection(databaseURL, user, password);
    }
    
}
