package reports;

import java.util.ArrayList;
import java.util.List;

import stocks.Product;
import transactions.IncomingTransaction;
import transactions.OutgoingTransaction;
import transactions.Transaction;
import transactions.TransactionsManager;

public class AllTransactionForProductReport implements Report
{

	private char incomingOrOutgoing;
	private Product product;
	private TransactionsManager tm;

	public AllTransactionForProductReport(char inOrOut, Product p, TransactionsManager tr)
	{
		incomingOrOutgoing = inOrOut;
		product = p;
		tm = tr;
	}

	@Override
	public void printReport()
	{
		System.out.println("Transactions Report for " + product.getName() + ":");
		System.out.println("Products appear in the format: name(id): amount");
		
		
		//To do
	}
}
