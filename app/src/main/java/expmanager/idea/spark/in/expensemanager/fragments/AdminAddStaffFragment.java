package expmanager.idea.spark.in.expensemanager.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.adapters.MyStaffDetailsAdapter;
import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.model.Staff;
import expmanager.idea.spark.in.expensemanager.utils.Utils;

/**
 * Created by Haresh.Veldurty on 3/9/2017.
 */

public class AdminAddStaffFragment extends Fragment {
    Button addstaffbtn,cancelstaffdialog,addstafftoDb;
    EditText staffname;
    Spinner spinnershift1,spinnershift2,spinnertime1,spinnertime2,spinnersal;
    DatabaseHandler db;
    ListView stafflist;
    List<Staff> list,staff_list;
    public static MyStaffDetailsAdapter adapt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    public AdminAddStaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.admin_addstaff_layout,
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
        list = db.getAllStaff();
        if(list != null) {
            adapt = new MyStaffDetailsAdapter(getActivity(), R.layout.list_staff_item, list);
            stafflist.setAdapter(adapt);
        }
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
        staffname = (EditText) dialog.findViewById(R.id.staffname);

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
                    Staff insertstaff = new Staff(staffname.getText().toString());
                    db.addStaff(insertstaff);
                    AdminAddStaffFragment.adapt.add(insertstaff);
                    AdminAddStaffFragment.adapt.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });

    }
}
