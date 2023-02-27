package reports;

import java.util.ArrayList;
import java.util.List;

import stocks.Product;
import transactions.IncomingTransaction;
import transactions.OutgoingTransaction;
import transactions.Transaction;
import transactions.TransactionsManager;

public class TransactionsByMonthReport implements Report
{

	private char incomingOrOutgoing;
	private int month;
	private TransactionsManager tm;

	public TransactionsByMonthReport(char inOrOut, int mon, TransactionsManager tr)
	{
		incomingOrOutgoing = inOrOut;
		month = mon;
		tm = tr;
	}

	@Override
	public void printReport()
	{
		System.out.println("All Transactions Report" + (month != -1 ? " for " + getMonthName(month) : "") + ":\nProducts appear in the format: name(id): amount");
	
    
       
          if (incomingOrOutgoing == 'i'){
            List<IncomingTransaction> incomingTransaction = tm.getIncomingTransactions();
         
            for(IncomingTransaction i : incomingTransaction){
                List <Product> products = i.getProductListForTransaction(); 
                if(month==i.getMonth()){
                  
                  for(Product pro: products){
                  System.out.println(pro.getName() + "(" + pro.getID() + "): " + i.getNumProductInTransaction(pro));
                  }
                  
                  }
                  
                }
            }
        
      
         else if (incomingOrOutgoing == 'o'){
            List<OutgoingTransaction> outgoingTransaction = tm.getOutgoingTransactions();
         
            for(OutgoingTransaction o : outgoingTransaction){
                List <Product> products = o.getProductListForTransaction(); 
                if(month==o.getMonth()){
                  
                  for(Product pro: products){
                  System.out.println(pro.getName() + "(" + pro.getID() + "): " + o.getNumProductInTransaction(pro));
                  }
                  
                  }
                  
                }
            }


    if(month<1||month>12)
      {  
          if (incomingOrOutgoing == 'i'){
            List<IncomingTransaction> incomingTransaction = tm.getIncomingTransactions();
         
            for(IncomingTransaction i : incomingTransaction){
                List <Product> products = i.getProductListForTransaction(); 
                
                  
                  for(Product pro: products){
                  System.out.println(pro.getName() + "(" + pro.getID() + "): " + i.getNumProductInTransaction(pro));
                  }
                  
                  
                  
                }
            }else
            {
                if (incomingOrOutgoing == 'o'){
            List<OutgoingTransaction> outgoingTransaction = tm.getOutgoingTransactions();
         
            for(OutgoingTransaction o : outgoingTransaction){
                List <Product> products = o.getProductListForTransaction(); 
               
                  
                  for(Product pro: products){
                  System.out.println(pro.getName() + "(" + pro.getID() + "): " + o.getNumProductInTransaction(pro));
                  }
                  
                  
                  
                }
            }
      }
               
            
        
      }
  
  }

	private String getMonthName(int i)
	{
		String months[] = new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
		return months[i];
	}

}
