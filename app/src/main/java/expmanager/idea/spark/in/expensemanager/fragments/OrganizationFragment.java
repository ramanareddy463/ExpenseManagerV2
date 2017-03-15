package expmanager.idea.spark.in.expensemanager.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import expmanager.idea.spark.in.expensemanager.MainActivity;
import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.SignUpActivity;
import expmanager.idea.spark.in.expensemanager.model.CreateOrganisationRequest;
import expmanager.idea.spark.in.expensemanager.model.CreateOrganisationResponse;
import expmanager.idea.spark.in.expensemanager.model.LoginResponse;
import expmanager.idea.spark.in.expensemanager.model.SignUpRequest;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import expmanager.idea.spark.in.expensemanager.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Haresh.Veldurty on 2/21/2017.
 */

public class OrganizationFragment extends Fragment {

    private ImageView setupexp;
    private EditText etOrganizationName;
    private ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public OrganizationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.organization_layout,
                container, false);
        setupexp = (ImageView) rootView.findViewById(R.id.setupexp);
        etOrganizationName = (EditText) rootView.findViewById(R.id.et_organization);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        setupexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!etOrganizationName.getText().toString().isEmpty()) {

                    progressBar.setVisibility(View.VISIBLE);

                    CreateOrganisationRequest createOrganisationRequest = new CreateOrganisationRequest(etOrganizationName.getText().toString(), Utils.getDeviceId(getActivity()));
                    SessionManager sessionManager = new SessionManager(getActivity());
                    RetrofitApi.getApi().CreateOrganisation(sessionManager.getAuthToken(), createOrganisationRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            progressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {


                                Gson gson = new Gson();
                                try {
                                    CreateOrganisationResponse createOrganisationResponse = gson.fromJson(response.body().string(), CreateOrganisationResponse.class);
//                                    SessionManager sessionManager = new SessionManager(getActivity());
//                                    sessionManager.createLoginSession(loginResponse.getToken());

                                    TangibleExpenseFragment fragmenttangibleexp = new TangibleExpenseFragment();
                                    getFragmentManager().beginTransaction().replace(R.id.content_frame, fragmenttangibleexp).commit();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } else {

                                Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();

                        }
                    });


                } else {
                    Toast.makeText(getActivity(), "Enter all the fields", Toast.LENGTH_SHORT).show();
                }


            }
        });


        return rootView;
    }


}
