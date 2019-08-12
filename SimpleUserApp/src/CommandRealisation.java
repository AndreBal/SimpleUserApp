import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class CommandRealisation {
	public static User create(Scanner sc) {
		String[] info = new String[5];
		System.out.println("¬ведите им€ нового пользовател€:");
		info[0] = sc.nextLine();
		System.out.println("¬ведите фамилию:");
		info[1] = sc.nextLine();
		System.out.println("Type number of roles:");
		int numberRoles = Integer.parseInt(sc.nextLine());
		while (numberRoles < 1 || numberRoles > 3) {
			System.out.println("Wrong roles ammount! Retry using numbers from 1 to 3:");
			numberRoles = Integer.parseInt(sc.nextLine());
		}
		System.out.println("Type role:");
		String roles = sc.nextLine();
		for (int i = 1; i < numberRoles; i++) {
			System.out.println("Type next role:");
			roles += "Rdеl" + sc.nextLine();
		}
		info[2] = roles;
		System.out.println("Type email:");
		String email = sc.nextLine();
		boolean isEmailValid = emailValid(email);
		while (!isEmailValid) {
			System.out.println("Wrong email format! Try again:");
			email = sc.nextLine();
			isEmailValid = emailValid(email);
		}
		info[3] = email;
		System.out.println("Type ammount of phones");
		int numberPhones = Integer.parseInt(sc.nextLine());
		while (numberPhones < 1 || numberPhones > 3) {
			System.out.println("Wrong phone ammount! Retry using numbers from 1 to 3:");
			numberPhones = Integer.parseInt(sc.nextLine());
		}
		System.out.println("Type phone number");
		String phones = sc.nextLine();
		boolean isPhoneValid = phoneValid(phones);
		while (!isPhoneValid) {
			System.out.println("Wrong phone format! It should be 375** *******. Retry:");
			phones = sc.nextLine();
			isPhoneValid = phoneValid(phones);
		}
		for (int i = 1; i < numberPhones; i++) {
			System.out.println("Type next phone:");
			String tempPhone = sc.nextLine();
			if(phoneValid(tempPhone)) {
			phones += "Pdеl" + tempPhone;
			}else {
				System.out.println("Wrong phone format! It should be 375** *******. Retry:");
				i--;
			}
		}
		info[4] = phones;

		return new User(info);
	}

	public static void saving(List<User> Users) {
		File newFile = new File(Paths.get(".\\res").toAbsolutePath().toString(), "Users.txt");
		
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile), "UTF16"));
			String nL = System.getProperty("line.separator");
			for(User u: Users) {
				String info = u.getInfo();
				writer.write(info);
				writer.write(nL);
			}	
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
	public static void printAll(List<User> Users) {
		for(User u: Users) {
			System.out.println(u);
			System.out.println("-----------------------");
		}
	}

	public static void  search(List<User> Users,Scanner sc) {
		System.out.println("Type name of desirable user:");
		String user = sc.nextLine().toLowerCase();
		System.out.println("Type Surname of desirable user:");
		user += sc.nextLine().toLowerCase();
		User userto = null;
		for(int i = 0;i<Users.size();i++) {
			if(user.equals((Users.get(i).getName()+Users.get(i).getSurname()).toLowerCase())) {			
				System.out.println(Users.get(i));
				userto = Users.get(i);
				Users.remove(i);
				break;
			}
		}
		if(userto == null) {
			System.out.println("There is no such user");
			return;
		}
		System.out.println("Type \"edit\", to edit desirable user.\nType \"delete\" to delete\nType\"back\" to return back to previous menu");
		String command = sc.nextLine();
		while(true) {
			switch(command) {
			case "edit":
				userto = editUser(userto,sc);
				System.out.println("User successfully edited.\nType \"edit\", to edit desirable user.\nType \"delete\" to delete\nType\"back\" to return back to previous menu");
				command = sc.nextLine();
				break;
			case "delete":
				System.out.println("User successfully deleted. Going back, to previous menu.");
				userto = null;
				command = "back";
				break;
			case "back":
				if(userto!=null) {
					Users.add(userto);
				}
				return;
			default:
				System.out.println("Wrong command!\nType \"edit\", to edit desirable user.\nType \"delete\" to delete\nType\"back\" to return back to previous menu");
				command = sc.nextLine();
				break;	
			}
			}
		
	}
	
	public static User editUser(User us, Scanner sc) {
		System.out.println("type \"name\" to edit it");
		System.out.println("type \"surname\" to edit it");
		System.out.println("type \"role\" to edit it\\them");
		System.out.println("type \"mail\" to edit it");
		System.out.println("type \"phone\" to edit it\\them");
		System.out.println("type \"help\" to see list of commands");
		System.out.println("type \"back\" to return to previous menu");
		String command = sc.nextLine();
		while(!command.equals("back")) {	
			switch(command) {
			case "help":
				System.out.println("type \"name\" to edit it");
				System.out.println("type \"surname\" to edit it");
				System.out.println("type \"role\" to edit it\\them");
				System.out.println("type \"mail\" to edit it");
				System.out.println("type \"back\" to return to previous menu");
				System.out.println("type \"phone\" to edit it\\them");
				command = sc.nextLine();
				break;
			case "name":
				System.out.println("Type new name:");
				String name = sc.nextLine();
				us.setName(name);
				System.out.println("Name changed. Type next command:");
				command = sc.nextLine();
				break;
			case "surname":
				System.out.println("Type new surname:");
				String surname = sc.nextLine();
				us.setSurname(surname);
				System.out.println("Surname changed. Type next command:");
				command = sc.nextLine();
				break;
			case "role":
				System.out.println("Type number of roles");
				int numberRoles = Integer.parseInt(sc.nextLine());
				while (numberRoles < 1 || numberRoles > 3) {
					System.out.println("Wrong roles ammount! Retry using numbers from 1 to 3:");
					numberRoles = Integer.parseInt(sc.nextLine());
				}
				String[] roles = new String[numberRoles];
				System.out.println("Type role:");
				roles[0] = sc.nextLine();
				for (int i = 1; i < numberRoles; i++) {
					System.out.println("Type next role:");
					roles[i] =  sc.nextLine();
				}
				us.setRole(roles);
				System.out.println("Role(s) changed. Type next command:");
				command = sc.nextLine();
				break;
			case "mail":
				System.out.println("Type new email:");
				String email = sc.nextLine();
				boolean isEmailValid = emailValid(email);
				while (!isEmailValid) {
					System.out.println("Wrong email format! Try again:");
					email = sc.nextLine();
					isEmailValid = emailValid(email);
				}
				us.setMail(email);
				System.out.println("Email changed. Type next command:");
				command = sc.nextLine();
				break;
			case "phone":
				System.out.println("Type ammount of phones");
				int numberPhones = Integer.parseInt(sc.nextLine());
				while (numberPhones < 1 || numberPhones > 3) {
					System.out.println("Wrong phone ammount! Retry using numbers from 1 to 3:");
					numberPhones = Integer.parseInt(sc.nextLine());
				}
				System.out.println("Type phone number:");
				String[] phone = new String[numberPhones];
				 String phones = sc.nextLine();
				boolean isPhoneValid = phoneValid(phones);
				while (!isPhoneValid) {
					System.out.println("Wrong phone format! It should be 375** *******. Retry:");
					phones = sc.nextLine();
					isPhoneValid = phoneValid(phones);
				}
				phone[0] = phones;
				for (int i = 1; i < numberPhones; i++) {
					System.out.println("Type next phone:");
					String tempPhone = sc.nextLine();
					if(phoneValid(tempPhone)) {
					phone[i] = tempPhone;
					}else {
						System.out.println("Wrong phone format! It should be 375** *******. Retry:");
						i--;
					}
				}
				command = sc.nextLine();
				break;
			default:
				System.out.println("Wrong command! Type \"help\" to see list of commands." );
				command = sc.nextLine();
				break;	
			}
			}
		return us;
		
	}
	
	public static boolean emailValid(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean phoneValid(String phone) {
		String ePattern = "^375[0-9]{2}\\s[0-9]{7}";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(phone);
		return m.matches();
	}
}
