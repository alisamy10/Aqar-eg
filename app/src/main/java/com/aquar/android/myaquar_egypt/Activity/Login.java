package com.aquar.android.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.aquar.android.myaquar_egypt.R;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);



    }

    public void regist(View v) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.rejest_dialog, null);

        final AlertDialog alertD = new AlertDialog.Builder(this).create();

        Button exit = view.findViewById(R.id.exit_button);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertD.cancel();
            }
        });
        alertD.setCancelable(true);
        alertD.setView(view);
        alertD.show();

    }

    public void skip(View view) {
        startActivity(new Intent(Login.this, MainActivity.class));

    }
}
