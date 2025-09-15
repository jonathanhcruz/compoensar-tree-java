package CustomSystem.Input;

import java.util.Scanner;

public class SystemGetInput {
  final private String stringToPrint;

    public SystemGetInput(String stringToPrint) {
        this.stringToPrint = stringToPrint;
    }

    public String getResponse () {
        final Scanner scanner = new Scanner(System.in);
        System.out.println(stringToPrint);
        return scanner.nextLine();
    }
}
