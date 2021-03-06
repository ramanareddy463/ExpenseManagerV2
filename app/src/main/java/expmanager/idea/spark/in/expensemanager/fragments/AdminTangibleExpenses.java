package expmanager.idea.spark.in.expensemanager.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.adapters.MyTanExpAdapter;
import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.model.AddTangibleExpenseRequest;
import expmanager.idea.spark.in.expensemanager.model.TanExpenses;
import expmanager.idea.spark.in.expensemanager.network.RetrofitApi;
import expmanager.idea.spark.in.expensemanager.utils.NetworkUtils;
import expmanager.idea.spark.in.expensemanager.utils.SessionManager;
import expmanager.idea.spark.in.expensemanager.utils.Utils;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Haresh.Veldurty on 3/9/2017.
 */

public class AdminTangibleExpenses extends Fragment {
    Button addtanexpense, canceltandialog, addtanexptoDb;
    EditText priceval, categoryval;
    Spinner whenval;
    DatabaseHandler db;
    ListView tanexplist;
    List<TanExpenses> list, invoice_list;
    public static MyTanExpAdapter adapt;
    private ProgressBar progressBar;
    private ImageView imgArrow;
    RelativeLayout main_layout;
    int flag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public AdminTangibleExpenses() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.admin_tanexpense_layout,
                container, false);
        db = new DatabaseHandler(getActivity());


        Display display = getActivity().getWindowManager().getDefaultDisplay();

        int width = display.getWidth();

        final int convertWidth = (int) (width * 0.65);

        imgArrow = (ImageView) rootView.findViewById(R.id.img_arrow);

        main_layout = (RelativeLayout) rootView.findViewById(R.id.main_layout);

        imgArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag == 0) {
                    main_layout.getLayoutParams().width = convertWidth;
                    main_layout.requestLayout();
                    flag = 1;
                } else {
                    main_layout.getLayoutParams().width = RelativeLayout.LayoutParams.MATCH_PARENT;
                    main_layout.requestLayout();
                    flag = 0;
                }

            }
        });


        addtanexpense = (Button) rootView.findViewById(R.id.addtanexpense);
        tanexplist = (ListView) rootView.findViewById(R.id.tanexplist);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);

        list = db.getAllTanExpenses();
        if (list != null) {
            adapt = new MyTanExpAdapter(getActivity(), R.layout.list_tanexp_item, list);
            tanexplist.setAdapter(adapt);
        }
        addtanexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddtagibleExpDialog1();
            }
        });


        addtanexpense.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                openAddtagibleExpDialog();
                return true;
            }
        });
        return rootView;
    }


    public void openAddtagibleExpDialog() {
        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.addtanexpensedailog_layout);
        canceltandialog = (Button) dialog.findViewById(R.id.canceltandialog);
        addtanexptoDb = (Button) dialog.findViewById(R.id.addtanexptoDb);

        categoryval = (EditText) dialog.findViewById(R.id.categoryval);
        whenval = (Spinner) dialog.findViewById(R.id.whenval);
        priceval = (EditText) dialog.findViewById(R.id.priceval);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.getWindow().setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().getAttributes().width = (int) (Utils.getDeviceMetrics(getActivity()).widthPixels * 0.55);
        ArrayAdapter<CharSequence> adapterper = ArrayAdapter.createFromResource(getActivity(),
                R.array.perWhen, R.layout.simple_spinner_item);
        adapterper.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        whenval.setAdapter(adapterper);
        dialog.show();

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });
        canceltandialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        addtanexptoDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if(!categoryval.getText().toString().isEmpty() && !whenval.getSelectedItem().toString().isEmpty() && !priceval.getText().toString().isEmpty()) {
                    TanExpenses insertall = new TanExpenses(categoryval.getText().toString(), whenval.getSelectedItem().toString(), priceval.getText().toString());
                    db.addTanExpenses(insertall);
                    AdminTangibleExpenses.adapt.add(insertall);
                    AdminTangibleExpenses.adapt.notifyDataSetChanged();
                    dialog.dismiss();
                }*/

                if (!NetworkUtils.getInstance().isNetworkAvailable(getActivity())) {

                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!categoryval.getText().toString().isEmpty() && !whenval.getSelectedItem().toString().isEmpty() && !priceval.getText().toString().isEmpty()) {
                    final TanExpenses insertall = new TanExpenses(categoryval.getText().toString(), whenval.getSelectedItem().toString(), priceval.getText().toString());
                    dialog.dismiss();

                    progressBar.setVisibility(View.VISIBLE);


                    AddTangibleExpenseRequest addTangibleExpenseRequest = new AddTangibleExpenseRequest(insertall.getCategory(), insertall.getWhen(), insertall.getPrice());
                    SessionManager sessionManager = new SessionManager(getActivity());
                    RetrofitApi.getApi().AddTangibleExpense(sessionManager.getAuthToken(), addTangibleExpenseRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            progressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                db.addTanExpenses(insertall);
                                AdminTangibleExpenses.adapt.add(insertall);
                                AdminTangibleExpenses.adapt.notifyDataSetChanged();

                            } else {

                                Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }
        });
    }


    private void openAddtagibleExpDialog1() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.addtanexpensedailog_layout, null);


        alertDialog.setView(dialogView);
        final AlertDialog dialog = alertDialog.create();


        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        canceltandialog = (Button) dialogView.findViewById(R.id.canceltandialog);
        addtanexptoDb = (Button) dialogView.findViewById(R.id.addtanexptoDb);

        categoryval = (EditText) dialogView.findViewById(R.id.categoryval);
        whenval = (Spinner) dialogView.findViewById(R.id.whenval);
        priceval = (EditText) dialogView.findViewById(R.id.priceval);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.getWindow().setAttributes(lp);

        ArrayAdapter<CharSequence> adapterper = ArrayAdapter.createFromResource(getActivity(),
                R.array.perWhen, R.layout.simple_spinner_item);
        adapterper.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        whenval.setAdapter(adapterper);


        canceltandialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        addtanexptoDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if(!categoryval.getText().toString().isEmpty() && !whenval.getSelectedItem().toString().isEmpty() && !priceval.getText().toString().isEmpty()) {
                    TanExpenses insertall = new TanExpenses(categoryval.getText().toString(), whenval.getSelectedItem().toString(), priceval.getText().toString());
                    db.addTanExpenses(insertall);
                    AdminTangibleExpenses.adapt.add(insertall);
                    AdminTangibleExpenses.adapt.notifyDataSetChanged();
                    dialog.dismiss();
                }*/

                if (!NetworkUtils.getInstance().isNetworkAvailable(getActivity())) {

                    Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!categoryval.getText().toString().isEmpty() && !whenval.getSelectedItem().toString().isEmpty() && !priceval.getText().toString().isEmpty()) {
                    final TanExpenses insertall = new TanExpenses(categoryval.getText().toString(), whenval.getSelectedItem().toString(), priceval.getText().toString());
                    dialog.cancel();

                    progressBar.setVisibility(View.VISIBLE);


                    AddTangibleExpenseRequest addTangibleExpenseRequest = new AddTangibleExpenseRequest(insertall.getCategory(), insertall.getWhen(), insertall.getPrice());
                    SessionManager sessionManager = new SessionManager(getActivity());
                    RetrofitApi.getApi().AddTangibleExpense(sessionManager.getAuthToken(), addTangibleExpenseRequest).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            progressBar.setVisibility(View.GONE);

                            if (response.isSuccessful()) {

                                db.addTanExpenses(insertall);
                                AdminTangibleExpenses.adapt.add(insertall);
                                AdminTangibleExpenses.adapt.notifyDataSetChanged();

                            } else {

                                Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            progressBar.setVisibility(View.GONE);

                            Toast.makeText(getActivity(), "Oops something went wrong", Toast.LENGTH_SHORT).show();

                        }
                    });


                }


            }
        });


        dialog.getWindow().getAttributes().width = (int) (Utils.getDeviceMetrics(getActivity()).widthPixels * 0.55);
        dialog.show();


    }


}
