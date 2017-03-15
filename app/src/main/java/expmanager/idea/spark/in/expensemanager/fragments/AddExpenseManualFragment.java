package expmanager.idea.spark.in.expensemanager.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.model.AddExpenseRequest;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.NetworkUtils;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import expmanager.idea.spark.in.expensemanager.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Haresh.Veldurty on 3/14/2017.
 */

public class AddExpenseManualFragment extends Fragment {

    private EditText exp_date,exp_descrip,exp_unit,exp_amount;
    private AutoCompleteTextView autoCompleteTextViewCatId,autoCompleteTextViewInvId;
    private Button btnexpAdd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public AddExpenseManualFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_manual_expense_layout,
                container, false);

        autoCompleteTextViewCatId = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteCatId);
        autoCompleteTextViewInvId = (AutoCompleteTextView) rootView.findViewById(R.id.autoCompleteInvId);

        exp_descrip = (EditText) rootView.findViewById(R.id.exp_descrip);
        exp_unit = (EditText) rootView.findViewById(R.id.exp_unit);
        exp_amount = (EditText) rootView.findViewById(R.id.exp_amount);

        btnexpAdd = (Button) rootView.findViewById(R.id.btnexpAdd);

        exp_date = (EditText) rootView.findViewById(R.id.exp_date);
        exp_date.setText(Utils.getDateTime());


        btnexpAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!NetworkUtils.getInstance().isNetworkAvailable(getActivity())) {

                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if((!autoCompleteTextViewCatId.getText().toString().isEmpty())&&(!autoCompleteTextViewInvId.getText().toString().isEmpty())){

                    AddExpenseRequest addExpenseRequest = new AddExpenseRequest();
                    addExpenseRequest.setInvoiceNumber(autoCompleteTextViewInvId.getText().toString());
                    addExpenseRequest.setCategory(autoCompleteTextViewCatId.getText().toString());
                    addExpenseRequest.setDate(Utils.getDateTime());
                    addExpenseRequest.setAmount(Integer.parseInt(exp_amount.getText().toString()));
                    addExpenseRequest.setDescription(exp_descrip.getText().toString());
                    SessionManager sessionManager = new SessionManager(getActivity());
                    RetrofitApi.getApi().AddExpense(sessionManager.getAuthToken(), addExpenseRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                           // progressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();

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
