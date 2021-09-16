package edu.neu.mad_sea.xinyizhu.lesson1_1;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;
    private AppCompatButton zeroButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
        zeroButton = (AppCompatButton) findViewById(R.id.button_zero);
        Log.d("MainActivity", "Hello World");
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void countUp(View view) {
        ++mCount;
        view.setBackgroundColor(Color.YELLOW);
        if (mShowCount != null) {
            mShowCount.setText(Integer.toString(mCount));
            if (mCount > 0) zeroButton.setBackgroundColor(Color.RED);
        }
    }

    public void resetZero(View view) {
        mCount = 0;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
        zeroButton.setBackgroundColor(Color.rgb(170, 170, 170));
    }

}