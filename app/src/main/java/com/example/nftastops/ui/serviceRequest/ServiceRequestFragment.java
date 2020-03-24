package com.example.nftastops.ui.serviceRequest;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nftastops.R;
import com.example.nftastops.model.ServiceRequests;
import com.example.nftastops.model.StopTransactions;
import com.example.nftastops.utilclasses.NetworkAPICall;
import com.example.nftastops.utilclasses.recyclerView.RVAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//import android.widget.Toolbar;

public class ServiceRequestFragment extends Fragment {
    //private SendViewModel sendViewModel;
    Toolbar toolbar;
    ViewPager view_pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Open","Closed"};
    int Numboftabs =2;
    private NetworkAPICall apiCAll;
    private List<ServiceRequests> serviceRequests;
    private List<StopTransactions> stopTransactions;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ServiceRequestFragment() {
        //super(fm);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        sendViewModel =
//                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_service_request, container, false);
       // final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        //toolbar = root.findViewById(R.id.tool_bar);
        //setSupportActionBar(toolbar);
        //((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        //adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs);


        //stopTransactions.add();

        adapter =  new ViewPagerAdapter(getFragmentManager(),Titles,Numboftabs,stopTransactions);



        // Assigning ViewPager View and setting the adapter
        view_pager = root.findViewById(R.id.pager);
        view_pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        //tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs =  root.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // TODO::Setting Custom Color for the Scroll bar indicator of the Tab View
        //tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
        //    @Override
        //    public int getIndicatorColor(int position) {
        //        return getResources().getColor(R.color.tabsScrollColor);
        //    }
        //});

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(view_pager);

        return root;
    }

}
