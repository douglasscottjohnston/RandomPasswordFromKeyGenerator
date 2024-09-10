package src;

import java.util.Scanner;

public class Main {
    private static final String DEFAULT_ALGORITHM = "AES";
    private static final String DEFAULT_TRANSFORMATION = "AES/ECB/NoPadding";

    public static void main(String[] args) {
        String key;
        String algorithm = DEFAULT_ALGORITHM;
        String transformation = DEFAULT_TRANSFORMATION;

        if(args.length > 0) {
            key = args[0];
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your key: ");
            key = scanner.nextLine();
            scanner.close();
        }

        if (key.isBlank()) {
            throw new IllegalArgumentException("A key was not provided");
        }
        PasswordGenerator passwordGenerator = new PasswordGenerator(key, algorithm, transformation);
        String password = passwordGenerator.generatePassword();
        if(password.isBlank()) {
            System.out.println("The process failed");
        } else {
            System.out.println("Your new password: " + password);
        }
    }
}