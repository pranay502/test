package mail;

import java.io.File;

public class CurrentDirectoryTest {
	public static void main(String[] args){
		File file = new File(".");
		if(file.exists()){
			System.out.println("Success");
			System.out.println(file.isDirectory());
			System.out.println(file.getAbsolutePath());
		}
	}
}
