package monsterPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import toolsForMonsters.StringFormatException;
import toolsForMonsters.getRandom;

public class MainMonsters {
	static String newName;
	public static Monster newMonster;
	public static Monster ingemund;

	public static void main(String[] args) throws StringFormatException, IOException {	
		System.out.println();
		System.out.println("                	     .  '  .");
		System.out.println("       __                 _   . ' .   _");
		System.out.println("       )_\\j`-._   _ _._._=_='. .'. .'=_=_._._ _   _.-'t/_(");
		System.out.println("<-.   )_\\F|  F \"_~ ~=~=~=~=~  '   '  ~=~=~=~=~ ~_\" 7  |7/_(   .->");
		System.out.println("   \\___)_\\F  )\\/\"      ' ` ~ .'...'. ~ ' `      \"\\/(  7/_(___/");
		System.out.println("    ()( _)   F_)             . ' . ' .            (_7   (_ )()");
		System.out.println("  _F_\\F,_)/F_\\_,        Hello and welcome!!      ,_/_7\\(_,7/_7_");
		System.out.println();

		System.out.println("    Let's create your monster");
		BufferedReader br1 = new BufferedReader (new InputStreamReader(System.in));
		System.out.println("    Please enter monster's name: ");
		newName = br1.readLine();
		//Check validity of name
		Monster.isValidName(newName); Monster.isValidNameLenght(newName); Monster.isValidNameStart(newName);

		System.out.println("    Say hello to your new Monster " + newName + "!!");
		newMonster = new Monster(newName, getRandom.getRandomNumber(1, 20),getRandom.getRandomNumber(3, 30), getRandom.getRandomNumber(1, 20), getRandom.getRandomNumber(1, 50),getRandom.getRandomNumber(3, 20));


		if((newMonster.getAnchors()<=newMonster.getAnchors()) && (newMonster.getWeightCarried() <= newMonster.getCarryCapacity())) {
			Purse chanel= new Purse(10,1,150,0);
			Purse dolce= new Purse(15,1,170,1);
			Weapon m16 = new Weapon(getRandom.getRandomNumber(1, 30), getRandom.getRandomNumber(2, 30), getRandom.getRandomNumber(10, 30));
			Weapon bazooka = new Weapon(getRandom.getRandomNumber(1, 30), getRandom.getRandomNumber(2, 30), getRandom.getRandomNumber(10, 30));
			Backpack hershel = new Backpack();
			newMonster.pickUpObject(m16);
			newMonster.moveWeaponToRightHand(m16);
			newMonster.pickUpObject(hershel);
			newMonster.pickUpObject(bazooka);
			newMonster.pickUpObject(chanel);
			newMonster.pickUpObject(dolce);
			hershel.add(bazooka, chanel,dolce);

		}
		newMonster.getStats();
		System.out.println();
		System.out.println("Good, now we will build a monster you will fight with, his name is Brother Ingemund and he is a nice fella");

		ingemund = new Monster("Brother Ingemund", getRandom.getRandomNumber(1, 20),getRandom.getRandomNumber(3, 30), getRandom.getRandomNumber(1, 20), getRandom.getRandomNumber(1, 50),getRandom.getRandomNumber(3, 20));
		if((ingemund.getAnchors()<=ingemund.getAnchors()) && (ingemund.getWeightCarried() <= ingemund.getCarryCapacity())) {
			Purse tiss= new Purse(20,2,200,1);
			Weapon lala = new Weapon(getRandom.getRandomNumber(1, 30), getRandom.getRandomNumber(2, 30), getRandom.getRandomNumber(10, 30));
			Weapon lala1 = new Weapon(getRandom.getRandomNumber(1, 30), getRandom.getRandomNumber(2, 30), getRandom.getRandomNumber(10, 30));
			Backpack back = new Backpack();
			ingemund.pickUpObject(lala);
			ingemund.moveWeaponToRightHand(lala);
			ingemund.pickUpObject(tiss);
			ingemund.pickUpObject(back);
			ingemund.pickUpObject(lala1);
			back.add(tiss, lala1);
//			//System.out.println(back.getTotalValue());
		}


		ingemund.getStats();

		ingemund.hitting(newMonster);

	}


}
