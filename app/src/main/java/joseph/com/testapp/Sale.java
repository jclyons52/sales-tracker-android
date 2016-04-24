package joseph.com.testapp;

import java.util.Date;

/**
 * Created by administrator on 24/04/2016.
 */
public class Sale {

    public Double purchaseCost;

    public Double saleCost;

    public Boolean productCare;

    public Date date;

    public Sale(Double purchaseCost, Double saleCost, Boolean productCare) {
        this.purchaseCost = purchaseCost;
        this.saleCost = saleCost;
        this.productCare = productCare;
        this.date = new Date();
    }

    public Double profit() {
        return this.saleCost - this.purchaseCost;
    }
}
