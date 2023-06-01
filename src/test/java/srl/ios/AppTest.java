package srl.ios;

import static org.junit.jupiter.api.Assertions.assertEquals;

import srl.ios.cli.Cli;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppTest {
    private static ByteArrayOutputStream outContent;

    private static String getStringOutput() {
        return outContent.toString().replace("\r", "");
    }

    @BeforeAll
    public static void setup() {
        outContent = new ByteArrayOutputStream();
    }

    @BeforeEach
    void prepareOut() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testApp() {
        String input = "INSERIMENTO";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        App.main(new String[0]);
        String testmsg = "\nFunzionalità in fase di testing, presenti solo Unit Test."
                + "\nNessuna connessione da interrompere.\n";
        assertEquals(Cli.getWelcomeString() + testmsg, getStringOutput());
    }
}
