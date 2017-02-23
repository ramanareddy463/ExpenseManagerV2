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
import android.widget.Spinner;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.utils.Utils;

/**
 * Created by Haresh.Veldurty on 2/22/2017.
 */

public class StaffFragment extends Fragment {
   Button addstaffbtn,cancelstaffdialog,addtanexptoDb;
    Spinner spinnershift1,spinnershift2,spinnertime1,spinnertime2,spinnersal;
    DatabaseHandler db;
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
        addstaffbtn = (Button) rootView.findViewById(R.id.addstaffbtn);
        addstaffbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddStaffDialog();
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

    }
}
