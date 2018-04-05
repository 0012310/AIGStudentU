package com.aig.advanceinnovationgroup.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.EditUser;
import com.aig.advanceinnovationgroup.model.UserDetail;
import com.aig.advanceinnovationgroup.util.AppController;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 4/5/2018.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyHolder> {

    List<UserDetail> userDetailList;
    Context context;
    String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6IjEiLCJyb2xlIjoiU1VQRVJBRE1JTiJ9.II1eEmwbKL8ke2-UAm1mdflIkbe5RZi3I3su0x7Ccn1sAwGOhXnTX38sHrMKzLIZtShv19i9eL9zhreUw8rylg";

    public UserAdapter(Context context, List<UserDetail> userDetails){
        this.context = context;
        this.userDetailList = userDetails;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_single_row, parent, false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        final UserDetail detail = userDetailList.get(position);

        holder.nameTV.setText(detail.getName());
        holder.userNameTV.setText(detail.getUserName());
        holder.passwordTV.setText(detail.getPassword());
        holder.roleTV.setText(detail.getRole());

        holder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDetailList.remove(position);
                notifyDataSetChanged();
                deleteUser(detail.getId());
            }
        });

        holder.editIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditUser.class);
                intent.putExtra("user", (Serializable) detail);
                context.startActivity(intent);
            }
        });

    }

    private void deleteUser(int id) {
        JSONObject object = new JSONObject();
        try {
            object.put("id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "http://139.59.84.10/HadariOnlineR/rest/hello/delete", object, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    Toast.makeText(context, response.getString("msg"), Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Token "+ token);
                return map;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public int getItemCount() {
        return userDetailList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        private TextView userNameTV, passwordTV, nameTV, roleTV;
        private ImageView deleteIV, editIV;

        public MyHolder(View itemView) {
            super(itemView);

            userNameTV = (TextView) itemView.findViewById(R.id.tv_userName);
            passwordTV = (TextView) itemView.findViewById(R.id.tv_password);
            nameTV = (TextView) itemView.findViewById(R.id.tv_name);
            roleTV = (TextView) itemView.findViewById(R.id.tv_role);
            deleteIV = (ImageView) itemView.findViewById(R.id.iv_delete);
            editIV = (ImageView) itemView.findViewById(R.id.iv_edit);
        }
    }
}
