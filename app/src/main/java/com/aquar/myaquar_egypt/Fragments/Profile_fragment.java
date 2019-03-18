package com.aquar.myaquar_egypt.Fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import java.util.Objects;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


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

        TextView edit=(TextView) view.findViewById(R.id.edit_btn);
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
        UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
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


    protected void showDialog()
    {



        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(true);

        View view  = getActivity().getLayoutInflater().inflate(R.layout.activity_custom_dialog, null);
        dialog.setContentView(view);

        EditText usernameEdit=(EditText) view.findViewById(R.id.edit_info_username);
        EditText phoneEdit=(EditText) view.findViewById(R.id.edit_info_phone);
        EditText emailEdit=(EditText) view.findViewById(R.id.edit_info_Email);
        EditText jopEdit=(EditText) view.findViewById(R.id.edit_info_jopTitle);

        usernameEdit.setText(username.getText());
        phoneEdit.setText(mobile.getText());
        emailEdit.setText(email.getText());
        jopEdit.setText(job_title.getText());


        Button submit = (Button) view.findViewById(R.id.submit_BT);
        Button cancel = (Button) view.findViewById(R.id.cancel_bt);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "done..!", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Canceled", Toast.LENGTH_SHORT).show();
                dialog.cancel();

            }
        });



        dialog.show();
    }


}
