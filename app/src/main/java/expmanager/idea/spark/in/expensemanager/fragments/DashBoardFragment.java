package expmanager.idea.spark.in.expensemanager.fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.BasicStroke;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Haresh.Veldurty on 3/7/2017.
 */

public class DashBoardFragment extends Fragment {
    private LinearLayout chartContainer;
    private GraphicalView mChart;
    private Spinner mothspinner;
    private String[] mMonth = new String[] {
            "Jan", "Feb" , "Mar", "Apr", "May", "Jun",
            "Jul", "Aug" , "Sep", "Oct", "Nov", "Dec"
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard_layout,
                container, false);
        chartContainer = (LinearLayout) rootView.findViewById(R.id.chart);
        mothspinner = (Spinner) rootView.findViewById(R.id.mothspinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.months, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        mothspinner.setAdapter(adapter);
        openChart();

        callServiceApi();

        return rootView;
    }

    private void callServiceApi() {

//        SessionManager sessionManager = new SessionManager(getActivity());
//        RetrofitApi.getApi().GetDashboard(sessionManager.getAuthToken(), " ").enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//                if (response.isSuccessful()) {
//
////                    db.addStaff(insertstaff);
////                    StaffFragment.adapt.add(insertstaff);
////                    StaffFragment.adapt.notifyDataSetChanged();
////
////                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
//
//
//
//                } else {
//
//                    Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//                Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }


    private void openChart(){
        int[] x = { 0,1,2,3,4,5,6,7, 8, 9, 10, 11 };
        int[] income = { 2000,2500,2700,3000,2800,3500,3700,3800, 0,0,0,0};
        int[] expense = {2200, 2700, 2900, 2800, 2600, 3000, 3300, 3400, 0, 0, 0, 0 };

// Creating an XYSeries for Income
        XYSeries incomeSeries = new XYSeries("Income");
// Creating an XYSeries for Expense
        XYSeries expenseSeries = new XYSeries("Expense");
// Adding data to Income and Expense Series
        for(int i=0;i<x.length;i++){
            incomeSeries.add(i,income[i]);
            expenseSeries.add(i,expense[i]);
        }
        // Creating a dataset to hold each series
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
// Adding Income Series to the dataset
        dataset.addSeries(incomeSeries);
// Adding Expense Series to dataset
        dataset.addSeries(expenseSeries);

// Creating XYSeriesRenderer to customize incomeSeries
        XYSeriesRenderer incomeRenderer = new XYSeriesRenderer();

        incomeRenderer.setColor(Color.CYAN); //color of the graph set to cyan
        incomeRenderer.setFillPoints(true);
        incomeRenderer.setLineWidth(2f);
        incomeRenderer.setDisplayChartValues(true);
//setting chart value distance
        incomeRenderer.setDisplayChartValuesDistance(10);
//setting line graph point style to circle
        incomeRenderer.setPointStyle(PointStyle.CIRCLE);
//setting stroke of the line chart to solid
        incomeRenderer.setStroke(BasicStroke.SOLID);

// Creating XYSeriesRenderer to customize expenseSeries
        XYSeriesRenderer expenseRenderer = new XYSeriesRenderer();
        expenseRenderer.setColor(Color.GREEN);
        expenseRenderer.setFillPoints(true);
        expenseRenderer.setLineWidth(2f);
        expenseRenderer.setDisplayChartValues(true);
//setting line graph point style to circle
        expenseRenderer.setPointStyle(PointStyle.SQUARE);
//setting stroke of the line chart to solid
        expenseRenderer.setStroke(BasicStroke.SOLID);

// Creating a XYMultipleSeriesRenderer to customize the whole chart
        XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
        multiRenderer.setXLabels(0);
        //multiRenderer.setChartTitle("Income vs Expense Chart");
        multiRenderer.setXTitle("Payment");
        multiRenderer.setYTitle("Pending Amount");

/***
 * Customizing graphs
 */
//setting text size of the title
        multiRenderer.setChartTitleTextSize(28);
//setting text size of the axis title
        multiRenderer.setAxisTitleTextSize(24);
//setting text size of the graph lable
        multiRenderer.setLabelsTextSize(24);
//setting zoom buttons visiblity
        multiRenderer.setZoomButtonsVisible(false);
//setting pan enablity which uses graph to move on both axis
        multiRenderer.setPanEnabled(false, false);
//setting click false on graph
        multiRenderer.setClickEnabled(false);
//setting zoom to false on both axis
        multiRenderer.setZoomEnabled(false, false);
//setting lines to display on y axis
        multiRenderer.setShowGridY(true);
//setting lines to display on x axis
        multiRenderer.setShowGridX(true);
//setting legend to fit the screen size
        multiRenderer.setFitLegend(true);
//setting displaying line on grid
        multiRenderer.setShowGrid(true);
//setting zoom to false
        multiRenderer.setZoomEnabled(false);
//setting external zoom functions to false
        multiRenderer.setExternalZoomEnabled(false);
//setting displaying lines on graph to be formatted(like using graphics)
        multiRenderer.setAntialiasing(true);
//setting to in scroll to false
        multiRenderer.setInScroll(false);
//setting to set legend height of the graph
        multiRenderer.setLegendHeight(30);
//setting x axis label align
        multiRenderer.setXLabelsAlign(Paint.Align.CENTER);
//setting y axis label to align
        multiRenderer.setYLabelsAlign(Paint.Align.LEFT);
//setting text style
        multiRenderer.setTextTypeface("sans_serif", Typeface.NORMAL);
//setting no of values to display in y axis
        multiRenderer.setYLabels(10);
// setting y axis max value, Since i'm using static values inside the graph so i'm setting y max value to 4000.
// if you use dynamic values then get the max y value and set here
        multiRenderer.setYAxisMax(4000);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMin(-0.5);
//setting used to move the graph on xaxiz to .5 to the right
        multiRenderer.setXAxisMax(11);
//setting bar size or space between two bars
//multiRenderer.setBarSpacing(0.5);
//Setting background color of the graph to transparent
        multiRenderer.setBackgroundColor(Color.TRANSPARENT);
//Setting margin color of the graph to transparent
        multiRenderer.setMarginsColor(getResources().getColor(R.color.transparent_background));
        multiRenderer.setApplyBackgroundColor(true);
        multiRenderer.setScale(2f);
//setting x axis point size
        multiRenderer.setPointSize(4f);
//setting the margin size for the graph in the order top, left, bottom, right
        multiRenderer.setMargins(new int[]{30, 30, 30, 30});

        for(int i=0; i< x.length;i++){
            multiRenderer.addXTextLabel(i, mMonth[i]);
        }

// Adding incomeRenderer and expenseRenderer to multipleRenderer
// Note: The order of adding dataseries to dataset and renderers to multipleRenderer
// should be same
        multiRenderer.addSeriesRenderer(incomeRenderer);
        multiRenderer.addSeriesRenderer(expenseRenderer);

//this part is used to display graph on the xml

//remove any views before u paint the chart
        chartContainer.removeAllViews();
//drawing bar chart
        mChart = ChartFactory.getLineChartView(getActivity(), dataset, multiRenderer);
//adding the view to the linearlayout
        chartContainer.addView(mChart);

    }


}
