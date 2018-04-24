package medicare.sjsu.edu.medicare;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import medicare.sjsu.edu.medicare.data.PatientDetailLines;
import medicare.sjsu.edu.medicare.data.PatientDetails;
import medicare.sjsu.edu.medicare.db.FirebaseDataOperations;
import medicare.sjsu.edu.medicare.db.GetDataListener;

public class ViewRecordDetailsActivity extends AppCompatActivity {

    FirebaseDataOperations firebaseDataOperations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_record_details);

        String policyNbr = getIntent().getStringExtra("policyNbr");
      //  policyNbr = "9611317679";
        Log.d("MediCare", policyNbr);

        firebaseDataOperations = new FirebaseDataOperations();

        getDataFromFirebase(policyNbr);
    }

    protected void getDataFromFirebase(String policyNbr) {
        firebaseDataOperations.getPatientDetails(policyNbr, new GetDataListener() {
            @Override
            public void onStart() {
                Log.d("Medicare", "Firebase Data Fetch Started");
            }

            @Override
            public void onSuccess(PatientDetails patientDetails) {
                showDataOnUI(patientDetails);
            }

            @Override
            public void onFailure() {
                Log.e("MediCare", "Error fetching the data");
            }
        });
    }

    protected void showDataOnUI(final PatientDetails patientDetails) {
        Log.d("Medicare", "yes");

        Button viewHistory = findViewById(R.id.viewHistory);
        viewHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewPatientHistory.class);
                intent.putExtra("patientHistory", patientDetails);
                startActivity(intent);
            }
        });

        Button addComment = findViewById(R.id.addComment);
        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView policyTextView = findViewById(R.id.policyNbr);
                EditText comment = findViewById(R.id.comment);
                List<PatientDetailLines> patientDetailLinesList =  patientDetails.getPatientDetails();
                if(patientDetailLinesList == null || patientDetailLinesList.isEmpty()) {
                    patientDetailLinesList = new ArrayList<>();
                }

                PatientDetailLines patientDetailLines = new PatientDetailLines();
                patientDetailLines.setComments(comment.getText().toString());
                patientDetailLines.setJoinDate(new Date());
                patientDetailLinesList.add(patientDetailLines);
                patientDetails.setPatientDetails(patientDetailLinesList);
                firebaseDataOperations.updateRecord(patientDetails);

                Toast.makeText(getBaseContext(), "Comment added successfully!!", Toast.LENGTH_LONG).show();
                comment.setText("");
            }
        });

        TextView policyTextView = findViewById(R.id.policyNbr);
        policyTextView.setText(patientDetails.getPolicyNbr());

        TextView firstName = findViewById(R.id.firstNameText);
        firstName.setText(patientDetails.getFirstName());

        TextView lastName = findViewById(R.id.lastNameText);
        lastName.setText(patientDetails.getLastName());

        TextView age = findViewById(R.id.ageText);
        age.setText(patientDetails.getAge().toString());

        TextView height = findViewById(R.id.heightText);
        height.setText(patientDetails.getHeight().toString());

        TextView weight = findViewById(R.id.weightText);
        weight.setText(patientDetails.getWeight().toString());

        TextView sex = findViewById(R.id.sextText);
        sex.setText(patientDetails.getSex());
    }

}
