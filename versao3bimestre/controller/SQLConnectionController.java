package ch.makery.controller;

import java.sql.Connection;
import java.sql.SQLException;
import ch.makery.util.SQLConnector;
import ch.makery.model.Interface.ISQLFactory;

public class SQLConnectionController {
    
    private static SQLConnectionController onlyInstance;
    private static ISQLFactory connectionFactory;
    
    private SQLConnectionController(){
        connectionFactory = new SQLConnector();
    }
    
    public static SQLConnectionController getInstance(){
        if(onlyInstance == null){
            onlyInstance = new SQLConnectionController();
        }
        return onlyInstance;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return connectionFactory.getConnection();
    }
    
}
