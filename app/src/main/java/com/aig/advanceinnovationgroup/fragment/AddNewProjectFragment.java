package com.aig.advanceinnovationgroup.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.model.ProjectType;
import com.aig.advanceinnovationgroup.model.ProjectTypeData;
import com.aig.advanceinnovationgroup.util.AppController;
import com.aig.advanceinnovationgroup.util.Constant;
import com.aig.advanceinnovationgroup.util.Utils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;


public class AddNewProjectFragment extends Fragment implements View.OnClickListener {

    private AppCompatSpinner projectTypeSP;
    private EditText projectNameET, projectDescriptionET, fileNameET;
    private Button browseBT, addProjectBT;
    private Dialog mProgressDialog;
    private List<ProjectType> projectTypeList;
    private ArrayList<String> projectList;
    private ArrayList<String> projectListId;
    private int mposition;
    private static final int REQUEST_PICK_FILE = 1;

    private Uri fileUri;
    String selectedImagePath = "";

    private File selectedFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectTypeList = new ArrayList<>();
        projectList = new ArrayList<>();
        projectListId = new ArrayList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_new_project, container, false);

        initView(view);
        projectTypeData();

        return view;
    }

    private void initView(View view) {
        projectTypeSP = (AppCompatSpinner) view.findViewById(R.id.sp_project_type);
        projectNameET = (EditText) view.findViewById(R.id.et_project_name);
        projectDescriptionET = (EditText) view.findViewById(R.id.et_project_description);
        fileNameET = (EditText) view.findViewById(R.id.et_file_name);
        browseBT = (Button) view.findViewById(R.id.btn_browse);
        addProjectBT = (Button) view.findViewById(R.id.btn_add_project);

        browseBT.setOnClickListener(this);
    }


    public void projectTypeData(){
        mProgressDialog = Utils.showProgressDialog(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.PROJECTTYPE_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response :", response);
                Utils.cancelProgressDialog(mProgressDialog);
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new StringReader(response));
                reader.setLenient(true);
                ProjectTypeData projectTypeData = gson.fromJson(reader, ProjectTypeData.class);
//                AttendanceData attendanceData = new Gson().fromJson(response, AttendanceData.class);
                int status = projectTypeData.getStatus();
                if (status==1) {
                    projectTypeList = projectTypeData.getProjectType();

                    projectList.add("Select Project Type");
                    projectListId.add("0");

                    for (int i = 0; i < projectTypeList.size(); i++) {
                        projectList.add(projectTypeList.get(i).getProjectType());
                        projectListId.add(projectTypeList.get(i).getProjectId());

                    }

                    // Initializing an ArrayAdapter
                    final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                            getActivity(), android.R.layout.simple_spinner_dropdown_item, projectList) {
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
                    projectTypeSP.setAdapter(spinnerArrayAdapter);
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

    private void showFileChooser() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    REQUEST_PICK_FILE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getActivity(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_FILE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageUri = data.getData();
            try {
                Log.d("file :", selectedImageUri.getScheme());
                Log.d("file :", selectedImageUri.getPath());
                selectedImagePath = selectedImageUri.getPath();
                String filename = selectedImagePath.substring(selectedImagePath.lastIndexOf("/") + 1).replaceAll("%20", " ");;
                fileNameET.setText(filename);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_CANCELED) {

            // user cancelled Image capture
            Toast.makeText(getContext(),
                    "User cancelled file capture", Toast.LENGTH_SHORT)
                    .show();

        } else {
            // failed to capture image
            Toast.makeText(getContext(),
                    "Sorry! Failed to capture file", Toast.LENGTH_SHORT)
                    .show();
        }


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_browse:
                showFileChooser();
                break;
        }
    }
}
