package medicare.sjsu.edu.medicare.db;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import medicare.sjsu.edu.medicare.data.PatientDetails;

/**
 * Created by Shriaithal on 4/11/2018.
 */

public class WriteToDataBaseTask extends AsyncTask<PatientDetails, String, String> {

    Context context;

    public WriteToDataBaseTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(PatientDetails... patientDetails) {
        FirebaseDataOperations firebaseDataOperations = new FirebaseDataOperations();
        firebaseDataOperations.insertPatientDetails(patientDetails[0]);
        return "SUCCESS";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, "Record Inserted Successfully!", Toast.LENGTH_LONG).show();
    }
}
