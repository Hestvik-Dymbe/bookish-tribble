package sander.appeen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {


    private static final String TAG = "AddTask";
    private TextView mDisplayDate;
    private TextView mDisplayTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private Button addButton;
    private EditText summaryView;
    private EditText descriptionView;
    private EditText stakeAmountView;

    private Integer yearTime;
    private Integer monthTime;
    private Integer dayTime;
    private Integer hourTime;
    private Integer minuteTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        summaryView = (EditText) findViewById(R.id.summaryEditText);
        descriptionView = (EditText) findViewById(R.id.descriptionEditView);
        stakeAmountView = (EditText) findViewById(R.id.stakeAmountEditView);

        mDisplayDate = (TextView) findViewById(R.id.dateView);
        mDisplayTime = (TextView) findViewById(R.id.timeView);
        addButton = (Button) findViewById(R.id.add_button);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        AddTask.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                monthTime = month;
                dayTime = day;
                yearTime = year;

                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };



        //TODO Shows 10:7 instead of 10:07 for example
        mDisplayTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AddTask.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mDisplayTime.setText( selectedHour + ":" + selectedMinute);
                        hourTime = selectedHour;
                        minuteTime = selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

    addButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view){
            buttonPress();

            }
        });
    }

private void buttonPress(){
    String summary = summaryView.getText().toString();
    String description = descriptionView.getText().toString();
    Integer stakeAmount;
    if(stakeAmountView.getText().toString().isEmpty()){
        stakeAmount = getApplicationContext().getResources().getInteger(R.integer.standard_stake);
    }else{
        stakeAmount = Integer.valueOf(stakeAmountView.getText().toString());
    }

    Calendar cal = Calendar.getInstance();
    if(yearTime == null || monthTime == null || dayTime == null) {
        Toast.makeText(getApplicationContext(), getText(R.string.toast_if_date_not_set), Toast.LENGTH_LONG).show();
        return;
    }
    if(hourTime == null || minuteTime == null){
        hourTime = getApplicationContext().getResources().getInteger(R.integer.standard_hour_for_task);
        minuteTime = getApplicationContext().getResources().getInteger(R.integer.standard_minute_for_task);
    }
    cal.set(yearTime, monthTime, dayTime, hourTime, minuteTime);

    Log.v(TAG, "UNIX time: " + String.valueOf(cal.getTime().getTime()));
    Log.v(TAG, "summary: " + summary);
    Log.v(TAG, "description: " + description);
    Log.v(TAG, "stakeAmount: " + stakeAmount.toString());


    //TODO: Add code
    finish();

}
}
