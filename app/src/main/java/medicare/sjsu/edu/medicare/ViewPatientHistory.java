package medicare.sjsu.edu.medicare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import medicare.sjsu.edu.medicare.data.PatientDetailLines;
import medicare.sjsu.edu.medicare.data.PatientDetails;

public class ViewPatientHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_history);

        PatientDetails patientDetails = (PatientDetails) getIntent().getSerializableExtra("patientHistory");

        showDataOnUI(patientDetails.getPatientDetails());
    }

    protected void showDataOnUI(List<PatientDetailLines> patientDetails) {
        RecyclerView recyclerView =  findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        CustomRecyclerViewAdapter adapter = new CustomRecyclerViewAdapter(patientDetails);
        recyclerView.setAdapter(adapter);
    }
}
