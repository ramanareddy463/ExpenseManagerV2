package expmanager.idea.spark.in.expensemanager.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import expmanager.idea.spark.in.expensemanager.AdminActivity;
import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.adapters.MyStaffDetailsAdapter;
import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.model.AddStaffRequest;
import expmanager.idea.spark.in.expensemanager.model.Staff;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import expmanager.idea.spark.in.expensemanager.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Haresh.Veldurty on 2/22/2017.
 */

public class StaffFragment extends Fragment {
   Button addstaffbtn,cancelstaffdialog,addstafftoDb;
    EditText staffname,started,salary, staffemail,designation,staffaddress,staffphonenumber;
    Spinner spinnershift1,spinnershift2,spinnertime1,spinnertime2,spinnersal;
    DatabaseHandler db;
    ListView stafflist;
    List<Staff> list,staff_list;
    ImageView done;

    private ProgressBar progressBar;

    public static MyStaffDetailsAdapter adapt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public StaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.stafffragment_layout,
                container, false);
        db = new DatabaseHandler(getActivity());
        addstaffbtn = (Button) rootView.findViewById(R.id.addstaffbtn);
        addstaffbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddStaffDialog();
            }
        });
        stafflist = (ListView) rootView.findViewById(R.id.stafflist);
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        done = (ImageView) rootView.findViewById(R.id.complete);
        list = db.getAllStaff();
        if(list != null) {
            adapt = new MyStaffDetailsAdapter(getActivity(), R.layout.list_staff_item, list);
            stafflist.setAdapter(adapt);
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return rootView;
    }

    public void openAddStaffDialog() {
        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addstaffdialog_layout);
        cancelstaffdialog = (Button) dialog.findViewById(R.id.cancelstaffdialog);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().getAttributes().width = (int) (Utils.getDeviceMetrics(getActivity()).widthPixels*0.55);

        spinnershift1 = (Spinner) dialog.findViewById(R.id.spinnershift1);
        spinnershift2 = (Spinner) dialog.findViewById(R.id.spinnershift2);
        spinnertime1 = (Spinner) dialog.findViewById(R.id.spinnertime1);
        spinnertime2 = (Spinner) dialog.findViewById(R.id.spinnertime2);
        spinnersal = (Spinner) dialog.findViewById(R.id.spinnersal);
        addstafftoDb  = (Button) dialog.findViewById(R.id.addstafftodb);
        started = (EditText) dialog.findViewById(R.id.started);
        salary = (EditText) dialog.findViewById(R.id.salary);
        staffname= (EditText) dialog.findViewById(R.id.staff_name);
        staffemail = (EditText) dialog.findViewById(R.id.staffemail);
        designation = (EditText) dialog.findViewById(R.id.designation);
        staffaddress = (EditText) dialog.findViewById(R.id.staffaddress);
        staffphonenumber = (EditText) dialog.findViewById(R.id.staffphonenumber);




        started.setText(Utils.getDateTime());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.weekArray, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnershift1.setAdapter(adapter);
        spinnershift2.setAdapter(adapter);

        ArrayAdapter<CharSequence> adaptertime = ArrayAdapter.createFromResource(getActivity(),
                R.array.timeArray, R.layout.simple_spinner_item);
        adaptertime.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnertime1.setAdapter(adaptertime);
        spinnertime2.setAdapter(adaptertime);

        ArrayAdapter<CharSequence> adapterper = ArrayAdapter.createFromResource(getActivity(),
                R.array.perArray, R.layout.simple_spinner_item);
        adapterper.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinnersal.setAdapter(adapterper);


        dialog.show();

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
        cancelstaffdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        addstafftoDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!staffname.getText().toString().isEmpty() ) {
                    final Staff insertstaff = new Staff();

                    AddStaffRequest addStaffRequest = new AddStaffRequest();

                    addStaffRequest.setName(staffname.getText().toString());
                    addStaffRequest.setShiftDayFrom(spinnershift1.getSelectedItem().toString());
                    addStaffRequest.setShiftDayTo(spinnershift2.getSelectedItem().toString());
                    addStaffRequest.setShiftTimeFrom(spinnertime1.getSelectedItem().toString());
                    addStaffRequest.setShiftTimeTo(spinnertime2.getSelectedItem().toString());
                    addStaffRequest.setStarted(started.getText().toString());
                    addStaffRequest.setSalary(Integer.parseInt(salary.getText().toString()));
                    addStaffRequest.setSalaryType(spinnersal.getSelectedItem().toString());
                    addStaffRequest.setStaff_email(staffemail.getText().toString());
                    addStaffRequest.setStaff_designation(designation.getText().toString());
                    addStaffRequest.setStaff_address(staffaddress.getText().toString());
                    addStaffRequest.setStaff_phonenumber(staffphonenumber.getText().toString());




                    insertstaff.setStaff_name(staffname.getText().toString());
                    insertstaff.setShift_days1(spinnershift1.getSelectedItem().toString());
                    insertstaff.setShift_days2(spinnershift2.getSelectedItem().toString());
                    insertstaff.setShift_time1(spinnertime1.getSelectedItem().toString());
                    insertstaff.setShift_time2(spinnertime2.getSelectedItem().toString());
                    insertstaff.setStaff_startdate(started.getText().toString());
                    insertstaff.setPrice_perhr(salary.getText().toString());
                    insertstaff.setPriceType(spinnersal.getSelectedItem().toString());

                    insertstaff.setStaff_email(staffemail.getText().toString());
                    insertstaff.setStaff_designation(designation.getText().toString());
                    insertstaff.setStaff_address(staffaddress.getText().toString());
                    insertstaff.setStaff_phonenumber(staffphonenumber.getText().toString());

                    dialog.dismiss();
                    progressBar.setVisibility(View.VISIBLE);

                    SessionManager sessionManager = new SessionManager(getActivity());
                    RetrofitApi.getApi().AddStaff(sessionManager.getAuthToken(), addStaffRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            progressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                db.addStaff(insertstaff);
                                StaffFragment.adapt.add(insertstaff);
                                StaffFragment.adapt.notifyDataSetChanged();

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

    }
}
