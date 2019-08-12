import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppMain {

	public static void main(String[] args) {
		List<User> Users = readUsers("Users.txt");
	
		System.out.println("Greetings! Program is up and running. Type \"help\" for help");
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		while(!command.equals("exit")) {
			switch(command) {
			case "help":
				System.out.println("type \"create\" to create a new user");
				System.out.println("type \"search\" to find a user (if you want to edit, delete or print one user) ");
				System.out.println("type \"print\" to print all users ");
				System.out.println("type \"exit\" to save data and close the program");
				command = sc.nextLine();
				break;
			case "create":
				User user = CommandRealisation.create(sc);
				Users.add(user);
				System.out.println("User created! Type next command.");
				command = sc.nextLine();
				break;
			case "search":
				CommandRealisation.search(Users, sc);
				System.out.println("Type next command. Type \"help\" for help");
				command = sc.nextLine();
				break;
			case "print":
				CommandRealisation.printAll(Users);
				command = sc.nextLine();
				break;
			default:
				System.out.println("Wrong command. Type \"help\" for help.");
				command = sc.nextLine();
				break;	
			}
			
		}
		CommandRealisation.saving(Users);
	}

	private static List<User> readUsers(String fileName) {
        List<User> Users = new ArrayList<>();
        Path pathToFile = Paths.get(".\\res\\Users.txt");
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_16)) {
            String line = br.readLine();
            while (line != null) {
                String[] info = line.split("DEL");
                User user = new User(info);
                Users.add(user);
                line = br.readLine();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return Users;
	}
}
