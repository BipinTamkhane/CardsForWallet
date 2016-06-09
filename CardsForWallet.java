import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CardsForWallet {
	int noOfSlots = 3; // Initialization of Default Number of Slots.
	Map<Integer, String> cardMap = new LinkedHashMap<>(); // To store Card ID & Card Name
	Set<Integer> usedCards = new LinkedHashSet<>(); // To store ID of Used Card.
	int counter = 0; // To Generate the ID for the Card
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		CardsForWallet cfr = new CardsForWallet();
		boolean exit = false;
		
		System.out.println("Default no of slots is "+ cfr.noOfSlots);
		while(!exit) {
			boolean correctInput = false;
			
			while(!correctInput) {
				System.out.println("\n1. Reset\n2. Add new card\n3. Use existing card\n"
						+ "4. Show cards for wallet");
				System.out.println("\nEnter choice (Q to exit) ->> ");
				
				correctInput = true;
				cfr.sc = new Scanner(System.in);
				String choice = cfr.sc.next();
				switch(choice) {
				case "1":
					cfr.reset();
					break;
					
				case "2":
					cfr.addNew();
					break;
					
				case "3":
					cfr.useExisting();
					break;
					
				case "4":
					cfr.showCards();
					break;
					
				case "Q":
					exit = true;
					System.out.println("Bye!");
					break;
					
				default:
					System.out.println("\nInvalid input!");
					correctInput = false;
					break;
				}
			}
		}
		cfr.sc.close();
	}
	
        // This Method is used to Reset the Wallet.
	public void reset() {
		cardMap.clear();
		usedCards.clear();
		counter = 0;
		System.out.println("No of slots ->> ");
		try {
			noOfSlots = sc.nextInt();
		} catch(InputMismatchException e) {
			System.out.println("Enter integer values only!!!");
		}
		System.out.println("--------------------------------------------");
	}
	
        //This Method is used to Add new Card
	public void addNew() {
		System.out.println("Card Name ->> ");
		String cardName = sc.next();
		cardMap.put(++counter, cardName);
		usedCards.add(counter);
		System.out.println("->> Unique ID of " + cardName + " is " + counter);
		System.out.println("--------------------------------------------");
	}
	
        //This method is used to Use Existing Card from Wallet.
	public void useExisting() {
		System.out.println("Unique ID of card ->> ");
		boolean error = true;
		try {
			Integer cardID = sc.nextInt();
			if(cardMap.containsKey(cardID)) {
				usedCards.remove(cardID);
				usedCards.add(cardID);
				error = false;
			}
		} catch(InputMismatchException e) {
			error = true;
		}
		
		if(error)
			System.out.println("Invalid Card ID");
		System.out.println("--------------------------------------------");
	}
        
	//This method is used to Display Available Cards in Wallet. 
	public void showCards() {
		if(usedCards.size() > 0) {
			Set<Integer> finalSet = new HashSet<>();
			int count = 0;
			Iterator<Integer> it = usedCards.iterator();
			while(it.hasNext()) {
				int x = it.next();
				if(usedCards.size() < noOfSlots || count >= (usedCards.size()) - noOfSlots)
					finalSet.add(x);
				count++;
			}
			
			it = finalSet.iterator();
			
			int sNo = 1;
			while(it.hasNext()) {
				int id = it.next();
				System.out.println("->> " + sNo++ + ". " + cardMap.get(id) + 
						" (Card-ID : " + id + " )");
			}
		} else
			System.out.println("No card usages found !!!");
		System.out.println("--------------------------------------------");
	}
}
