package joseph.com.salestracker;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class BreakdownActivity extends AppCompatActivity {


    private SalesCollection salesCollection = new SalesCollection();

    private CustomCalendarView calendarView;

    //Initialize calendar with date
    private Calendar currentCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakdown);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);
        this.currentCalendar = Calendar.getInstance(Locale.getDefault());

        try {
            this.salesCollection = this.salesCollection.load(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//Show Monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

//Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

//call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

//Handling custom calendar events
        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                SalesCollection collection = new SalesCollection();
                for (Sale sale: salesCollection.sales
                        ) {
                    String data = df.format(sale.date);
                    String currentDate = df.format(date);
                    if(data.equals(currentDate)) {
                        collection.addSale(sale);
                    }

                }
                ;
                final TextView totalSales = (TextView) findViewById(R.id.textView8);

                final TextView totalProfit = (TextView) findViewById(R.id.textView10);

                final TextView commission = (TextView) findViewById(R.id.textView12);

                totalSales.setText(collection.totalSales().toString());
                totalProfit.setText(collection.totalProfit().toString());
                commission.setText(collection.commission(10).toString());

                highlightDaysWithSales();
            }

            @Override
            public void onMonthChanged(Date date) {
                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
                Toast.makeText(getApplicationContext(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        highlightDaysWithSales();

//        calendar.setDate(salesCollection.sales.get(0).date.getTime());
//
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
//                Toast.makeText(getApplicationContext(), String.valueOf(salesCollection.sales.get(0).date), Toast.LENGTH_LONG).show();
//            };
//        });
    }

    private void highlightDaysWithSales() {
        ArrayList<String> days = getDates();

        List<DayDecorator> decorators = new ArrayList<>();
        decorators.add(new ColorDecorator(days));
        calendarView.setDecorators(decorators);
        calendarView.refreshCalendar(currentCalendar);
    }

    @NonNull
    private ArrayList<String> getDates() {
        ArrayList<String> days = new ArrayList<String>();

        for (Sale sale: salesCollection.sales
             ) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String data = df.format(sale.date);
            days.add(data);
        }
        ;
        return days;
    }

    private class ColorDecorator implements DayDecorator {

        public ArrayList<String> days;
        public ColorDecorator(ArrayList<String> days) {
            this.days = days;
        }

        @Override
        public void decorate(DayView cell) {
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            if (days.contains(df.format(cell.getDate()))) {
                int color = Color.argb(255, 144, 245, 0);
                cell.setBackgroundColor(color);
            }

        }
    }
}
