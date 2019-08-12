
public class User {
	private String name;
	private String surname;
	private String[] role;
	private String mail;
	private String[] phone;

	public User(String[] info){
		this.name = info[0];
		this.surname = info[1];
		this.role = info[2].split("Rdеl");
		this.mail = info[3];
		this.phone = info[4].split("Pdеl");
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public void setRole(String[] role) {
		this.role = role;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	@Override
    public String toString() {
		String user = surname+" "+name+"\n"+"Роли: ";
		for(String s:role) {
			user+=s+" ,";
		}
		user = user.substring(0, user.length() - 1);
		user+="\nЭлектронная почта: "+mail+"\n"+"Телефоны: ";
		for(String s:phone) {
			user+=s+" ,";
		}
		user = user.substring(0, user.length() - 1);
        return user;
    }

	public String getInfo() {
		String info = name+"DEL"+surname+"DEL";
		for(String s:role) {
			info+=s+"Rdеl";
		}
		info = info.substring(0, info.length() - 4);
		info+="DEL"+mail+"DEL";
		for(String s:phone) {
			info+=s+"Pdеl";
		}
		info = info.substring(0, info.length() - 4);
		return info;
	}
	
}
