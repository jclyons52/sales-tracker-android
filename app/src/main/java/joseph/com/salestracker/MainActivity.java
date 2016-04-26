package joseph.com.salestracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.RadioButton;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private SalesCollection salesCollection = new SalesCollection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setText(View view) throws IOException {

        final EditText purchaseCost = (EditText) findViewById(R.id.purchaseCost);

        final EditText saleCost = (EditText) findViewById(R.id.saleCost);

        final RadioButton productCare = (RadioButton) findViewById(R.id.productCare);

        Double pCost = Double.parseDouble(purchaseCost.getText().toString());
        Double sCost = Double.parseDouble(saleCost.getText().toString());
        Boolean pCare = productCare.isChecked();
        Sale sale = new Sale(pCost, sCost, pCare);
        salesCollection.addSale(sale);
        salesCollection.save(this);
        purchaseCost.setText("");
        saleCost.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            this.salesCollection = this.salesCollection.load(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            this.salesCollection.save(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewStats(View view) {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }
}
