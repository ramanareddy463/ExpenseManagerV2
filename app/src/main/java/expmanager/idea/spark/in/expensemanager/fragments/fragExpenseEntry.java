package expmanager.idea.spark.in.expensemanager.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import expmanager.idea.spark.in.expensemanager.R;
import expmanager.idea.spark.in.expensemanager.adapters.expenseAdapter;
import expmanager.idea.spark.in.expensemanager.adapters.expenseListAdapter;
import expmanager.idea.spark.in.expensemanager.database.DatabaseHandler;
import expmanager.idea.spark.in.expensemanager.model.Expense;
import expmanager.idea.spark.in.expensemanager.utils.Utils;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link fragExpenseEntry.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link fragExpenseEntry#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragExpenseEntry extends Fragment implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private double mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;


    private OnFragmentInteractionListener mListener;

    //Add Expense Form
    AutoCompleteTextView autoCatId;
    AutoCompleteTextView autoInvId;
    EditText expDesc;
    EditText expDate;
    EditText expUnit;
    EditText expAmt;
    ListView lstExpItems;
    Button lblinvTotAmt,lblExpTotAmt;
    Button imginvprev;
    DatabaseHandler myDbHelper;
    Button btnExpAdd,btnExpSave,btnExpCancel;
    boolean isEditFlag;

    Spinner spnrecursivetype;
    Double invAmt,expAmtSofar;
    int weekindex;
    int editedExpId;
    LinearLayout lnrExpHeader;
    private String[] recursiveType = {"Daily", "Weekly", "Monthly", "Monthly - First day", "Monthly - Last day"};

    Dialog dialog;
    //Expense Listing
    LinearLayout lnrexplistingTotlay, lnrexplistinglay, lnrTotExpLay, lnrRecursiveLayout, lnrweekviewmain;
    LinearLayout[] lnrCatWiseExpLay;
    TextView[] lblexpcategory, lblexpcategorytotal, lblcatexpitemtot, lvweekexpdrp;
    LinearLayout[] lstexpList;
    private double totExpPerWeek;
    private int totCatItem, totExpItem;
    LinearLayout.LayoutParams mainLayParams;

    public fragExpenseEntry() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragExpenseEntry.
     */
    // TODO: Rename and change types and number of parameters
    public static fragExpenseEntry newInstance(int param1, double param2, String param3, String param4,String param5) {
        fragExpenseEntry fragment = new fragExpenseEntry();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putDouble(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getDouble(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_expense_entry, container, false);

        mainLayParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLayParams.setMargins(Utils.dpConverter(10, getContext()), Utils.dpConverter(10, getContext()), Utils.dpConverter(10, getContext()), Utils.dpConverter(10, getContext()));

        weekindex = mParam1;

        myDbHelper = new DatabaseHandler(getContext());

        myDbHelper.openConnection();
        myDbHelper.deleteExpenseIsnotSaved();
        myDbHelper.closeConnection();

        lnrExpHeader = (LinearLayout)v.findViewById(R.id.lnrExpHeader);
        //Add Expense
        autoCatId = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteCatId);
        refreshAdapter();
        autoInvId = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteInvId);

        invAmt = mParam2;
        autoInvId.setInputType(InputType.TYPE_NULL);
        autoInvId.setText(mParam3);

        expUnit = (EditText) v.findViewById(R.id.exp_unit);
        expAmt = (EditText) v.findViewById(R.id.exp_amount);
        expAmtSofar =0.0;
        expDate = (EditText) v.findViewById(R.id.exp_date);
        expDate.setInputType(InputType.TYPE_NULL);
        expDate.setText(mParam4);

        expDesc = (EditText) v.findViewById(R.id.exp_descrip);
        lnrRecursiveLayout = (LinearLayout) v.findViewById(R.id.lnrRecursiveLayout);

        lstExpItems = (ListView) v.findViewById(R.id.lstExpItems);
        lstExpItems.setOnItemClickListener(this);
        lblinvTotAmt = (Button) v.findViewById(R.id.lblinvTotAmt);
        lblExpTotAmt = (Button) v.findViewById(R.id.lblExpTotAmt);
        imginvprev = (Button) v.findViewById(R.id.lblpreview);

        imginvprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(mParam5)), "image/*");
                startActivity(intent);
            }
        });

        loadDetails();

        spnrecursivetype = (Spinner) v.findViewById(R.id.spnrecursivetype);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, recursiveType);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnrecursivetype.setAdapter(adapter_state);
        spnrecursivetype.setOnItemSelectedListener(this);


        btnExpAdd = (Button) v.findViewById(R.id.btnexpAdd);
        btnExpAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (autoCatId.getText().toString().length() > 0 && expUnit.getText().length() > 0 && expAmt.getText().length() > 0 && expDate.getText().length() > 0 && expDesc.getText().length() > 0) {

                    myDbHelper.openConnection();
                    int catId = myDbHelper.getCatId(autoCatId.getText().toString());
                    if (catId == 0) {
                        myDbHelper.insetCategory(autoCatId.getText().toString(), "",0);
                        catId = myDbHelper.getCatId(autoCatId.getText().toString());
                    }
                    int invId=0;
                    invId = myDbHelper.getInvId(autoInvId.getText().toString());
                    if (invId == 0) {
                        Toast.makeText(getContext(), "Invalid Invoice No", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    expAmtSofar = expAmtSofar + Double.parseDouble(expAmt.getText().toString());

                    if(!isEditFlag){
                        myDbHelper.insetExpense(new Expense(expDate.getText().toString(), expDesc.getText().toString(), catId, invId, Integer.parseInt(expUnit.getText().toString()), 0, 0, Double.parseDouble(expAmt.getText().toString()), 0, "",0,weekindex,0));
                    }else{
                        myDbHelper.updateExpense(new Expense(expDate.getText().toString(), expDesc.getText().toString(), catId, invId, Integer.parseInt(expUnit.getText().toString()), 0, 0, Double.parseDouble(expAmt.getText().toString()), 0, "",0,weekindex,editedExpId));
                        isEditFlag =false;
                    }

                    /*if (chkexpIsRecursive.isChecked()) {
                        myDbHelper.insetRecursiveExpense(myDbHelper.getMaxExpenseId(), spnrecursivetype.getSelectedItem().toString(), Util.getCurrentDate(), "", Util.getCurrentDate(), SharedPref.getCurUserId(getContext()));
                    }*/
                    myDbHelper.closeConnection();

                    refreshAdapter();
                    loadExpenseItems();

                    btnExpAdd.setText("Add");
                    expDesc.setText("");
                    expAmt.setText("");
                    expUnit.setText("");
                    autoCatId.setText("");

                    Toast.makeText(getContext(), "Expense Details Added Successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Please enter expense details", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnExpSave = (Button) v.findViewById(R.id.btnexpSave);
        btnExpSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDbHelper.openConnection();
                final double expTotSoFar = myDbHelper.getUnSaveExpAmt();
                myDbHelper.closeConnection();

                if(expTotSoFar<invAmt){
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                    alertDialog.setTitle("Confirm Cancel...");
                    alertDialog.setMessage("Invoice amount lesser then Expenses, Add remaining!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    alertDialog.show();
                    return;
                }

                if(expTotSoFar >invAmt){
                    AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                    ad.setTitle("Confirmation")
                            .setMessage("Expense Amount Exceeds Invoice, Make remaining as discount?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    loadPreview(expTotSoFar-invAmt);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create();
                    ad.show();
                    return;
                }

                loadPreview(0);
            }
        });

        btnExpCancel = (Button) v.findViewById(R.id.btnexpCancel);
        btnExpCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Confirm Cancel...");
                alertDialog.setMessage("Are you want cancel the added expenses?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        myDbHelper.openConnection();
                        myDbHelper.deleteExpenseIsnotSaved();
                        myDbHelper.closeConnection();
                       // mListener.openWeekView();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();

            }
        });

        // Inflate the layout for this fragment
        return v;
    }

    private void loadPreview(double disc){
        // custom dialog
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.exp_preview);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.getWindow().setAttributes(lp);

        lnrexplistinglay = (LinearLayout)dialog.findViewById(R.id.lnrexplistinglay);

        initializeControls();
        loadExpDetails();

        Button btnsave = (Button)dialog.findViewById(R.id.btnsubmit);
        TextView lblinvid = (TextView)dialog.findViewById(R.id.lblinvid);
        lblinvid.setText("Invoice Name: " + mParam3);
        TextView lblinvdate = (TextView)dialog.findViewById(R.id.lblinvdate);
        lblinvdate.setText("Invoice Date: " + mParam4);
        TextView lblinvdisc = (TextView)dialog.findViewById(R.id.lblinvdisc);
        lblinvdisc.setText("Invoice Discount: " + disc);

        ImageView prev = (ImageView)dialog.findViewById(R.id.imgprev);
        File imgFile = new File(mParam5);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            prev.setImageBitmap(myBitmap);
        }
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(mParam5)), "image/*");
                startActivity(intent);
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDbHelper.openConnection();
                myDbHelper.updateExpenseSaved();
                myDbHelper.closeConnection();

                expAmtSofar =0.0;
                Toast.makeText(getContext(), "All Expense Saved Successfully", Toast.LENGTH_SHORT).show();
                //mListener.openWeekView();
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void initializeControls() {
        myDbHelper.openConnection();
        int[] catIdArray = myDbHelper.getExpCatId();
        myDbHelper.closeConnection();

        lnrCatWiseExpLay = new LinearLayout[catIdArray.length];
        lblexpcategory = new TextView[catIdArray.length];
        lblexpcategorytotal = new TextView[catIdArray.length];
        lblcatexpitemtot = new TextView[catIdArray.length];
        lvweekexpdrp = new TextView[catIdArray.length];
        lstexpList = new LinearLayout[catIdArray.length];
    }

    private void loadExpDetails() {

        if (myDbHelper != null) {
            totExpItem = 0;
            totCatItem = 0;
            totExpPerWeek = 0;

            lnrexplistinglay.removeAllViews();
            myDbHelper.openConnection();

            final int[] catIdArray = myDbHelper.getExpCatId();
            double catWiseTotExp;

            for (int loop1 = 0; loop1 < catIdArray.length; loop1++) {
                Log.i("WeekView", "Cat ID:" + catIdArray[loop1]);

                final int index = loop1;


                catWiseTotExp = 0;

                ArrayList<Expense> expList = new ArrayList<Expense>();
                expList = myDbHelper.getExpensesload(catIdArray[loop1]);
                if (expList.size() > 0) {

                    totCatItem = totCatItem + 1;
                    totExpItem = totExpItem + expList.size();

                    for (int loop2 = 0; loop2 < expList.size(); loop2++) {
                        totExpPerWeek = totExpPerWeek + expList.get(loop2).getExpAmt();
                        catWiseTotExp = catWiseTotExp + expList.get(loop2).getExpAmt();
                    }

                    lnrCatWiseExpLay[loop1] = (LinearLayout) getActivity().getLayoutInflater().inflate(R.layout.lay_cat_wise_expense, null);
                    lnrCatWiseExpLay[loop1].setLayoutParams(mainLayParams);

                    lblexpcategory[loop1] = (TextView) lnrCatWiseExpLay[loop1].findViewById(R.id.lblexpcategory);
                    lblexpcategorytotal[loop1] = (TextView) lnrCatWiseExpLay[loop1].findViewById(R.id.lblexpcategorytotal);
                    lblcatexpitemtot[loop1] = (TextView) lnrCatWiseExpLay[loop1].findViewById(R.id.lblcatexpitemtot);
                    lstexpList[loop1] = (LinearLayout) lnrCatWiseExpLay[loop1].findViewById(R.id.lstexplist);
                    lvweekexpdrp[loop1] = (TextView) lnrCatWiseExpLay[loop1].findViewById(R.id.lvweekexp1drp);

                    lvweekexpdrp[loop1].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (lstexpList[index].getVisibility() == View.VISIBLE) {
                                lstexpList[index].setVisibility(View.GONE);
                                lvweekexpdrp[index].setBackground(getResources().getDrawable(R.drawable.ic_expand_more_white_24dp));
                            } else {
                                lvweekexpdrp[index].setBackground(getResources().getDrawable(R.drawable.ic_expand_less_white_24dp));
                                lstexpList[index].setVisibility(View.VISIBLE);
                            }

                        }
                    });

                    expenseAdapter expAd = new expenseAdapter(getContext(), expList);

                    final int adapterCount = expAd.getCount();

                    for (int i = 0; i < adapterCount; i++) {
                        View item = expAd.getView(i, null, null);
                        lstexpList[loop1].addView(item);
                    }
                    lstexpList[loop1].setVisibility(View.GONE);

                    lblcatexpitemtot[loop1].setText(expList.size() + " Items");
                    lblexpcategorytotal[loop1].setText("$" + String.format("%.2f", catWiseTotExp));

                    String catName = myDbHelper.getCatName(catIdArray[loop1]);
                    lblexpcategory[loop1].setText(catName);

                    lnrCatWiseExpLay[loop1].setBackgroundColor(getResources().getColor(R.color.colorAccent));

                    lnrexplistinglay.addView(lnrCatWiseExpLay[loop1]);

                }
            }
            myDbHelper.closeConnection();
        }
    }


    private void loadDetails(){
        lnrExpHeader.setVisibility(View.VISIBLE);
/*
        File imgFile = new File(mParam5);
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imginvprev.setImageBitmap(myBitmap);
        }
*/
        lblinvTotAmt.setText("Invoice:" + invAmt);
        lblExpTotAmt.setText("Expense:" + expAmtSofar);
    }

    private void loadExpenseItems(){
        editedExpId = 0;
        lblExpTotAmt.setText("Expense:" + expAmtSofar);
        myDbHelper.openConnection();
        expenseListAdapter adapter=new expenseListAdapter(getActivity(), myDbHelper.getExpenses(0),lstExpItems);
        lstExpItems.setAdapter(adapter);
        if(adapter.getCount()>0){
            lnrExpHeader.setVisibility(View.VISIBLE);
        }
        myDbHelper.closeConnection();
    }

    public void editExpense(int id){
        if(id<=0){
            return;
        }
        myDbHelper.openConnection();
        ArrayList<Expense> lv= new ArrayList<Expense>();
        lv=myDbHelper.getExpensesEdit(id);
        myDbHelper.closeConnection();

        if(lv.size()>0){
            expDesc.setText(lv.get(0).getExpDescription());
            expAmt.setText("" + lv.get(0).getExpAmt());
            expAmtSofar = expAmtSofar - lv.get(0).getExpAmt();
            expUnit.setText("" + lv.get(0).getExpUnit());
            myDbHelper.openConnection();
            autoCatId.setText(myDbHelper.getCatName(lv.get(0).getExpCatId()));
            myDbHelper.closeConnection();
            btnExpAdd.setText("Update");
            isEditFlag =true;
            editedExpId = id;
        }
    }

    public void deleteExpense(int id){
        if(id>0){
            myDbHelper.openConnection();
            ArrayList<Expense> lv= new ArrayList<Expense>();
            lv=myDbHelper.getExpensesEdit(id);
            myDbHelper.closeConnection();

            myDbHelper.openConnection();
            myDbHelper.deleteExpense(id);
            expAmtSofar = expAmtSofar - lv.get(0).getExpAmt();
            myDbHelper.closeConnection();
            loadExpenseItems();
            loadDetails();
            Toast.makeText(getContext(), "Expense deleted Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    private void refreshAdapter() {
        if (myDbHelper != null) {
            myDbHelper.openConnection();
            String[] catnameArr = myDbHelper.getCatname();
            myDbHelper.closeConnection();

            ArrayAdapter<String> catadapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, catnameArr);
            autoCatId.setAdapter(catadapter);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(view.getId()==R.id.btnexpedit){
            editExpense((int) id);
        }else if (view.getId()==R.id.btnexpdelete){
            deleteExpense((int) id);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
       // void openWeekView();
    }
}
