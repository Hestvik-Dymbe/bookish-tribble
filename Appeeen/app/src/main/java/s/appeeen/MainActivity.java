package s.appeeen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i = 0; i < 50; i++){

            addViewToMainActivity(Integer.toString(i));

        }
    }

    private void addViewToMainActivity(String text){

        LinearLayout mainLinearView = (LinearLayout) findViewById(R.id.mainLinear);
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(24,24,0,24);
        textView.setTextSize(24);
        mainLinearView.addView(textView);
    }
}
