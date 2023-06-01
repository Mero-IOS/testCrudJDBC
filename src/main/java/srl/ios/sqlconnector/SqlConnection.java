package srl.ios.sqlconnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class SqlConnection {

    private static Connection dbConnected;

    private SqlConnection() {
    }

    private static void connect() {
        try {
            dbConnected = DriverManager.getConnection(DbEnvReader.getDbUrl(), DbEnvReader.getDbUser(),
                    DbEnvReader.getDbPassword());
        } catch (SQLException e) {
            System.out.println("Database connection could not be establised: " + e.getMessage());
        }
    }

    public static void disconnect() {
        try {
            dbConnected.close();
            dbConnected = null;
        } catch (SQLException e) {
            System.out.println("Eccezione SQL:" + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("Nessuna connessione da interrompere.");
        }
    }

    public static Connection getConnection() {
        if (dbConnected == null) {
            connect();
        }
        return dbConnected;
    }

}
