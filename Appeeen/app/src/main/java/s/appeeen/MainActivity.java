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
        LinearLayout mainLinearView = (LinearLayout) findViewById(R.id.mainLinear);
        for(int i = 0; i < 50; i++){
            TextView textView = new TextView(this);
            textView.setText(Integer.toString(i));
            mainLinearView.addView(textView);
        }
    }
}
