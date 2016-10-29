package com.saha.aesencryptiondecryption;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.saha.aeslibrary.AES;

import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity
{
    private EditText original, encrypted;
    private Button btn;
    boolean original_true = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String password = "password";
        AES.setKey(password);

        original = (EditText) findViewById(R.id.original);
        encrypted = (EditText) findViewById(R.id.encrypted);
        btn = (Button) findViewById(R.id.button);

        original.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                original_true = true;
            }
        });

        encrypted.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                original_true = false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (original_true)
                {
                    try
                    {
                        String temp = original.getText().toString();
                        encrypted.setText(AES.encrypt(temp));
                    }
                    catch (GeneralSecurityException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try
                    {
                        String temp = encrypted.getText().toString();
                        original.setText(AES.decrypt(temp));
                    }
                    catch (GeneralSecurityException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
