package com.aquar.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Login.userResPOJO;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.edit_text_username)
    EditText edit_text_username;
    @BindView(R.id.edit_text_phone)
    EditText edit_text_phone;
    @BindView(R.id.edit_text_jopTitle)
    EditText edit_text_jopTitle;
    @BindView(R.id.edit_text_Email)
    EditText edit_text_Email;
    @BindView(R.id.edit_text_password)
    EditText edit_text_password;
//    userResPOJO resPOJO=new userResPOJO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        AndroidNetworking.initialize(this);

    }

    @OnClick(R.id.registration_BT)
    public void onRegister() {
        String name = edit_text_username.getText().toString().trim();
        String phone = edit_text_phone.getText().toString().trim();
        String email = edit_text_Email.getText().toString().trim();
        String password = edit_text_password.getText().toString().trim();
        String jobTitle = edit_text_jopTitle.getText().toString().trim();
        ValidationRegisterData(name, phone, email, password, jobTitle);


    }

    private void ValidationRegisterData(String name, String phone, String email, String password, String jobTitle) {
        if (TextUtils.isEmpty(name)) {
            edit_text_username.setError("Required");
        }
        if (TextUtils.isEmpty(password)) {
            edit_text_password.setError("Required");
        }
        if (TextUtils.isEmpty(phone)) {
            edit_text_phone.setError("Required");
        }
        if (TextUtils.isEmpty(email)) {
            edit_text_Email.setError("Required");
        }
        if (TextUtils.isEmpty(jobTitle)) {
            edit_text_jopTitle.setError("Required");
        } else {
            onRegisterData(name, password, phone, email, jobTitle);
//            Toast.makeText(getApplicationContext(), "hh", Toast.LENGTH_SHORT).show();
        }
    }

    private void onRegisterData(String name, String password, String phone, String email, String jobTitle) {
        JSONObject object = new JSONObject();
        try {
            object.put("user_name", name);
            object.put("phone", phone);
            object.put("email", email);
            object.put("password", password);
            object.put("job_title", jobTitle);

        } catch (JSONException e) {
            e.getStackTrace();
        }
        AndroidNetworking.post(ConstantsUrl.Registration)
                .addJSONObjectBody(object)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        userResPOJO resPOJO = gson.fromJson(response.toString(), userResPOJO.class);

                        Log.d("RegisterResponse", response.toString());
                        String userOBJSTR = gson.toJson(resPOJO.getUserInfo());
//
                        Log.d("RegisterResponse", resPOJO.getUserInfo().getToken()+"");

                        mySharedPreference.setUserToken(resPOJO.getUserInfo().getToken());
                        mySharedPreference.setUserOBJ(userOBJSTR);
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));

                    }

                    @Override
                    public void onError(ANError anError) {
//                        Log.d("RegisterError", resPOJO.getUserInfo().getToken());
                        Log.d("RegisterError", anError.getErrorBody() + "");
                        Log.d("RegisterError", anError.getErrorCode() + "");
//                        Log.d("RegisterError", anError.getResponse().toString());

                    }
                });
    }

}