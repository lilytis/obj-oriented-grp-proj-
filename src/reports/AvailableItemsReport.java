package reports;

import stocks.Product;
import stocks.StockManager;
import java.util.List;

public class AvailableItemsReport implements Report
{

	private StockManager inventory;

	/**
	 * Default constructor
	 */
	public AvailableItemsReport(StockManager s)
	{
		inventory = s;
	}

	/**
	 * 
	 */
	@Override
	public void printReport() {
		System.out.println("Available Products Report:\nProducts appear in the format: name(id): amount");

		//To do
		//cycle through the product list
		List<Product> products = inventory.getProductList();
		for (Product product : products) {
		//check if the product has value greater than 0 in the list
			if (product.getCount() > 0) { 
			//print each product in the list
				System.out.println(product.getName() + "(" + product.getID() + "): " + product.getCount());
				}
		}
}
}