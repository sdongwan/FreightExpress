package com.highspace.hs.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.highspace.hs.R;

/**
 * Created by wenyue on 2016/10/10.
 */

public class StartAddressActivity extends Activity {
    private Button saveAddress;
    private EditText edName;
    private EditText edNumber;
    private EditText edAddress;
    private EditText edLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_address_activity);
        initView();
        saveAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name", edName.getText().toString());
                bundle.putString("number", edNumber.getText().toString());
                bundle.putString("address", edAddress.getText().toString());
                bundle.putString("location", edLocation.getText().toString());
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private void initView() {
        saveAddress = (Button) findViewById(R.id.save_address);
        edName = (EditText) findViewById(R.id.edit_name);
        edNumber = (EditText) findViewById(R.id.edit_number);
        edAddress = (EditText) findViewById(R.id.edit_address);
        edLocation = (EditText) findViewById(R.id.edit_location);
    }
}
