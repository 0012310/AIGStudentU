package com.aig.advanceinnovationgroup.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aig.advanceinnovationgroup.R;
import com.aig.advanceinnovationgroup.fragment.AddNewComplaintFragment;
import com.aig.advanceinnovationgroup.fragment.AddNewIncidentFragment;
import com.aig.advanceinnovationgroup.fragment.AddNewProjectFragment;
import com.aig.advanceinnovationgroup.fragment.AddPraparatoryExamFragment;
import com.aig.advanceinnovationgroup.fragment.CustomerVoiceFragment;
import com.aig.advanceinnovationgroup.fragment.DashboardFragment;
import com.aig.advanceinnovationgroup.fragment.DownloadFragment;
import com.aig.advanceinnovationgroup.fragment.ElearningFragment;
import com.aig.advanceinnovationgroup.fragment.FaqFragment;
import com.aig.advanceinnovationgroup.fragment.FeeDetailFragment;
import com.aig.advanceinnovationgroup.fragment.MyProfileFragment;
import com.aig.advanceinnovationgroup.fragment.PayFeeFragment;
import com.aig.advanceinnovationgroup.fragment.ReferFriendFragment;
import com.aig.advanceinnovationgroup.fragment.SocialShareFragment;
import com.aig.advanceinnovationgroup.fragment.ViewAttendanceFragment;
import com.aig.advanceinnovationgroup.fragment.ViewComplaintFragment;
import com.aig.advanceinnovationgroup.fragment.ViewFinalExamFragment;
import com.aig.advanceinnovationgroup.fragment.ViewIncidentFragment;
import com.aig.advanceinnovationgroup.fragment.ViewPreparatoryExamFragment;
import com.aig.advanceinnovationgroup.fragment.ViewProjectFragment;
import com.aig.advanceinnovationgroup.util.AppPreferences;

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
    private RelativeLayout homeRL, elearningRL, helpRL, faqRL;
    private View viewHome, viewElearning, viewhelp, viewFaq;


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


    public void changeFragment(int position){
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
                ViewPreparatoryExamFragment finalExamFragment = new ViewPreparatoryExamFragment();
                FragmentTransaction viewExamTransaction = fragmentManager.beginTransaction();
                viewExamTransaction.replace(R.id.container, finalExamFragment);
                viewExamTransaction.commit();
                getSupportActionBar().setTitle("View Preparatory Exam");
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

            case 17:
                FaqFragment faqFragment = new FaqFragment();
                FragmentTransaction faqTransaction = fragmentManager.beginTransaction();
                faqTransaction.replace(R.id.container, faqFragment);
                faqTransaction.commit();
                getSupportActionBar().setTitle("FAQ");
                break;

            case 18:
                ElearningFragment elearningFragment = new ElearningFragment();
                FragmentTransaction leaarningTransaction = fragmentManager.beginTransaction();
                leaarningTransaction.replace(R.id.container, elearningFragment);
                leaarningTransaction.commit();
                getSupportActionBar().setTitle("E-Learning");
                break;

            case 19:
                ReferFriendFragment referFriendFragment = new ReferFriendFragment();
                FragmentTransaction refFrndTransaction = fragmentManager.beginTransaction();
                refFrndTransaction.replace(R.id.container, referFriendFragment);
                refFrndTransaction.commit();
                getSupportActionBar().setTitle("Refer a Friend");
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

        homeRL = (RelativeLayout) findViewById(R.id.rl_home);
        elearningRL = (RelativeLayout) findViewById(R.id.rl_elearning);
        helpRL = (RelativeLayout) findViewById(R.id.rl_help);
        faqRL = (RelativeLayout) findViewById(R.id.rl_faq);

        viewHome = (View) findViewById(R.id.view_home);
        viewElearning = (View) findViewById(R.id.view_elearning);
        viewhelp = (View) findViewById(R.id.view_help);
        viewFaq = (View) findViewById(R.id.view_faq);




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


        homeRL.setOnClickListener(this);
        elearningRL.setOnClickListener(this);
        helpRL.setOnClickListener(this);
        faqRL.setOnClickListener(this);


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
            case R.id.rl_home:
                tabSelection(1);
                changeFragment(19);
                break;
            case R.id.rl_elearning:
                tabSelection(2);
                changeFragment(18);
                break;
            case R.id.rl_faq:
                tabSelection(3);
                changeFragment(17);
                break;
            case R.id.rl_help:
                tabSelection(4);
                openPopupWindow(helpRL);
                break;


        }
    }


    /*Method to change the background of the selected tabs*/
    private void tabSelection(int position){
        switch(position){
            case 1:
                viewHome.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
                viewElearning.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewFaq.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewhelp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewHome.setVisibility(View.VISIBLE);
                viewElearning.setVisibility(View.GONE);
                viewFaq.setVisibility(View.GONE);
                viewhelp.setVisibility(View.GONE);

                break;
            case 2:
                viewHome.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewElearning.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
                viewFaq.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewhelp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewHome.setVisibility(View.GONE);
                viewElearning.setVisibility(View.VISIBLE);
                viewFaq.setVisibility(View.GONE);
                viewFaq.setVisibility(View.GONE);

                break;
            case 3:
                viewHome.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewElearning.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewFaq.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
                viewhelp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewHome.setVisibility(View.GONE);
                viewElearning.setVisibility(View.GONE);
                viewFaq.setVisibility(View.VISIBLE);
                viewhelp.setVisibility(View.GONE);

                break;
            case 4:
                viewHome.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewElearning.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewFaq.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.white));
                viewhelp.setBackgroundColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
                viewHome.setVisibility(View.GONE);
                viewElearning.setVisibility(View.GONE);
                viewFaq.setVisibility(View.GONE);
                viewhelp.setVisibility(View.VISIBLE);

                break;
        }
    }


    public void openPopupWindow(RelativeLayout viewhelp){
     /*   final View view = new View(MainActivity.this);
        view.setLayoutParams(new ViewGroup.LayoutParams(1, 1));
        view.setBackgroundColor(Color.TRANSPARENT);

        viewhelp.addView(view);

        view.setX(x);
        view.setY(y);*/

        PopupMenu popup = new PopupMenu(MainActivity.this, viewhelp);
        //Inflating the Popup using xml file
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
               switch (item.getItemId()){
                   case R.id.profile:
                       changeFragment(15);
                       break;
                   case R.id.password:
                       Intent intent1 = new Intent(MainActivity.this, ChangePasswordActivity.class);
                       startActivity(intent1);
                       break;
                   case R.id.logout:
                       AppPreferences.clearAllPreferences(MainActivity.this);
                       Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                       startActivity(intent);
                       finish();
                       break;

               }
                return true;
            }
        });

        popup.show();//showing popup menu
    }

}
