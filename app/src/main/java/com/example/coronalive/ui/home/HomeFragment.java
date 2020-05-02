package com.example.coronalive.ui.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronalive.MycustomAdapter;
import com.example.coronalive.R;
import com.example.coronalive.helper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView confirm,death,recover,update,active;
    ListView list;

    public static List<helper> helperList = new ArrayList<>();

    helper help;
    MycustomAdapter mycustomAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        confirm= root.findViewById(R.id.confirmed);
        active= root.findViewById(R.id.active);
        death= root.findViewById(R.id.death);
        recover=root.findViewById(R.id.recover);
        update = root.findViewById(R.id.time);

        list = root.findViewById(R.id.list);

        fetchData();

        mycustomAdapter = new MycustomAdapter(getActivity().getApplicationContext(), helperList);
        list.setAdapter(mycustomAdapter);

        return root;
    }




    public void fetchData() {
        String url ="https://api.covid19india.org/data.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject= new JSONObject(response);

                    JSONArray array = (JSONArray) jsonObject.get("statewise");
                    JSONObject innerObj = (JSONObject) array.get(0);
                    confirm.setText(innerObj.getString("confirmed"));
                    active.setText(innerObj.getString("active"));
                    death.setText(innerObj.getString("deaths"));
                    recover.setText(innerObj.getString("recovered"));
                    update.setText(innerObj.getString("lastupdatedtime"));

                    JSONArray array1 = (JSONArray) jsonObject.get("statewise");

                    for (int i=1 ; i<array1.length();++i){
                        JSONObject inner = (JSONObject) array1.get(i);
                        String state =inner.getString("state");
                        String confirm = inner.getString("confirmed");
                        String recover = inner.getString("recovered");
                        String death = inner.getString("deaths");

                        help = new helper(state, confirm, recover, death);
                        helperList.add(help);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(request);

    }
}