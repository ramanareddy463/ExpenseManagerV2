package expmanager.idea.spark.in.expensemanager.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.adapters.SalesListAdapter;
import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.model.AddSaleRequest;
import expmanager.idea.spark.in.expensemanager.model.Sales;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import expmanager.idea.spark.in.expensemanager.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Haresh.Veldurty on 3/14/2017.
 */

public class SalesFragment extends Fragment {
    private Spinner typespinner;
    Button addsales;
    List<Sales> list;
    DatabaseHandler db;
    ListView mListView;
    SalesListAdapter adapt;
    EditText datesale,amountsale;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sales_layout,
                container, false);
        db = new DatabaseHandler(getActivity());
        typespinner = (Spinner) rootView.findViewById(R.id.typespinner);
        datesale = (EditText) rootView.findViewById(R.id.datesale);
        amountsale = (EditText) rootView.findViewById(R.id.amountsale);
        addsales = (Button) rootView.findViewById(R.id.addsales);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.saletype, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        typespinner.setAdapter(adapter);
        datesale.setText(Utils.getDateTime());
        mListView  = (ListView) rootView.findViewById(R.id.saleslist);

        list = db.getAllSalesDetails();

        adapt  = new SalesListAdapter(getActivity(), R.layout.sales_list_item, list);
        mListView.setAdapter(adapt);

        addsales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!typespinner.getSelectedItem().toString().isEmpty() && !datesale.getText().toString().isEmpty()
                        && ! amountsale.getText().toString().isEmpty()) {
                   final  Sales task = new Sales(typespinner.getSelectedItem().toString(),datesale.getText().toString(),
                            amountsale.getText().toString());

                    AddSaleRequest addSaleRequest = new AddSaleRequest();
                    addSaleRequest.setAmount(Integer.parseInt(amountsale.getText().toString()));
                    addSaleRequest.setDate(datesale.getText().toString());
                    addSaleRequest.setSaleType(typespinner.getSelectedItem().toString());



                    SessionManager sessionManager = new SessionManager(getActivity());
                    RetrofitApi.getApi().AddSale(sessionManager.getAuthToken(), addSaleRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                           // progressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

                                db.addSalesDetails(task);
                                adapt.add(task);
                                adapt.notifyDataSetChanged();



//                                Gson gson = new Gson();
//                                try {
//                                    CreateOrganisationResponse createOrganisationResponse = gson.fromJson(response.body().string(), CreateOrganisationResponse.class);
////                                    SessionManager sessionManager = new SessionManager(getActivity());
////                                    sessionManager.createLoginSession(loginResponse.getToken());
//
//                                    TangibleExpenseFragment fragmenttangibleexp = new TangibleExpenseFragment();
//                                    getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmenttangibleexp).commit();
//
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }

                            } else {

                                Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });



        return rootView;
    }


}

