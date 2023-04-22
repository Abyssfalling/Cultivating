package com.example.cultivating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView currentDate, currentDay;
    public EditText[] Text = new EditText[12];//输入框
    public TextView[] View = new TextView[12];//展示框
    public TextView[] Top = new TextView[12];
    public CheckBox[] Day = new CheckBox[8];
    public SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentDate = findViewById(R.id.current_date);
        currentDay = findViewById(R.id.current_day);
        // 获取当前日期和星期几
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
        String date = dateFormat.format(calendar.getTime());
        String day = dayFormat.format(calendar.getTime());
        // 设置文本框的文本为当前日期和星期几
        currentDate.setText("Current date: " + date);
        currentDay.setText("Current day: " + day);

        Day[1] = findViewById(R.id.Mon);
        Day[2] = findViewById(R.id.Tue);
        Day[3] = findViewById(R.id.Wed);
        Day[4] = findViewById(R.id.Thu);
        Day[5] = findViewById(R.id.Fri);
        Day[6] = findViewById(R.id.Sat);
        Day[7] = findViewById(R.id.Sun);


        Text[0] = findViewById(R.id.Boss_edit);
        View[0] = findViewById(R.id.Boss_now);
        Top[0] = findViewById(R.id.Boss_top);

        Text[1] = findViewById(R.id.find_edit);
        View[1] = findViewById(R.id.find_now);
        Top[1] = findViewById(R.id.find_top);

        Text[2] = findViewById(R.id.Drop1_edit);
        View[2] = findViewById(R.id.Drop1_now);
        Top[2] = findViewById(R.id.Drop1_top);

        Text[3] = findViewById(R.id.Drop2_edit);
        View[3] = findViewById(R.id.Drop2_now);
        Top[3] = findViewById(R.id.Drop2_top);

        Text[4] = findViewById(R.id.Drop3_edit);
        View[4] = findViewById(R.id.Drop3_now);
        Top[4] = findViewById(R.id.Drop3_top);

        Text[5] = findViewById(R.id.Talent1_edit);
        View[5] = findViewById(R.id.Talent1_now);
        Top[5] = findViewById(R.id.Talent1_top);

        Text[6] = findViewById(R.id.Talent2_edit);
        View[6] = findViewById(R.id.Talent2_now);
        Top[6] = findViewById(R.id.Talent2_top);

        Text[7] = findViewById(R.id.Talent3_edit);
        View[7] = findViewById(R.id.Talent3_now);
        Top[7] = findViewById(R.id.Talent3_top);

        Text[8] = findViewById(R.id.Exp1_edit);
        View[8] = findViewById(R.id.Exp1_now);
        Top[8] = findViewById(R.id.Exp1_top);

        Text[9] = findViewById(R.id.Exp2_edit);
        View[9] = findViewById(R.id.Exp2_now);
        Top[9] = findViewById(R.id.Exp2_top);

        Text[10] = findViewById(R.id.Exp3_edit);
        View[10] = findViewById(R.id.Exp3_now);
        Top[10] = findViewById(R.id.Exp3_top);

        Text[11] = findViewById(R.id.Mora_edit);
        View[11] = findViewById(R.id.Mora_now);
        Top[11] = findViewById(R.id.Mora_top);

        sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        //启动时读取上一步保存
        for (int i = 1; i <= 7; i++) {
            String read_check = "checkbox" + i;
            boolean isChecked = sharedPreferences.getBoolean(read_check, false);
            Day[i].setChecked(isChecked);
        }

        for (int i = 0; i < 12; i++) {
            String read_now = "now" + i;
            String text = sharedPreferences.getString(read_now, "0");
            View[i].setText(text);
        }
    }

    public void save(View view) {
        boolean[] isChecked = new boolean[8];
        for (int i = 1; i <= 7; i++) {
            isChecked[i] = Day[i].isChecked();
        }

        for(int i=0;i<12;i++)
        {
            String editText = Text[i].getText().toString();
            if(!editText.equals(""))
            {
                View[i].setText(editText);
            }
        }

        int add = 0;
        for(int i=4;i>=2;i--)
        {
            int top = Integer.parseInt(Top[i].getText().toString());
            int now = Integer.parseInt(View[i].getText().toString()) + add;

            if(now > top && i != 2)
            {
                int temp = now - top;
                now = top + temp%3;
                add = temp/3;
            }
            String new_text = "" + now;
            View[i].setText(new_text);
        }

        add = 0;
        for(int i=7;i>=5;i--)
        {
            int top = Integer.parseInt(Top[i].getText().toString());
            int now = Integer.parseInt(View[i].getText().toString()) + add;

            if(now > top && i != 5)
            {
                int temp = now - top;
                now = top + temp%3;
                add = temp/3;
            }
            String new_text = "" + now;
            View[i].setText(new_text);
        }

        add = 0;
        for(int i=10;i>=8;i--)
        {
            int top = Integer.parseInt(Top[i].getText().toString());
            int now = Integer.parseInt(View[i].getText().toString()) + add;

            if(now > top && i == 10)
            {
                int temp = now - top;
                now = top + temp%5;
                add = temp/5;
            }

            else if(now > top && i == 9)
            {
                int temp = now - top;
                now = top + temp%4;
                add = temp/4;
            }
            String new_text = "" + now;
            View[i].setText(new_text);
        }

        // 将文本保存到SharedPreferences中
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 1; i <= 7; i++) {
            String put_str = "checkbox" + i;
            editor.putBoolean(put_str, isChecked[i]);
        }
        for (int i = 0; i < 12; i++) {
            String put_str = "now" + i;
            String new_text = View[i].getText().toString();
            editor.putString(put_str, new_text);
        }
        editor.apply();
    }

    public void exit(View view)
    {
        finish();
    }

    public void clear(View view)
    {
        // 获取 SharedPreferences.Editor 实例
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // 使用 clear() 方法清除所有数据
        editor.clear();

        // 提交修改
        editor.apply();

        for(int i=0;i<12;i++)
        {
            View[i].setText("0");
        }

    }
}
