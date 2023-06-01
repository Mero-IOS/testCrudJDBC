package srl.ios.cli;

import java.util.Scanner;

public final class TerminalInterface {
    private TerminalInterface() {
    }

    public static String getWelcomeString() {
        return TextConstants.LINE + "\n" + TextConstants.INTRO_STRING + "\n" + TextConstants.INSTRUCTIONS;
    }

    public static ChosenProgram getChosenProgram(String inputtedProgram) {
        ChosenProgram programToExecute = ChosenProgram.INVALID;
        try {
            programToExecute = ChosenProgram.valueOf(inputtedProgram);
        } catch (IllegalArgumentException e) {
            System.out.println("Input non riconosciuto.");
        }
        return programToExecute;
    }

    public static ChosenProgram chooseProgram(Scanner input) {
        ChosenProgram programToExecute = ChosenProgram.INVALID;
        do {
            String line = input.nextLine();
            programToExecute = getChosenProgram(line);
        } while (programToExecute == ChosenProgram.INVALID);
        return programToExecute;
    }

    public static void start() {
        Scanner terminalInput = new Scanner(System.in);

        System.out.println(getWelcomeString());
        ChosenProgram programToExecute = chooseProgram(terminalInput);

        switch (programToExecute) {
            case RICERCA, INSERIMENTO, RIMOZIONE, MODIFICA -> System.out
                    .println(TextConstants.NONFUNCTIONAL);
            default -> System.out.println("Unreachable selection");
        }

        terminalInput.close();
    }
}
