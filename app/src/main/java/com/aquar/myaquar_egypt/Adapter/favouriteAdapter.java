package com.aquar.myaquar_egypt.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.aquar.myaquar_egypt.Activity.ProjectdetailsActivity;
import com.aquar.myaquar_egypt.Fragments.homeFragment;
import com.aquar.myaquar_egypt.InternalStorage.Session;
import com.aquar.myaquar_egypt.InternalStorage.mySharedPreference;
import com.aquar.myaquar_egypt.Model.Favouirtes.favouriteObjPOJO;
import com.aquar.myaquar_egypt.Model.Login.UserInfo;
import com.aquar.myaquar_egypt.R;
import com.aquar.myaquar_egypt.Utils.ConstantsUrl;
import com.aquar.myaquar_egypt.Utils.myUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;

/**
 * Created by aswany on 3/2/19.
 */

public class favouriteAdapter extends RecyclerView.Adapter<favouriteAdapter.myView> {
    private Context myContext;
    private ArrayList<favouriteObjPOJO> favouriteObjPOJOS = new ArrayList<>();
//    private AlertDialog dialog;
    private Dialog dialog;

    public favouriteAdapter(Context myContext, ArrayList<favouriteObjPOJO> favouriteObjPOJOS) {
        this.myContext = myContext;
        this.favouriteObjPOJOS = favouriteObjPOJOS;
    }

    @NonNull
    @Override
    public favouriteAdapter.myView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_of_favourite_list, viewGroup, false);
        return new myView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final favouriteAdapter.myView myView, int i) {

        final favouriteObjPOJO pojo = favouriteObjPOJOS.get(i);
//        dialog = new SpotsDialog.Builder().setContext(myContext).setTheme(R.style.Custom).build();
//        dialog.setMessage("Please wait.....");
        dialog = myUtils.LoadingDialog(myContext);

        Glide.with(myContext).load(pojo.getProjectImg()).into(myView.image_of_favourite_list);
        myView.text_of_favourite.setText(pojo.getProductTitle());
//        myView.ratingBar.setNumStars(pojo.);

        myView.like_btn_of_favourite_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myView.setData(pojo);


            }
        });
        myView.fragment_favourite_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Session.getInstance().setTypesOfUnitID(String.valueOf(pojo.getProductId()));

                myContext.startActivity(new Intent(myContext, ProjectdetailsActivity.class));
                homeFragment fragment_home = new homeFragment();
//                fragment_home.id= (int) pojo.getProductId();

            }
        });
    }

    @Override
    public int getItemCount() {
        try {
            return favouriteObjPOJOS.size();

        } catch (Exception e) {
            return 0;
        }

    }

    public class myView extends RecyclerView.ViewHolder {
        ImageView image_of_favourite_list;
        TextView text_of_favourite;
        RatingBar ratingBar;
        Button like_btn_of_favourite_list;
        LinearLayout fragment_favourite_LL;

        public myView(View viewGroup) {
            super(viewGroup);
            image_of_favourite_list = itemView.findViewById(R.id.image_of_favourite_list);
            text_of_favourite = itemView.findViewById(R.id.text_of_favourite);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            like_btn_of_favourite_list = itemView.findViewById(R.id.like_btn_of_favourite_list);
            fragment_favourite_LL = itemView.findViewById(R.id.fragment_favourite_LL);
        }

        public void setData(final favouriteObjPOJO pojo) {
            dialog.show();
            Gson gson = new Gson();
            UserInfo userPOJO = gson.fromJson(mySharedPreference.getUserOBJ(), UserInfo.class);
            final JSONObject object = new JSONObject();
            try {
                object.put("user_id", String.valueOf(userPOJO.getUserId()));
                object.put("project_id", pojo.getProductId());
            } catch (Exception e) {
                e.printStackTrace();
            }
            pojo.getProductId();
            AndroidNetworking.post(ConstantsUrl.favorite)
                    .setPriority(Priority.MEDIUM)
                    .addJSONObjectBody(object)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            myUtils.handleError(myContext, response.toString(), response.length());
                            myView.this.like_btn_of_favourite_list.setVisibility(View.GONE);
                            favouriteObjPOJOS.remove(pojo);
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }

                        @Override
                        public void onError(ANError anError) {
//                            Toast.makeText(myContext, anError.toString(), Toast.LENGTH_SHORT).show();
                            myUtils.handleError(myContext, anError.getErrorBody(), anError.getErrorCode());
                            dialog.dismiss();


                            Log.d("favouriteFragment", anError.getResponse() + "");
                            Log.d("favouriteFragment", anError.getErrorBody() + "");
                        }
                    });
        }
    }
}
