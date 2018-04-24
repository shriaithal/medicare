package medicare.sjsu.edu.medicare.db;

import medicare.sjsu.edu.medicare.data.PatientDetails;

/**
 * Created by Shriaithal on 4/10/2018.
 */

public interface GetDataListener {

    public void onStart();
    public void onSuccess(PatientDetails patientDetails);
    public void onFailure();
}
