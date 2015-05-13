package ee.tlu.base;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

import static ee.tlu.base.Data.*;

public class GenerateMaps {
	
	private boolean generator;
	private String name, content;
	Scanner scanner;
	
	public GenerateMaps() throws SQLException{
		generator = true;
		while (generator) {
			System.out.println("Sisesta nimi: ");
			name = scanner.nextLine();
			/*System.out.println("Sisesta kaart: ");
			content = sc.nextLine();*/
			content = generateContent();
			addMap(name, content);
			/*	System.out.println("Sisesta mitu id tahad kustutada: ");
				count = sc.nextInt();
				ids = new int[count];
				for (int i = 0; i < ids.length; i++) {
					System.out.printf("Sisesta id %d/%d: ", i+1, ids.length);
					ids[i] = sc.nextInt();	
				}
				deleteMaps(ids);
			 */
		}
	}
	
	private String generateContent() {
		/*for i in range (300):
	        number = randint(0,2)
	        z += str(number)
	    print(z)
	    input()*/
	    Random random = new Random();
	    String s = "";
	    for (int i = 0; i < 300; i++) {
			int number = random.nextInt(2);
			s += number+"";
		}
		return s;
	}
	
	public static void main(String[] args) throws SQLException {
		new GenerateMaps();
	}

}
