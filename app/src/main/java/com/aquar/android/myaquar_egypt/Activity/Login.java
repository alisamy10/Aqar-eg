package com.aquar.android.myaquar_egypt.Activity;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.android.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.android.myaquar_egypt.Model.Login.userResPOJO;
import com.aquar.android.myaquar_egypt.Model.socialLogin.socialLoginPOJO;
import com.aquar.android.myaquar_egypt.R;
import com.aquar.android.myaquar_egypt.Utils.ConstantsUrl;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;

import static com.aquar.android.myaquar_egypt.Utils.ConstantsUrl.userDataBundleKey;

public class Login extends AppCompatActivity {
    @BindView(R.id.enter_email)
    EditText enter_email;
    @BindView(R.id.enter_pass)
    EditText enter_pass;

    //socialData
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 123;

    //registration widgets
    EditText edit_text_firstName, edit_text_phone, edit_text_email, edit_text_password, edit_text_jobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


    }

    @OnClick(R.id.login)
    public void onLoginPress() {
        String email = enter_email.getText().toString().trim();
        String password = enter_pass.getText().toString().trim();
        ValidationData(email, password);
    }

    private void ValidationData(String mobile, String password) {
        if (TextUtils.isEmpty(mobile)) {
            enter_email.setError("Required");
        }
        if (TextUtils.isEmpty(password)) {
            enter_pass.setError("Required");
        } else {
            onLogin(mobile, password);
        }
    }

    private void onLogin(String mobile, String password) {
        JSONObject object = new JSONObject();
        try {
            object.put("phone", mobile);
            object.put("password", password);
        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.verifyLogin)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        String userOBJSTR = gson.toJson(response.toString(), userResPOJO.class);
                        Log.d("testest", response.toString());
                        mySharedPreference.setUserOBJ(userOBJSTR);
                        startActivity(new Intent(Login.this, MainActivity.class));

                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

    public void regist(View v) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View view = layoutInflater.inflate(R.layout.rejest_dialog, null);

        final AlertDialog alertD = new AlertDialog.Builder(this).create();

//        Button exit = view.findViewById(R.id.exit_button);
        Button registration_BT = view.findViewById(R.id.registration_BT);
        edit_text_firstName = view.findViewById(R.id.edit_text_username);
        edit_text_phone = view.findViewById(R.id.edit_text_phone);
        edit_text_jobTitle = view.findViewById(R.id.edit_text_jopTitle);
        edit_text_email = view.findViewById(R.id.edit_text_Email);
        edit_text_password = view.findViewById(R.id.edit_text_password);

        registration_BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit_text_firstName.getText().toString().trim();
                String phone = edit_text_phone.getText().toString().trim();
                String email = edit_text_email.getText().toString().trim();
                String password = edit_text_password.getText().toString().trim();
                String jobTitle = edit_text_jobTitle.getText().toString().trim();

                ValidationRegisterData(name, phone, email, password, jobTitle);
            }
        });
//        exit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertD.cancel();
//            }
//        });
        alertD.setCancelable(true);
        alertD.setView(view);
        alertD.show();
    }

    private void ValidationRegisterData(String name, String phone, String email, String password, String jobTitle) {
        if (TextUtils.isEmpty(name)) {
            edit_text_firstName.setError("Required");
        }
        if (TextUtils.isEmpty(password)) {
            edit_text_password.setError("Required");
        }
        if (TextUtils.isEmpty(phone)) {
            edit_text_phone.setError("Required");
        }
        if (TextUtils.isEmpty(email)) {
            edit_text_email.setError("Required");
        }
        if (TextUtils.isEmpty(jobTitle)) {
            edit_text_jobTitle.setError("Required");
        } else {
            onRegisterData(name, password, phone, email, jobTitle);
        }
    }

    private void onRegisterData(String name, String password, String phone, String email, String jobTitle) {
        JSONObject object = new JSONObject();
        try {
            object.put("name", name);
            object.put("phone", phone);
            object.put("email", email);
            object.put("password", password);
            object.put("job_title", jobTitle);
        } catch (JSONException e) {
            e.getStackTrace();
        }
    }

    public void onFacebookBT() {
        LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList(
                "public_profile", "email"));
    }

    public void onGoogleBT() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void skip(View view) {
        startActivity(new Intent(Login.this, MainActivity.class));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            //Google response
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

        } else {
            //facebook response
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }


    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.d("googleData", account.getEmail() + "," + account.getDisplayName());

            sendDataToRegister(account.getDisplayName(), account.getEmail());


        } catch (ApiException e) {
            Log.d("googleData", "signInResult:failed code=" + e.getStatusCode());

            Toast.makeText(this, "Failed to do Sign In", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendDataToRegister(String name, String mail) {
        socialLoginPOJO object = new socialLoginPOJO(name, mail);

//        Intent toregister = new Intent(Login.this, RegistrationActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(userDataBundleKey, object);
//        toregister.putExtras(mBundle);
//        startActivity(toregister);
    }

    private void facebookToken() {



        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        GraphRequest request = GraphRequest.newMeRequest(
                                loginResult.getAccessToken(),
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(JSONObject object, GraphResponse response) {
                                        // Application code
                                        Log.d("facebookData", object.toString());
                                        try {
                                            String name = object.getString("name");

                                            Log.d("facebookData", name);

                                            sendDataToRegister(name, "");


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }


                                    }
                                });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,link");
                        request.setParameters(parameters);
                        request.executeAsync();

                        LoginManager.getInstance().logOut();

                    }

                    @Override
                    public void onCancel() {
                        Log.d("FacebookData", "facebook cancelled");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("FacebookData", error.toString());
                        Toast.makeText(Login.this, "Failed to do Sign In", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
