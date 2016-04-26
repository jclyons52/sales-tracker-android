package joseph.com.salestracker;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by administrator on 24/04/2016.
 */
public class SalesCollectionTest {

    @Test
    public void it_adds_sales() {

        SalesCollection salesCollection = this.getSalesCollection();

        assertTrue(salesCollection.sales.size() == 2);
    }

    @Test
    public void it_gets_total_sales() {
        SalesCollection salesCollection = this.getSalesCollection();

        assertTrue(salesCollection.totalSales() == 50.0);
    }

    @Test
    public void it_gets_total_cost() {
        SalesCollection salesCollection = this.getSalesCollection();

        assertTrue(salesCollection.totalCost() == 22.0);
    }

    @Test
    public void it_gets_total_profit() {
        SalesCollection salesCollection = this.getSalesCollection();

        assertTrue(salesCollection.totalProfit() == 28.0);
    }

    @Test
    public void it_gets_total_commission() {
        SalesCollection salesCollection = this.getSalesCollection();

        assertTrue(salesCollection.commission(5) == 1.4);
    }

    @Test
    public void it_gets_product_care_ratio() {
        SalesCollection salesCollection = this.getSalesCollection();

        assertTrue(salesCollection.productCareRatio() == 0.5);
    }

    @Test
    public void it_saves_and_loads_sales_collection() {

    }

    private SalesCollection getSalesCollection() {
        Sale sale1 = new Sale(11.0, 25.0, true);

        Sale sale2 = new Sale(11.0, 25.0, false);

        SalesCollection salesCollection = new SalesCollection();

        salesCollection.addSale(sale1);

        salesCollection.addSale(sale2);

        return salesCollection;
    }
}
