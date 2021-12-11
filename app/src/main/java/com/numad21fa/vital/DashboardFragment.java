package com.numad21fa.vital;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass. Use the {@link DashboardFragment#newInstance} factory method
 * to create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of this fragment using the provided
     * parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        // Instantiate a CalendarView in this fragment
        CalendarView calendarView = view.findViewById(R.id.calendarView);

        // Get the selected of CalendarView in milliseconds
        TextView textView_milli_date = view.findViewById(R.id.textView_milli_date);

        // Get the selected of CalendarView in simpleDate format
        TextView textView_simple_date = view.findViewById(R.id.textView_simple_date);

        // Set a Select Date listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month_1, int day) {
                // in Android CalendarView, January == 0, need +1 back
                String date = year + "/" + (month_1 + 1) + "/" + day;
                // TODO: Change View to User's Daily intakes here
                textView_simple_date.setText("Date Selected: " + date);
                try {
                    // Set Time to a Day
                    String startTime = date + " 00:00:00";
                    String endTime = date + " 23:59:59";
                    // Parse it to milliseconds
                    textView_milli_date.setText("Start: " + simpleDateToMilliDate(startTime) + ", End: " + simpleDateToMilliDate(endTime));
                } catch (ParseException e) {
                    textView_milli_date.setText("Parse Error");
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    // Convert "yyyy/MM/dd HH:mm:ss" format to milliseconds between the desired epoch and 1970-01-01 00:00:00.
    // Try Epoch to Date conversion here https://www.epochconverter.com/
    public Long simpleDateToMilliDate(String simpleDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = sdf.parse(simpleDate);
        return date.getTime();
    }
}