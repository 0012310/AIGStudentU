package com.aig.advanceinnovationgroup.adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.DaysData;
import com.aig.advanceinnovationgroup.model.DropdownValue;
import com.aig.advanceinnovationgroup.model.TimeData;
import com.aig.advanceinnovationgroup.util.AppController;
import com.aig.advanceinnovationgroup.util.Constant;
import com.aig.advanceinnovationgroup.util.Utils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by admin on 3/26/2018.
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomHolder> {
    List<EditText> allEds = new ArrayList<>();
    List<String> startTime = new ArrayList<>();
    List<String> endTime = new ArrayList<>();
    List<DropdownValue> dropdownValueList = new ArrayList<>();
    private Calendar myCalendar;
    private Context context;
    private int days;
    public StringBuilder commaSepValueBuilder, startTimeValueBuilder, endTimeValueBuilder;
    public String [] dateString ;
    public String [] startTimeString ;
    public String [] endTimeString ;
    private Dialog mProgressDialog;
    private int mposition;

    public CustomAdapter(Context context, int days){
        this.context = context;
        this.days = days;
        dateString = new String[days];


    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View child = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_view, parent, false);
        return new CustomHolder(child);
    }

    @Override
    public void onBindViewHolder(final CustomHolder holder, final int position) {


        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                Log.d("date :",sdf.format(myCalendar.getTime()));

              //  for (int i = 0; i < dateString.length; i++) {
               //     if (i == dateString.length - 1){
                        dateString[position]  = sdf.format(myCalendar.getTime());
                  //  }
              //  }

                holder.dateET.setText(sdf.format(myCalendar.getTime()));


            }

        };

        holder.dateET.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        timeData(holder);

        holder.startTimeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    startTimeString = new String[startTime.size()];
                    startTimeString[position] =startTime.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        holder.endTimeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0){
                    endTimeString = new String[endTime.size()];
                    endTimeString[position] =endTime.get(position);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




    }

    @Override
    public int getItemCount() {
        return days;
    }

    public class CustomHolder extends RecyclerView.ViewHolder {

        private EditText dateET;
        private AppCompatSpinner startTimeSP, endTimeSP;

        public CustomHolder(View itemView) {
            super(itemView);

            dateET = (EditText) itemView.findViewById(R.id.et_date);

            startTimeSP = (AppCompatSpinner) itemView.findViewById(R.id.sp_start_time);
            endTimeSP = (AppCompatSpinner) itemView.findViewById(R.id.sp_end_time);

        }
    }


    public void timeData(final CustomHolder holder){
        mProgressDialog = Utils.showProgressDialog(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.TIME_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                TimeData timeData = gson.fromJson(reader, TimeData.class);
                int status = timeData.getStatus();
                if (status==1) {
                    dropdownValueList = timeData.getDropdownValue();

                    startTime.add("Select Start Time");
                    endTime.add("Select End Time");
                  //  dayListId.add("0");

                    for (int i = 0; i < dropdownValueList.size(); i++) {
                        startTime.add(String.valueOf(dropdownValueList.get(i).getTimeName()));
                        endTime.add(String.valueOf(dropdownValueList.get(i).getTimeName()));

                    }

                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            context, android.R.layout.simple_spinner_dropdown_item, startTime) {
                        @Override
                        public boolean isEnabled(int position) {

                            position = mposition;

                            return true;
                        }

                        @Override
                        public View getDropDownView(int position, View convertView,
                                                    ViewGroup parent) {
                            View view = super.getDropDownView(position, convertView, parent);
                            TextView tv = (TextView) view;

                            if (position == 0) {
                                // Set the hint text color gray
                                tv.setTextColor(Color.GRAY);
                            } else {
                                tv.setTextColor(Color.BLACK);
                            }
                            return view;
                        }
                    };
                    holder.startTimeSP.setAdapter(spinnerArrayAdapter);
                    holder.endTimeSP.setAdapter(spinnerArrayAdapter);

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Utils.cancelProgressDialog(mProgressDialog);
            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

}
