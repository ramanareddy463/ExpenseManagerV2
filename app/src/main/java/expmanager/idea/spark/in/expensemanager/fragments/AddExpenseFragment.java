package expmanager.idea.spark.in.expensemanager.fragments;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import expmanager.idea.spark.in.expensemanager.R;



/**
 * Created by Ramana.Reddy on 2/28/2017.
 */

public class AddExpenseFragment extends Fragment {

    private ImageView imageRescan;
    private Uri outputFileUri;
    private static final int PHOTO_REQUEST_CODE = 1;

    private static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/ExpenseManager/";
    private static final String TESSDATA = "tessdata";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.add_expense,
                container, false);

        imageRescan = (ImageView) rootView.findViewById(R.id.img_rescan);

        imageRescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startCameraActivity();
            }
        });


        return rootView;
    }

    /**
     * to get high resolution image from camera
     */
    private void startCameraActivity() {
        try {
            String IMGS_PATH = Environment.getExternalStorageDirectory().toString() + "/ExpenseManager/imgs";
            prepareDirectory(IMGS_PATH);

            String img_path = IMGS_PATH + "/ocr.jpg";

            outputFileUri = Uri.fromFile(new File(img_path));

            final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);

            if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, PHOTO_REQUEST_CODE);
            }
        } catch (Exception e) {
            Log.e("Add Expense", e.getMessage());
        }
    }


    private void prepareDirectory(String path) {

        File dir = new File(path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("Add Expense", "ERROR: Creation of directory " + path + " failed, check does Android Manifest have permission to write to external storage.");
            }
        } else {
            Log.i("Add Expense", "Created directory " + path);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        //making photo
        if (requestCode == PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            doOCR();
        } else {
            Toast.makeText(getActivity(), "ERROR: Image was not obtained.", Toast.LENGTH_SHORT).show();
        }
    }

    private void doOCR() {
        prepareTesseract();
        startOCR(outputFileUri);
    }

    private void prepareTesseract() {
        try {
            prepareDirectory(DATA_PATH + TESSDATA);
        } catch (Exception e) {
            e.printStackTrace();
        }

       // copyTessDataFiles(TESSDATA);
    }

    private void startOCR(Uri imgUri) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
            Bitmap bitmap = BitmapFactory.decodeFile(imgUri.getPath(), options);

           // String result = extractText(bitmap);

           // Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

            //textView.setText(result);

        } catch (Exception e) {
            //Log.e(TAG, e.getMessage());
        }
    }

}
