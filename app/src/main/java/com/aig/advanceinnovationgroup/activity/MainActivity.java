package com.aig.advanceinnovationgroup.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.fragment.AddNewComplaintFragment;
import com.aig.advanceinnovationgroup.fragment.AddNewIncidentFragment;
import com.aig.advanceinnovationgroup.fragment.AddNewProjectFragment;
import com.aig.advanceinnovationgroup.fragment.AddPraparatoryExamFragment;
import com.aig.advanceinnovationgroup.fragment.CustomerVoiceFragment;
import com.aig.advanceinnovationgroup.fragment.DashboardFragment;
import com.aig.advanceinnovationgroup.fragment.DownloadFragment;
import com.aig.advanceinnovationgroup.fragment.FeeDetailFragment;
import com.aig.advanceinnovationgroup.fragment.MyProfileFragment;
import com.aig.advanceinnovationgroup.fragment.PayFeeFragment;
import com.aig.advanceinnovationgroup.fragment.SocialShareFragment;
import com.aig.advanceinnovationgroup.fragment.ViewAttendanceFragment;
import com.aig.advanceinnovationgroup.fragment.ViewComplaintFragment;
import com.aig.advanceinnovationgroup.fragment.ViewFinalExamFragment;
import com.aig.advanceinnovationgroup.fragment.ViewIncidentFragment;
import com.aig.advanceinnovationgroup.fragment.ViewProjectFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private NavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private LinearLayout ll_fee_management, ll_fee, ll_dashboard, ll_view_attendance, ll_download, ll_project_communication, ll_project, ll_customer_voice, ll_incident_manager,
                         ll_incident, ll_assessment, ll_exam, ll_complaint, ll_new_complaint, ll_my_profile, ll_social_share;
    private boolean fee_status = true;
    private boolean project_status = true;
    private boolean incident_status = true;
    private boolean assessment_status = true;
    private boolean complaint_status = true;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private TextView tv_fee_detail, tv_pay_fee, tv_add_new_project, tv_view_project, tv_add_new_incident, tv_view_incident, tv_add_preparatory_exam,
                     tv_view_preparatory_exam, tv_view_final_exam, tv_add_complaint, tv_view_complaint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUi();
        proceed();

    }

    private void proceed() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        FragmentTransaction orderTransaction = fragmentManager.beginTransaction();
        orderTransaction.replace(R.id.container, dashboardFragment);
        orderTransaction.commit();
        getSupportActionBar().setTitle("Dashboard");
    }


    private void changeFragment(int position){
        switch (position){
            case 1:
                proceed();
                break;
            case 2:
                ViewAttendanceFragment viewAttendanceFragment = new ViewAttendanceFragment();
                FragmentTransaction orderTransaction = fragmentManager.beginTransaction();
                orderTransaction.replace(R.id.container, viewAttendanceFragment);
                orderTransaction.commit();
                getSupportActionBar().setTitle("View Attendance");
                break;
            case 3:
                FeeDetailFragment feeDetailFragment = new FeeDetailFragment();
                FragmentTransaction feeTransaction = fragmentManager.beginTransaction();
                feeTransaction.replace(R.id.container, feeDetailFragment);
                feeTransaction.commit();
                getSupportActionBar().setTitle("Fee Details");
                break;

            case 4:
                PayFeeFragment payFeeFragment = new PayFeeFragment();
                FragmentTransaction payFeeTransaction = fragmentManager.beginTransaction();
                payFeeTransaction.replace(R.id.container, payFeeFragment);
                payFeeTransaction.commit();
                getSupportActionBar().setTitle("Pay Fee");
                break;

            case 5:
                DownloadFragment downloadFragment = new DownloadFragment();
                FragmentTransaction downloadTransaction = fragmentManager.beginTransaction();
                downloadTransaction.replace(R.id.container, downloadFragment);
                downloadTransaction.commit();
                getSupportActionBar().setTitle("Download");
                break;


            case 6:
                AddNewProjectFragment newProjectFragment = new AddNewProjectFragment();
                FragmentTransaction newProjectTransaction = fragmentManager.beginTransaction();
                newProjectTransaction.replace(R.id.container, newProjectFragment);
                newProjectTransaction.commit();
                getSupportActionBar().setTitle("Add New Project");
                break;

            case 7:
                ViewProjectFragment viewProjectFragment = new ViewProjectFragment();
                FragmentTransaction viewProjectTransaction = fragmentManager.beginTransaction();
                viewProjectTransaction.replace(R.id.container, viewProjectFragment);
                viewProjectTransaction.commit();
                getSupportActionBar().setTitle("View Project");
                break;

            case 8:
                CustomerVoiceFragment voiceFragment = new CustomerVoiceFragment();
                FragmentTransaction voiceTransaction = fragmentManager.beginTransaction();
                voiceTransaction.replace(R.id.container, voiceFragment);
                voiceTransaction.commit();
                getSupportActionBar().setTitle("Customer Voice");
                break;

            case 9:
                AddNewIncidentFragment incidentFragment = new AddNewIncidentFragment();
                FragmentTransaction incidentTransaction = fragmentManager.beginTransaction();
                incidentTransaction.replace(R.id.container, incidentFragment);
                incidentTransaction.commit();
                getSupportActionBar().setTitle("Add New Incident");
                break;

            case 10:
                ViewIncidentFragment viewIncidentFragment = new ViewIncidentFragment();
                FragmentTransaction viewIncidentTransaction = fragmentManager.beginTransaction();
                viewIncidentTransaction.replace(R.id.container, viewIncidentFragment);
                viewIncidentTransaction.commit();
                getSupportActionBar().setTitle("View Incident");
                break;

            case 11:
                AddPraparatoryExamFragment addExamFragment = new AddPraparatoryExamFragment();
                FragmentTransaction addExamTransaction = fragmentManager.beginTransaction();
                addExamTransaction.replace(R.id.container, addExamFragment);
                addExamTransaction.commit();
                getSupportActionBar().setTitle("Add Preparatory Exam");
                break;

            case 12:
                ViewFinalExamFragment finalExamFragment = new ViewFinalExamFragment();
                FragmentTransaction viewExamTransaction = fragmentManager.beginTransaction();
                viewExamTransaction.replace(R.id.container, finalExamFragment);
                viewExamTransaction.commit();
                getSupportActionBar().setTitle("View Final Exam");
                break;

            case 13:
                AddNewComplaintFragment newComplaintFragment = new AddNewComplaintFragment();
                FragmentTransaction addComplaintTransaction = fragmentManager.beginTransaction();
                addComplaintTransaction.replace(R.id.container, newComplaintFragment);
                addComplaintTransaction.commit();
                getSupportActionBar().setTitle("Add New Complaint");
                break;

            case 14:
                ViewComplaintFragment viewComplaintFragment = new ViewComplaintFragment();
                FragmentTransaction viewComplaintTransaction = fragmentManager.beginTransaction();
                viewComplaintTransaction.replace(R.id.container, viewComplaintFragment);
                viewComplaintTransaction.commit();
                getSupportActionBar().setTitle("View Complaint");
                break;

            case 15:
                MyProfileFragment myProfileFragment = new MyProfileFragment();
                FragmentTransaction myProfileTransaction = fragmentManager.beginTransaction();
                myProfileTransaction.replace(R.id.container, myProfileFragment);
                myProfileTransaction.commit();
                getSupportActionBar().setTitle("My Profile");
                break;

            case 16:
                SocialShareFragment socialShareFragment = new SocialShareFragment();
                FragmentTransaction socialShareTransaction = fragmentManager.beginTransaction();
                socialShareTransaction.replace(R.id.container, socialShareFragment);
                socialShareTransaction.commit();
                getSupportActionBar().setTitle("Social Share");
                break;

        }

    }


    /*
    *Initialize all view
    * */

    private void initUi() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        ll_dashboard = (LinearLayout)findViewById(R.id.ll_dashboard);
        ll_view_attendance =(LinearLayout)findViewById(R.id.ll_view_attendance);
        ll_fee_management =(LinearLayout) findViewById(R.id.ll_fee_management);
        ll_fee = (LinearLayout)findViewById(R.id.ll_fee);
        ll_download = (LinearLayout) findViewById(R.id.ll_download);
        ll_project_communication = (LinearLayout) findViewById(R.id.ll_communication);
        ll_project = (LinearLayout) findViewById(R.id.ll_project);
        ll_customer_voice = (LinearLayout) findViewById(R.id.ll_customer_voice);
        ll_incident_manager = (LinearLayout) findViewById(R.id.ll_incident_manager);
        ll_incident = (LinearLayout) findViewById(R.id.ll_incident);
        ll_assessment = (LinearLayout) findViewById(R.id.ll_assessment);
        ll_exam = (LinearLayout) findViewById(R.id.ll_exam);
        ll_complaint = (LinearLayout) findViewById(R.id.ll_complaint);
        ll_new_complaint = (LinearLayout) findViewById(R.id.ll_new_complaint);
        ll_my_profile = (LinearLayout) findViewById(R.id.ll_my_profile);
        ll_social_share = (LinearLayout) findViewById(R.id.ll_social_share);

        tv_fee_detail = (TextView) findViewById(R.id.tv_fee_detail);
        tv_pay_fee = (TextView) findViewById(R.id.tv_pay_fee);

        tv_add_new_project = (TextView) findViewById(R.id.tv_add_new_project);
        tv_view_project = (TextView) findViewById(R.id.tv_view_project);

        tv_add_new_incident = (TextView) findViewById(R.id.tv_add_new_incident);
        tv_view_incident = (TextView) findViewById(R.id.tv_view_incident);

        tv_add_preparatory_exam = (TextView) findViewById(R.id.tv_add_exam);
        tv_view_preparatory_exam = (TextView) findViewById(R.id.tv_view_exam);
        tv_view_final_exam = (TextView) findViewById(R.id.tv_view_final_exam);

        tv_add_complaint = (TextView) findViewById(R.id.tv_add_new_complaint);
        tv_view_complaint = (TextView) findViewById(R.id.tv_view_complaint);




        /*
        *
        * *Click listner
        * */
        ll_dashboard.setOnClickListener(this);
        ll_view_attendance.setOnClickListener(this);
        ll_fee_management.setOnClickListener(this);
        ll_download.setOnClickListener(this);
        ll_project_communication.setOnClickListener(this);
        ll_customer_voice.setOnClickListener(this);
        ll_incident_manager.setOnClickListener(this);
        ll_assessment.setOnClickListener(this);
        ll_complaint.setOnClickListener(this);
        ll_my_profile.setOnClickListener(this);
        ll_social_share.setOnClickListener(this);
        tv_fee_detail.setOnClickListener(this);
        tv_pay_fee.setOnClickListener(this);
        tv_add_new_project.setOnClickListener(this);
        tv_view_project.setOnClickListener(this);
        tv_add_new_incident.setOnClickListener(this);
        tv_view_incident.setOnClickListener(this);
        tv_view_project.setOnClickListener(this);
        tv_add_preparatory_exam.setOnClickListener(this);
        tv_view_preparatory_exam.setOnClickListener(this);
        tv_view_final_exam.setOnClickListener(this);
        tv_add_complaint.setOnClickListener(this);
        tv_view_complaint.setOnClickListener(this);

        closeDrawer();


    }


    private void closeDrawer(){
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);


            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_dashboard:
                changeFragment(1);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_view_attendance:
                changeFragment(2);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_fee_management:
                if (fee_status) {
                    ll_fee.setVisibility(View.VISIBLE);
                    fee_status = false;
                }else {
                    ll_fee.setVisibility(View.GONE);
                    fee_status = true;
                }
                break;
            case R.id.tv_fee_detail:
                changeFragment(3);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.tv_pay_fee:
                changeFragment(4);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_download:
                changeFragment(5);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_communication:
                if (project_status) {
                    ll_project.setVisibility(View.VISIBLE);
                    project_status = false;
                }else {
                    ll_project.setVisibility(View.GONE);
                    project_status = true;
                }
                break;
            case R.id.tv_add_new_project:
                changeFragment(6);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.tv_view_project:
                changeFragment(7);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_customer_voice:
                changeFragment(8);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_incident_manager:
                if (incident_status) {
                    ll_incident.setVisibility(View.VISIBLE);
                    incident_status = false;
                }else {
                    ll_incident.setVisibility(View.GONE);
                    incident_status = true;
                }
                break;

            case R.id.tv_add_new_incident:
                changeFragment(9);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.tv_view_incident:
                changeFragment(10);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_assessment:
                if (assessment_status) {
                    ll_exam.setVisibility(View.VISIBLE);
                    assessment_status = false;
                }else {
                    ll_exam.setVisibility(View.GONE);
                    assessment_status = true;
                }
                break;
            case R.id.tv_add_exam:
                changeFragment(11);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.tv_view_exam:
                changeFragment(12);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.tv_view_final_exam:
                changeFragment(13);
                drawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.ll_complaint:
                if (complaint_status) {
                    ll_new_complaint.setVisibility(View.VISIBLE);
                    complaint_status = false;
                }else {
                    ll_new_complaint.setVisibility(View.GONE);
                    complaint_status = true;
                }
                break;
            case R.id.tv_add_new_complaint:
                changeFragment(13);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.tv_view_complaint:
                changeFragment(14);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_my_profile:
                changeFragment(15);
                drawerLayout.closeDrawer(Gravity.START);
                break;
            case R.id.ll_social_share:
                changeFragment(16);
                drawerLayout.closeDrawer(Gravity.START);
                break;
        }
    }
}
