package com.aquar.myaquar_egypt.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Login.userResPOJO;
import com.aquar.myaquar_egypt.Model.socialLogin.socialLoginPOJO;

import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import butterknife.BindAnim;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;

public class Login extends AppCompatActivity {

    @BindView(R.id.enter_email)
    EditText enter_email;
    @BindView(R.id.enter_pass)
    EditText enter_pass;
    @BindView(R.id.loginFB)
    LinearLayout loginFB;

    //socialData
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private int RC_SIGN_IN = 123;

    //dialog
    AlertDialog dialog1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView(R.layout.login);
        facebookToken();
        googleToken();
        ButterKnife.bind(this);

        dialog1 = new SpotsDialog.Builder().setContext(Login.this).setTheme(R.style.Custom).build();
        dialog1.setMessage("Please wait.....");


    }

    @OnClick(R.id.login)
    public void onLoginPress() {
        String email = enter_email.getText().toString().trim();
        String password = enter_pass.getText().toString().trim();
        ValidationData(email, password);
    }

    private void ValidationData(String email, String password) {
        if (TextUtils.isEmpty(email)) {
            enter_email.setError("Required");
        }
        if (TextUtils.isEmpty(password)) {
            enter_pass.setError("Required");
        } else {
            onLogin(email, password);
        }
    }

    private void onLogin(String email, String password) {
        dialog1.show();
        JSONObject object = new JSONObject();
        try {
            object.put("email", email);
            object.put("password", password);
        } catch (JSONException e) {
            e.getStackTrace();
        }

        AndroidNetworking.post(ConstantsUrl.Login)
                .addJSONObjectBody(object)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        userResPOJO resPOJO = gson.fromJson(response.toString(), userResPOJO.class);
                        String userOBJSTR = gson.toJson(resPOJO.getUserInfo());
                        Log.d("testest", response.toString());
                        mySharedPreference.setUserOBJ(userOBJSTR);
                        startActivity(new Intent(Login.this, MainActivity.class));
                        dialog1.dismiss();
                    }

                    @Override
                    public void onError(ANError anError) {
                        dialog1.dismiss();
                        myUtils.handleError(Login.this, anError.getErrorBody(), anError.getErrorCode());

                    }
                });
    }

    public void regist(View v) {

        startActivity(new Intent(Login.this, RegisterActivity.class));

    }

    @OnClick(R.id.loginFB)
    public void onFacebookBT() {
        LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList(
                "public_profile", "email"));

    }

    @OnClick(R.id.logingoggleplus)
    public void onGoogleBT() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @OnClick(R.id.skip)
    public void skip() {
        startActivity(new Intent(Login.this, MainActivity.class));
        finish();
//        mySharedPreference.setUserOBJ("");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            //Google response
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            int statusCode = result.getStatus().getStatusCode();

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

        Intent toregister = new Intent(Login.this, RegisterActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable(ConstantsUrl.userDataBundleKey, object);
        toregister.putExtras(mBundle);
        startActivity(toregister);
        finish();
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

    private void googleToken() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
