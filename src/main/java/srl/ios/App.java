package srl.ios;

import srl.ios.cli.TerminalInterface;
import srl.ios.sqlconnector.SqlConnection;

public final class App {

    private App() {
    }

    public static void main(String[] args) {
        TerminalInterface.start();
        SqlConnection.disconnect();
    }

}
