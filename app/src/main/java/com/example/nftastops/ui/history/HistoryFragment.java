package com.example.nftastops.ui.history;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.nftastops.R;
import com.example.nftastops.model.StopTransactions;
import com.example.nftastops.utilclasses.NetworkAPICall;
import com.example.nftastops.utilclasses.recyclerView.RVAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    private NetworkAPICall apiCAll;
    private List<StopTransactions> stopTransactions;
    RVAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        sendViewModel =
//                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_history, container, false);
        // final TextView textView = root.findViewById(R.id.text_send);
//        sendViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        RecyclerView rv = root.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        stopTransactions = new ArrayList<>();
        adapter = new RVAdapter(stopTransactions,onItemClickListener,"history");
        rv.setAdapter(adapter);
        apiCAll = NetworkAPICall.getInstance(getActivity());
        makeApiCall("transactions");
        return root;
    }

    private void makeApiCall(String url) {
        apiCAll.makeGet(getActivity(), url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //String results = response;
                Log.d("login", "history response: "+response);
                List<StopTransactions> results = new ArrayList<>();
                try {
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<StopTransactions>>() {
                    }.getType();
                    results = gson.fromJson(response, type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (results.isEmpty()) {
                    StopTransactions e = new StopTransactions(getActivity());
                    e.setStop_id("Stop Dummy");
                    e.setDate("14th Dec 2018");
                    e.setLocation("location 1");
                    e.setRequest_type("New");
                    StopTransactions e2 = new StopTransactions(getActivity());
                    e2.setStop_id("Stop Dummy 2");
                    e2.setDate("15th Dec 2019");
                    e2.setLocation("location 2");
                    e2.setRequest_type("Update");
                    results.add(e);
                    results.add(e2);
                }
                //IMPORTANT: set data here and notify
                stopTransactions.addAll(results);
                adapter.notifyDataSetChanged();

//                Toast.makeText(
//                        getActivity(),
//                        "Showing history", Toast.LENGTH_SHORT
//                ).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorString = "Error in showing Transactions";
//                Toast.makeText(
//                        getContext(),
//                        errorString, Toast.LENGTH_SHORT
//                ).show();
                System.out.println(errorString);
            }
        });
    }

    RVAdapter.OnItemClickListener onItemClickListener = new RVAdapter.OnItemClickListener() {


        @Override
        public void onItemClick(StopTransactions transaction) {

            /*Gson gson = new Gson();
            String transactionItem = gson.toJson(stopTransactions);
            Bundle args = new Bundle();
            args.putString("stopTransaction", transactionItem);

            StopFragment1 stopFragment1 = new StopFragment1();
            stopFragment1.setArguments(args);
            replaceFragment(stopFragment1); */


            Gson gson = new Gson();
            String transactionItem = gson.toJson(transaction);
            Bundle args = new Bundle();
            args.putString("stopTransaction", transactionItem);

            HistoryDetailedFragment historyDetailedFragment = new HistoryDetailedFragment();
            historyDetailedFragment.setArguments(args);
            replaceFragment(historyDetailedFragment);
        }
    };

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
