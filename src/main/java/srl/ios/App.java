package srl.ios;

import srl.ios.cli.Cli;
import srl.ios.sqlconnector.SqlConnection;

public final class App {

    private App() {
    }

    public static void main(String[] args) {
        Cli.start();
        SqlConnection.disconnect();
    }

}
