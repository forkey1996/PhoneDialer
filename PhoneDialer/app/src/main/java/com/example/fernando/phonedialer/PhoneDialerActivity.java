package com.example.fernando.phonedialer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.health.PackageHealthStats;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PhoneDialerActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CALL_PHONE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_dialer);
        setupButtonListeners();
        
    }

    class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            EditText phoneNumberEditText = (EditText)findViewById(R.id.editTextPhoneNumber);

            if (view instanceof ImageButton) {
                ImageButton imgButton = (ImageButton)view;
                if (imgButton.getId() == R.id.imageButtonCall) {
                    if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                                PhoneDialerActivity.this,
                                new String[]{Manifest.permission.CALL_PHONE},
                                PERMISSION_REQUEST_CALL_PHONE
                                );
                    }

                    if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                        startActivity(intent);
                    }
                }
                else
                {

                    if (!phoneNumberEditText.getText().toString().isEmpty())
                        phoneNumberEditText.setText(phoneNumberEditText.getText().toString().substring(0, phoneNumberEditText.getText().toString().length() - 1));
                }
            }
            else if (view instanceof Button) {
                String pressedButtonText = ((Button)view).getText().toString();
                phoneNumberEditText.setText(String.format("%s%s", phoneNumberEditText.getText().toString(), pressedButtonText));
            }
        }
    }

    private void setupButtonListeners() {
        Button button0 = (Button)findViewById(R.id.button0);
        Button button1 = (Button)findViewById(R.id.button1);
        Button button2 = (Button)findViewById(R.id.button2);
        Button button3 = (Button)findViewById(R.id.button3);
        Button button4 = (Button)findViewById(R.id.button4);
        Button button5 = (Button)findViewById(R.id.button5);
        Button button6 = (Button)findViewById(R.id.button6);
        Button button7 = (Button)findViewById(R.id.button7);
        Button button8 = (Button)findViewById(R.id.button8);
        Button button9 = (Button)findViewById(R.id.button9);
        Button buttonStar = (Button)findViewById(R.id.buttonStar);
        Button buttonHash = (Button)findViewById(R.id.buttonHash);
        ImageButton buttonCall = (ImageButton)findViewById(R.id.imageButtonCall);
        ImageButton buttonDelete = (ImageButton)findViewById(R.id.imageButtonDelete);

        ButtonClickListener clickListener = new ButtonClickListener();
        button0.setOnClickListener(clickListener);
        button1.setOnClickListener(clickListener);
        button2.setOnClickListener(clickListener);
        button3.setOnClickListener(clickListener);
        button4.setOnClickListener(clickListener);
        button5.setOnClickListener(clickListener);
        button6.setOnClickListener(clickListener);
        button7.setOnClickListener(clickListener);
        button8.setOnClickListener(clickListener);
        button9.setOnClickListener(clickListener);
        buttonStar.setOnClickListener(clickListener);
        buttonHash.setOnClickListener(clickListener);
        buttonCall.setOnClickListener(clickListener);
        buttonDelete.setOnClickListener(clickListener);
    }
}
