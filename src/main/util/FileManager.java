package main.util;

import java.util.*;
import java.io.FileWriter;
import stocks.Product;
import stocks.StockManager;
import stocks.Store;
import transactions.IncomingTransaction;
import transactions.OutgoingTransaction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FileManager {

	static String incomingTransactionsFile = "incoming_transactions.txt";
	static String outgoingTransactionsFile = "outgoing_transactions.txt";
	static String productsFile = "products.txt";
	static String storesFile = "stores.txt";
	static String DELIMITER = ",";
	
	public String readFile(Path filePath) throws IOException {
		String content = "";
		if(Files.notExists(filePath)){
			Files.createFile(filePath);
		}else{
			content = Files.readString(filePath);
		}
		return content;
	}
	  
	public void writeFile(Path filePath, String text) throws IOException {
		if(Files.notExists(filePath)){
			Files.createFile(filePath);
		}
		
		Files.writeString(filePath, text);
	}
	
	public List<Product> readProducts() {
		
		List<Product> products= new ArrayList<Product>();
		Scanner scnr = null;

		try {
			String readIt = readFile(Paths.get("../" + productsFile));
			scnr = new Scanner(readIt);
			
			while(scnr.hasNextLine()) {
				String holder = scnr.nextLine();
				String[] words = holder.split(DELIMITER);
				
				Product newProduct = new Product(words[1],Integer.parseInt(words[2]));
				products.add(newProduct);
			}
		}
		catch(IOException leave) {
			System.out.println(leave.toString());
		}
		scnr.close();
		return products;
	}
	
	public void writeProducts(List<Product> products) {

		try {
			FileWriter writer = new FileWriter("../" + productsFile);
			for(Product pro : products) {
				writer.write(pro + System.lineSeparator());
			}
			writer.close();
		}
		catch(IOException leave){
			System.out.println(leave.toString());
		}
	}
    
	public List<Store> readStores() {
    
		List<Store> stores= new ArrayList<Store>();
	
  		Scanner scnr = null;

		try {
			String readIt = readFile(Paths.get("../" + storesFile));
			scnr = new Scanner(readIt);
			
			while(scnr.hasNextLine()) {
				String holder = scnr.nextLine();
				String[] words = holder.split(DELIMITER);
				Store newStore = new Store(words[1],words[2]);
      
				stores.add(newStore);
			}
		}
		catch(IOException leave) {
			System.out.println(leave.toString());
		}
				
		scnr.close();
    
		return stores;
	}
  
	public void writeStores(List<Store> stores) {

		try {
			FileWriter writer = new FileWriter("../"+storesFile);
			
			for(Store sto:stores) {
				 writer.write(sto+System.lineSeparator());
			}
		    writer.close();
		} catch(IOException leave){
		 	System.out.println(leave.toString());
		 }
    }
  
	public List<IncomingTransaction> readIncomingTransactions(StockManager sm) {
		List<IncomingTransaction> its = new ArrayList<IncomingTransaction>();
		// To do
		// read incoming transactions from incomingTransactionsFile
		IncomingTransaction currentInTrans = null;

		Path path = Paths.get("../incoming_transactions.txt");
		List<String> strings = null;
		try {
			strings = Files.readAllLines(path);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}


		for (String string : strings) {

			String[] words = string.split(DELIMITER);
			if (string.contains(":")) {

				try{
					SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
					Date date = formatter.parse(words[1]);

					IncomingTransaction out = new IncomingTransaction(date);
					currentInTrans = out;
					its.add(out);
				}catch(ParseException e){
					System.out.println(e.getMessage());
				}

			} else if (!string.isBlank()) {
				// add products to object
				try {
					Product product = sm.findProductByID(Integer.parseInt(words[0]));
					currentInTrans.addProduct(product, Integer.parseInt(words[1]));
				} catch (Exception e) {
					System.out.println("Error in addProduct in readIncomingTransactions:: " + e.getMessage());
				}
			}
		}

		return its;
	}


	public void writeIncomingTransactions(List<IncomingTransaction> its) {
		//To do
		// write incoming transactions to incomingTransactionsFile
		String blank = "";
		try {

			String s = "../incoming_transactions.txt";
			Path path = Paths.get(s);
			Files.write(path, blank.getBytes(StandardCharsets.UTF_8), StandardOpenOption.TRUNCATE_EXISTING);
			for (IncomingTransaction in : its) {
				String data = in.toString() + System.lineSeparator();
				Files.write(Paths.get("../"+incomingTransactionsFile),data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<OutgoingTransaction> readOutgoingTransactions(StockManager sm) {
		List<OutgoingTransaction> ots= new ArrayList<OutgoingTransaction>();
		//To do
		// read outgoing transactions from outgoingTransactionsFile
		
		//format to create a new Date object from a string
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		
		//encompass code that could throw IOException from Paths.get(...)
		try {
			//keeps track of currect OutComingTransaction object to work on
			OutgoingTransaction currentOT = null;
			//get all the lines from the outgoing_transactions.txt
			List<String> lines = Files.readAllLines(Paths.get("../" + outgoingTransactionsFile));
			
			//iterate thorugh all the lines we received
			for(String line: lines){
				//holds strings that were sepeaated by DELIMITER
				String[] words = line.split(DELIMITER);
				
				// if a line contains a ':' it means we need to make a new OutgoingTransaction object
				// because the date is in that specific line
				if(line.contains(":")){
					
					//encompass code that could thorw ParseException from format.parse(...)
					try{
						//make a date based on format
						Date date = format.parse(words[2]);
						//make new OutgoingTransaction object
						OutgoingTransaction outgoing = new OutgoingTransaction(sm.findStoreByID(Integer.parseInt(words[0])),date);
						//keep track of current OT object until we make a new one
						currentOT = outgoing;
						//add outgoing to the list ots
						ots.add(outgoing);
					// catch ParseException from format.parse(...)
					}catch (ParseException e){
						System.out.println("Error in making Date Object in readOutGoingTransactions:: " + e.getMessage());
					}
				//if the line isn't blank that means we still have products to add to currentOT
				} else if(!line.isBlank()){ 
					//create a new product and add it to currentOT
					Product product = sm.findProductByID(Integer.parseInt(words[0]));
					currentOT.addProduct(product, Integer.parseInt(words[1]));
				}
			}			
		//catch IOException from Paths.get(...)
		}catch (IOException e) {
			System.out.println("Error IO in readOutGoingTransactions:: " + e.getMessage());
		}
		
		return ots;
	}
	
	public void writeOutgoingTransactions(List<OutgoingTransaction> ots) {
		//To do
		// write outgoing transactions to outgoingTransactionsFile
		
		//encompass code that could throw IOExceoption from Paths.get(...)
		try {
			//holds info to be printed
			String data = "";
			//clear old text in outgoing_transactions.txt
			Files.write(Paths.get("../" + outgoingTransactionsFile),data.getBytes(StandardCharsets.UTF_8),StandardOpenOption.TRUNCATE_EXISTING);
			//loop throrugh OutgoingTransaction list , ots
			for(OutgoingTransaction out: ots){
				data = out.toString() + System.lineSeparator();
				//write out data to outgoing_transactions.txt
				Files.write(Paths.get("../" + outgoingTransactionsFile),data.getBytes(StandardCharsets.UTF_8),StandardOpenOption.APPEND);
			}
		//catch IOException from Paths.get(...)
    		} catch (IOException e) {
            		System.out.println(e.getMessage());
		}
	}
}