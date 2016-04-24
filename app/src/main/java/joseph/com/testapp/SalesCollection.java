package joseph.com.testapp;

import java.util.ArrayList;

/**
 * Created by administrator on 24/04/2016.
 */
public class SalesCollection {

    public ArrayList<Sale> sales;

    {
        sales = new ArrayList<Sale>();
    }

    public void addSale(Sale sale) {
        this.sales.add(sale);
    }

    public Double totalSales() {
        Double total = 0.0;
        for (Sale sale: this.sales) {
            total += sale.saleCost;
        }
        return total;
    }

    public Double totalCost() {
        Double total = 0.0;
        for (Sale sale: this.sales) {
            total += sale.purchaseCost;
        }
        return total;
    }
    public Double totalProfit() {
        return this.totalSales() - this.totalCost();
    }

    public Double commission(int rate) {
        return this.totalProfit() * rate / 100;
    }

    public Double productCareRatio() {
        int count = 0;
        for (Sale sale: this.sales) {
            if(sale.productCare) { count++; }
        }
        return Double.valueOf(count) / Double.valueOf(this.sales.size());
    }
}
