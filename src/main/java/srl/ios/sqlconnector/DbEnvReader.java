package srl.ios.sqlconnector;

import io.github.cdimascio.dotenv.Dotenv;

final class DbEnvReader {

    private static final String DB_PASSWORD = Dotenv.load().get("DB_PASSWORD");
    private static final String DB_USER = Dotenv.load().get("DB_USERNAME");
    private static final String DB_URL = Dotenv.load().get("DB_URL");

    private DbEnvReader() {
    }

    protected static String getDbUrl() {
        return DB_URL;
    }

    protected static String getDbUser() {
        return DB_USER;
    }

    protected static String getDbPassword() {
        return DB_PASSWORD;
    }
}
