package reports;

import stocks.Product;
import stocks.StockManager;

/**
 * 
 */
public class AllItemsEnteredReport implements Report
{
	private StockManager inventory;

	/**
	 * Default constructor
	 */
	public AllItemsEnteredReport(StockManager s)
	{
		inventory = s;
	}

	/**
	 * 
	 */
	@Override
	public void printReport()
	{
		System.out.println("All Products Report:\nProducts appear in the format: name(id): amount");
		//To do
		
		//cycle through product list
		inventory.getProductList().forEach((product) -> {
			//print each product in product list
			System.out.println(product.getName() + "(" + product.getID() + "): " + product.getCount());
		});
		
	}
}
