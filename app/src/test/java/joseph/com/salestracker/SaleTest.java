package joseph.com.salestracker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by administrator on 24/04/2016.
 */
public class SaleTest {

    @Test
    public void it_creates_a_new_sale_object() {
        Sale sale = new Sale(11.0, 22.0, true);

        assertEquals(Double.valueOf(11), sale.purchaseCost);
        assertEquals(Double.valueOf(22), sale.saleCost);
        assertEquals(true, sale.productCare);
    }

    @Test
    public void it_gets_profit_for_sale() {
        Sale sale = new Sale((double) 11, (double) 25, true);

        assertEquals(Double.valueOf(14), sale.profit());
    }
}
