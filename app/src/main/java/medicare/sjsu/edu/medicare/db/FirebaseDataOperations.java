package medicare.sjsu.edu.medicare.db;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import medicare.sjsu.edu.medicare.data.PatientDetails;

/**
 * Created by Shriaithal on 4/10/2018.
 */

public class FirebaseDataOperations {

    private DatabaseReference databaseReference;

    public FirebaseDataOperations() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    public void insertPatientDetails(PatientDetails patientDetails) {
        databaseReference.child("patientDetails").child(patientDetails.getPolicyNbr()).setValue(patientDetails);
    }

    public void getPatientDetails(final String policyNbr, final GetDataListener getDataListener) {
        DatabaseReference patientDetailsRef = databaseReference.child("patientDetails");
        patientDetailsRef.orderByChild(policyNbr).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean found = false;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    PatientDetails patientDetails = snapshot.getValue(PatientDetails.class);
                    if(patientDetails.getPolicyNbr().equals(policyNbr)) {
                        found = true;
                        getDataListener.onSuccess(patientDetails); //call anonymous class success method to update UI
                    }
                }
                if(!found) {
                    getDataListener.onFailure();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void updateRecord(PatientDetails patientDetails) {
        databaseReference.child("patientDetails").child(patientDetails.getPolicyNbr()).setValue(patientDetails);
    }
}
