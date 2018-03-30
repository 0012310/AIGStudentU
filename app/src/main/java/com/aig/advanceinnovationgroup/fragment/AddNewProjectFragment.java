package com.aig.advanceinnovationgroup.fragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.activity.MainActivity;
import com.aig.advanceinnovationgroup.adapter.CustomAdapter;
import com.aig.advanceinnovationgroup.model.ProjectType;
import com.aig.advanceinnovationgroup.model.ProjectTypeData;
import com.aig.advanceinnovationgroup.util.AndroidMultiPartEntity;
import com.aig.advanceinnovationgroup.util.AppController;
import com.aig.advanceinnovationgroup.util.AppPreferences;
import com.aig.advanceinnovationgroup.util.Constant;
import com.aig.advanceinnovationgroup.util.Filepath;
import com.aig.advanceinnovationgroup.util.Utils;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
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

    private String selectedImagePath ;
    long totalSize = 0;
    private String project_type_id;

    private File selectedFile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectTypeList = new ArrayList<>();
        projectList = new ArrayList<>();
        projectListId = new ArrayList<>();
        if (!checkPermission()) {

        } else {
            if (checkPermission()) {
                requestPermissionAndContinue();
            } else {

            }
        }


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
        addProjectBT.setOnClickListener(this);
        projectTypeSP.setOnItemSelectedListener(type_listner);
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
        startActivityForResult(intent, REQUEST_PICK_FILE);

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICK_FILE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            String uriString = uri.toString();
            selectedImagePath = Filepath.getPath(getActivity(), uri);
            fileNameET.setText(selectedImagePath);
        }
    }





    /**
     * Uploading the file to server
     * */
    private class UploadFileToServer extends AsyncTask<Void, Integer, String> {

        String selectfile;

        @Override

        protected void onPreExecute() {
            // setting progress bar to zero
            // progressBar.setProgress(0);

            mProgressDialog = Utils.showProgressDialog(getActivity());

            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            // Making progress bar visible

        }

        @Override
        protected String doInBackground(Void... params) {

            return uploadFile();

        }

        @SuppressWarnings("deprecation")
        private String uploadFile() {
            String responseString = null;

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Constant.ADDPROJECT_URL);

            try {
                AndroidMultiPartEntity entity = new AndroidMultiPartEntity(new AndroidMultiPartEntity.ProgressListener() {

                    @Override
                    public void transferred(long num) {
                        publishProgress((int) ((num / (float) totalSize) * 100));
                    }
                });
                String file1 = Environment.getExternalStorageDirectory()+"/AIG";
                File myDir = new File(file1, selectedImagePath);
                FileBody fileBody1 = new FileBody(myDir);

                entity.addPart("project_file", fileBody1);

                // Extra parameters if you want to pass to server
                entity.addPart("project_name", new StringBody(projectNameET.getText().toString()));
                entity.addPart("project_description", new StringBody(projectDescriptionET.getText().toString()));
                entity.addPart("project_type", new StringBody(project_type_id));
                entity.addPart("trainer_id", new StringBody("2"));
                entity.addPart("student_id",new StringBody(AppPreferences.getString(getActivity(), AppPreferences.PREF_KEY.STUDENT_ID)));

                totalSize = entity.getContentLength();
                httppost.setEntity(entity);

                System.out.println(httppost + "aa");
                // Making server call
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity r_entity = response.getEntity();

                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == 200) {
                    // Server response
                    responseString = EntityUtils.toString(r_entity);
                } else {
                    responseString = "Error occurred! Http Status Code: "
                            + statusCode;
                }

            } catch (ClientProtocolException e) {
                responseString = e.toString();
            } catch (IOException e) {
                responseString = e.toString();
            }

            return responseString;

        }

        @Override
        protected void onPostExecute(String result) {
            Log.e("", "Response from server: " + result);
            Utils.cancelProgressDialog(mProgressDialog);
            try {
                JSONObject object = new JSONObject(result);
                Toast.makeText(getActivity(), object.getString("add_project"), Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).changeFragment(7);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            super.onPostExecute(result);
        }

    }


    private AdapterView.OnItemSelectedListener type_listner = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            if (position>0){
                project_type_id = projectListId.get(position);
                Log.d("id : ", project_type_id);

            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_browse:
                showFileChooser();
                break;
            case R.id.btn_add_project:
                new UploadFileToServer().execute();
                break;

        }
    }




    private static final int PERMISSION_REQUEST_CODE = 200;
    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("sadsa");
                alertBuilder.setMessage("dfsjahffujsk");
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults.length > 0) {

                boolean flag = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                    }
                }
                if (flag) {

                } else {
                    getActivity().finish();
                }

            } else {
                getActivity().finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
