package com.aquar.myaquar_egypt.Fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Activity.Login;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.Model.Login.userResPOJO;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import dmax.dialog.SpotsDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile_fragment extends Fragment {

    private Fragment fragment;


    @BindView(R.id.edit_profile_photo)
    CircleImageView chooseImage;
    @BindView(R.id.profile_photo)
    CircleImageView profile_photo;
    @BindView(R.id.user_name)
    TextView user_name;
    @BindView(R.id.jobTitle)
    TextView job_title;
    @BindView(R.id.job_title)
    TextView job_titleBar;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.mobile)
    TextView mobile;


    @BindView(R.id.jobTitle_linear)
    LinearLayout jobTitle_linear;
    @BindView(R.id.username_linear)
    LinearLayout username_linear;
    @BindView(R.id.email_linear)
    LinearLayout email_linear;
    @BindView(R.id.mobile_linear)
    LinearLayout mobile_linear;

    private UserInfo userPOJO;
    private Dialog dialog;
    //dialog
    private AlertDialog alertDialog;


    public Profile_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        myUtils.setLocale(getActivity());

        ButterKnife.bind(this, view);

        dataCheck();
        alertDialog = new SpotsDialog.Builder().setContext(getActivity()).setTheme(R.style.Custom).build();
        alertDialog.setMessage("Update information .....");
        TextView edit = view.findViewById(R.id.edit_btn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();

            }
        });


        return view;

    }

    private void dataCheck() {
        Gson gson = new Gson();
        userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
        try {
            if (!Objects.equals(userPOJO.getEmail(), null)) {

                Glide.with(getActivity()).load(userPOJO.getToken()).into(profile_photo);
                user_name.setText(userPOJO.getUsername());
                username.setText(userPOJO.getUsername());
                job_title.setText(userPOJO.getJobTitle());
                email.setText(userPOJO.getEmail());
                mobile.setText(userPOJO.getPhone());
                job_titleBar.setText(userPOJO.getJobTitle());
            }

        } catch (Exception e) {
            user_name.setText("Please Sign In First");
            user_name.setTextColor(getResources().getColor(R.color.Red));
            onVisibleText(jobTitle_linear, username_linear, email_linear, mobile_linear, job_titleBar);
        }
    }

    private void onVisibleText(LinearLayout username, LinearLayout job_title, LinearLayout email, LinearLayout mobile, TextView job_titleBar) {
        username.setVisibility(View.GONE);
        job_title.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        mobile.setVisibility(View.GONE);
        job_titleBar.setVisibility(View.GONE);
    }

//    @OnClick(R.id.edit_profile_photo)
//    public void setChooseImage() {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, 2);
//
//    }


    protected void showDialog() {


        dialog = new Dialog(getContext());
        dialog.setCancelable(true);

        View view = getActivity().getLayoutInflater().inflate(R.layout.activity_custom_dialog, null);
        dialog.setContentView(view);

        final EditText usernameEdit = view.findViewById(R.id.edit_info_username);
        final EditText phoneEdit = view.findViewById(R.id.edit_info_phone);
        final EditText passwordEdit = view.findViewById(R.id.edit_info_password);
        final EditText emailEdit = view.findViewById(R.id.edit_info_Email);
        final EditText jopEdit = view.findViewById(R.id.edit_info_jopTitle);

        usernameEdit.setText(username.getText());
        phoneEdit.setText(mobile.getText());
        emailEdit.setText(email.getText());
        jopEdit.setText(job_title.getText());


        Button submit = (Button) view.findViewById(R.id.submit_BT);
        Button cancel = (Button) view.findViewById(R.id.cancel_bt);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "done..!", Toast.LENGTH_SHORT).show();
                getEditData(usernameEdit, phoneEdit, emailEdit, jopEdit, passwordEdit);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });


        dialog.show();
    }

    private void getEditData(EditText usernameEditET, EditText phoneEditET, EditText emailEditET, EditText jopEditET, EditText passwordEditET) {
        String userName, userPhone, userEmail, userJob, userPassword;
        userEmail = emailEditET.getText().toString();
        userName = usernameEditET.getText().toString();
        userPhone = phoneEditET.getText().toString();
        userJob = jopEditET.getText().toString();
        userPassword = passwordEditET.getText().toString();

        if (TextUtils.isEmpty(userName))
            usernameEditET.setError("Required");
        else if (TextUtils.isEmpty(userPhone))
            phoneEditET.setError("Required");
        else if (TextUtils.isEmpty(userEmail))
            emailEditET.setError("Required");
//        else if (TextUtils.isEmpty(userPassword))
//            passwordEditET.setError("Required");
        else if (TextUtils.isEmpty(userJob))
            jopEditET.setError("Required");
        else
            updateData(userName, userPhone, userEmail, userPassword, userJob);


    }

    private void updateData(String userName, String userPhone, String userEmail, String userPassword, String userJob) {
        alertDialog.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", userPOJO.getUserId());
            jsonObject.put("user_name", userName);
            jsonObject.put("email", userEmail);
            jsonObject.put("phone", userPhone);
            jsonObject.put("password", userPassword);
            jsonObject.put("job_title", userJob);
        } catch (Exception e) {
            alertDialog.dismiss();
            e.printStackTrace();
        }
        Log.d("Update_Data", jsonObject.toString());

        AndroidNetworking.post(ConstantsUrl.UpdateUser)
                .addJSONObjectBody(jsonObject)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.cancel();
                        alertDialog.dismiss();
                        Log.d("Response", response.toString());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        userResPOJO resPOJO = gson.fromJson(response.toString(), userResPOJO.class);
                        String userOBJSTR = gson.toJson(resPOJO.getUserInfo());
                        mySharedPreference.setUserOBJ(userOBJSTR);
                        refreshFragment();
                    }

                    @Override
                    public void onError(ANError anError) {
                        alertDialog.dismiss();
                        myUtils.handleError(getActivity(), anError.getErrorBody(), anError.getErrorCode());
                    }
                });
    }

    private void refreshFragment() {
        Profile_fragment fragment = (Profile_fragment)
                getFragmentManager().findFragmentById(R.id.frame_home);

        getFragmentManager().beginTransaction()
                .detach(fragment)
                .attach(fragment)
                .commit();
    }


}
