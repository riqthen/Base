package com.riq.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

/**
 * 给按钮设置 android:background="@null" 属性，则按钮没有背景，即Button变成TextView样式，ImageButton变成ImageView样式
 * android:scaleType="fitCenter" 属性可以设置图片的位置，这是ImageView和ImageButton的属性
 * 获取系统年月日
 * calendar.get(Calendar.YEAR)
 * calendar.get(Calendar.MONTH)
 * calendar.get(Calendar.DAY_OF_MONTH)
 */
public class Widget extends AppCompatActivity {
    EditText editText;
    CheckBox checkBox;
    DatePicker datePicker;
    TimePicker timePicker;
    RadioGroup radioGroup;
    RadioButton radioButton;
    AutoCompleteTextView autoCompleteTextView;
    MultiAutoCompleteTextView multiAutoCompleteTextView;
    ImageSwitcher imageSwitcher;
    ProgressBar progressBar;
    SeekBar seekBar;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget);

        // ** EditText
        editText = (EditText) findViewById(R.id.et);
        editText.addTextChangedListener(textWatcher);
        editText.setOnEditorActionListener(onEditorActionListener);

        // ** CheckBox
        checkBox = (CheckBox) findViewById(R.id.cb_music);
        checkBox.setChecked(true);

        // ** DatePicker
        datePicker = (DatePicker) findViewById(R.id.dp);
        datePicker.init(2000, 1, 1, dateChangedListener);

        // ******* TimePicker
        timePicker = (TimePicker) findViewById(R.id.tp);
        timePicker.setIs24HourView(true);
        timePicker.setCurrentHour(18);
        timePicker.setCurrentMinute(00);
        timePicker.setOnTimeChangedListener(timeChangedListener);
//         timePicker.setHour(17);
//         timePicker.setMinute(59);

        // *** RadioGroup & RadioButton
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(checkedChangeListener);
        radioGroup.check(R.id.rb2);
        radioButton = (RadioButton) findViewById(R.id.rb1);
        radioButton.setOnCheckedChangeListener(rbCheckedChangeListener);

        // **** AutoCompleteTextView  需要item布局
        autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.actv);
        String[] data = new String[]{"aaa", "aab", "abb", "bbb", "ccc"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_actv, R.id.tv, data);    //此处一定要TextView的R.id.tv
        autoCompleteTextView.setAdapter(adapter);

        // **** MultiAutoCompleteTextView
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.mactv);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.item_actv, R.id.tv, data);
        multiAutoCompleteTextView.setAdapter(adapter2);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer()); //设置间隔逗号


        // ImageSwitcher 没用
     /*   imageSwitcher = (ImageSwitcher) findViewById(R.id.is);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView iv=new ImageView(Widget.this);
                return iv;
            }
        });
        imageSwitcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setImageResource(R.mipmap.ic_launcher);
            }
        });*/

        // *** ProgressBar
        progressBar = (ProgressBar) findViewById(R.id.pb);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i++ < 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("========", i + "");
                    progressBar.setProgress(i); // ProgressBar做了UI的处理，所以直接可以再子线程中修改UI
                    runOnUiThread(new Runnable() {  //此处有了休眠，则会缓慢变化i的值，更新了UI。
                        @Override
                        public void run() {
                            autoCompleteTextView.setText(progressBar.getProgress() + "/" + progressBar.getMax());
                        }
                    });
                }
            }
        }).start();

        // **** SeekBar
        seekBar = (SeekBar) findViewById(R.id.sb);
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        seekBar.setMax(200);
        seekBar.setProgress(50);

        // ***** Spinner
        String[] data3 = {"a", "b", "c", "d"};
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.car));
        spinner.setAdapter(adapter3);
        spinner.setOnItemSelectedListener(onItemSelectedListener);


    }

    //EditText监听
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    //按下回车键监听
    TextView.OnEditorActionListener onEditorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            return false;
        }
    };

    //DatePicker监听
    DatePicker.OnDateChangedListener dateChangedListener = new DatePicker.OnDateChangedListener() {
        @Override
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        }
    };

    //TimePicker监听
    TimePicker.OnTimeChangedListener timeChangedListener = new TimePicker.OnTimeChangedListener() {
        @Override
        public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

        }
    };

    //RadioGroup监听
    RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

        }
    };

    //RadioButton监听
    CompoundButton.OnCheckedChangeListener rbCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        }
    };

    //SeekBar监听
    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // 进度改变时
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // 开始拖动(碰上去的时候)
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // 拖动完毕
        }
    };

    //Spinner选择条目监听
    AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            //第一个会默认被选择，所以一进入就会响应
            String s = getResources().getStringArray(R.array.car)[position];
            Toast.makeText(Widget.this, s, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {  //该方法不会被调用，因为总有一个被选择显示出来
            Toast.makeText(Widget.this, "从没有触发过", Toast.LENGTH_SHORT).show();
        }
    };

}
