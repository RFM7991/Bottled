package com.rk.bottled;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rk.bottled.R;

import java.util.ArrayList;

public class InboxFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public VerticalViewPager viewPager;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public void addViewPager(VerticalViewPager v) {
        this.viewPager = v;
    }

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public InboxFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static InboxFragment newInstance() {
        InboxFragment fragment = new InboxFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

  //      mViewPager = (ViewPager) getActivity().findViewById(R.id.container_main);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);


        // recycler view
        recyclerView = (RecyclerView) view.findViewById(R.id.inbox_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);


        // add conversation fragments
        ConversationFragment convo1 = new ConversationFragment();
        convo1.setPreview("hey there");

        ConversationFragment convo2 = new ConversationFragment();
        convo2.setPreview("fuck me");

        ConversationFragment convo3 = new ConversationFragment();
        convo3.setPreview("yooo dogg");


        // data
        ArrayList<ConversationFragment> fragments = new ArrayList<ConversationFragment>();

        // load conversation fragment
        fragments.add(convo1);
        fragments.add(convo2);
        fragments.add(convo3);

        // specify an adapter
        mAdapter = new RecyclerViewAdapter(fragments);
        ((RecyclerViewAdapter) mAdapter).setViewPager(viewPager);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

}
