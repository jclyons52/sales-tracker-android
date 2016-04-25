package joseph.com.testapp;

import android.content.Context;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by administrator on 24/04/2016.
 */
public class SalesCollection {

    private String FILENAME = "hello_file";

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

    public void save(Context context) throws IOException {
        FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
        ObjectOutputStream os = new ObjectOutputStream(fos);
        os.writeObject(this.sales);
        os.close();
        fos.close();
    }

    public SalesCollection load(Context context) throws IOException, ClassNotFoundException {
        FileInputStream fis = context.openFileInput(FILENAME);
        ObjectInputStream is = new ObjectInputStream(fis);
        ArrayList<Sale> sales = (ArrayList<Sale>) is.readObject();
        SalesCollection salesCollection = new SalesCollection();
        salesCollection.sales = sales;
        is.close();
        fis.close();

        return salesCollection;
    }
}
