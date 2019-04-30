package com.sean.chien.shoppinglist;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity {
    private EditText mconfirmET;


    private LinearLayout mLinearLayout;
    public static final int ITEMS_REQUEST = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayout = findViewById(R.id.linear_container);
    }

    public void goToList(View view) {
        Intent intent = new Intent(this, ItemListActivity.class);
        startActivityForResult(intent, ITEMS_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ITEMS_REQUEST) {
            if (resultCode == RESULT_OK) {
                //data
                ArrayList<String> items = data.getExtras().getStringArrayList(ItemListActivity.ITEMS_EXTRA);
                for (int i = 0; i < items.size(); i++) {
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    );
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(params);
                    tv.setGravity(Gravity.CENTER);
                    tv.setText(items.get(i));
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
                    mLinearLayout.addView(tv, 0);
                }
            }
        }
    }


    public void Answer(View view) {
        String confirm = mconfirmET.getText().toString();

        // 2. parse the string into a Uri object with geolocation search question
        // - latitude 緯度  , longitude 經度
        Uri locUri = Uri.parse("geo:0,0?q=" + confirm);
        Intent intent = new Intent(Intent.ACTION_VIEW, locUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

