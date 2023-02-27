package reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import stocks.Product;
import transactions.IncomingTransaction;
import transactions.OutgoingTransaction;
import transactions.Transaction;
import transactions.TransactionsManager;

public class HighVolumeReport implements Report
{

	private char incomingOrOutgoing;
	private TransactionsManager tm;

	public HighVolumeReport(char inOrOut, TransactionsManager tr)
	{
		incomingOrOutgoing = inOrOut;
		tm = tr;
	}

	@Override
	public void printReport()
	{
		System.out.println("High Volume " + (incomingOrOutgoing == 'i' ? " Incoming " : " Outgoing ") + "Items:\nProducts appear in the format: name(id): amount");
	/*	int max, min, temp =0;
    int numOfProd=0;
		
      if(incomingOrOutgoing=='i')
      {
        //capturing the list of incoming transactions
        List <IncomingTransaction> incomingTransaction = tm.getIncomingTransactions();

        //need to copy the list of products here
        int tempPro =0;
        String tempNm = " ";
        Product tempProd = new Product(tempNm,tempPro);
  List<Product> Allprod =(tempProd);
        int tempNum;
        int max=0;
        int maxProdId =0;
        for(IncomingTransaction i : incomingTransaction)
          {

            
              
            
          List <Product> products = i.getProductListForTransaction();  
                     for(Product pro: products)
                      { 
                        System.out.println("In the products"
                        tempNum=i.getNumProductInTransaction(products);
                        if(tempNum>max)
                        { 
                          max = i.getNumProductInTransaction(products);
                          maxProdId = products.getID();
                        }
                      }
          }
      
        
//something like... for each transaction, capture the number of products sold. To capture number, use the list of products to cycle through the getNumProductInTransaction(Product p) and capture the amounts returned in an array. add the amount of each product in the array for each iteration using a nested loop
        */
      }
    
      
    }
    
	

