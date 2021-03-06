package infinite.pb;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.List;

/*This activity is responsible to display all the urls along with their data from Database relation
* in a tabular form.
* */
public class ViewRecords extends AppCompatActivity {

    UrlsDirectory u_dir;
    String TAG="View Records";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        init();
    }

    //to be removed
    public void addDummyRecos()
    {
        u_dir = new UrlsDirectory(this);
        u_dir.fillUrlsBag();
        u_dir.dropUrl("facebook.com");
        u_dir.dropUrl("gstatic.com");
        u_dir.dropUrl("wohahah.com");
        u_dir.dropUrl("matracker.com");
        u_dir.dropUrl("facebook.com");
        u_dir.dropUrl("facebook.com");
        Log.d("Size progress~~~:",""+u_dir.getData().size());

    }
    public void init() {
     //   addDummyRecos();

        DatabaseHandler dh= DatabaseHandler.getInstance(getApplicationContext());
        List<UrlData> data= dh.getAllUrlsData();
        TableLayout stk = (TableLayout) findViewById(R.id.displayLinear);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Sl.No ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" URL ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Count ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Status ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);

        for (int i = 0; i < data.size(); i++) {
            Log.d(TAG,"Rendering records in table");
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText("" + (i + 1));
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(data.get(i).getURL());
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText("" + data.get(i).getCount());

            t3v.setTextColor(Color.BLACK);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText((data.get(i).getStatus()==0)?"Allowed":"Blocked");
            t4v.setTextColor(Color.BLACK);

            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow);

        }
    }
}
