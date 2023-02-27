package reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stocks.Product;
import transactions.OutgoingTransaction;
import transactions.TransactionsManager;

public class ProductByStoreReport implements Report
{

	private int store;
	private TransactionsManager tm;

	public ProductByStoreReport(int s, TransactionsManager tr)
	{
		store = s;
		tm = tr;
	}

	/**
	 * 
	 */
	public void printReport()
	{
		System.out.println("Store Products Report For " + (store == -1 ? "All Stores" : "Store " + store) +
				":\nProducts appear in the format name(id): amount");

		//To do
		//hint: use product map as private Map<Product, Integer> productList;
	}
}