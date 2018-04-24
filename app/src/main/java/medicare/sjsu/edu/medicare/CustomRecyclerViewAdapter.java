package medicare.sjsu.edu.medicare;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import medicare.sjsu.edu.medicare.data.PatientDetailLines;

/**
 * Created by Shriaithal on 4/11/2018.
 */

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.PatientDetailLinesHolder> {

    public static class PatientDetailLinesHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView date;
        TextView comment;

        PatientDetailLinesHolder(View view) {
            super(view);
            cardView = view.findViewById(R.id.cv);
            date = view.findViewById(R.id.date);
            comment = view.findViewById(R.id.comment);
        }
    }

    List<PatientDetailLines> mPatientDetailLines;

    CustomRecyclerViewAdapter(List<PatientDetailLines> patientDetailLinesList) {
        this.mPatientDetailLines = patientDetailLinesList;
    }

    @Override
    public PatientDetailLinesHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.patient_detail_lines, viewGroup, false);
        PatientDetailLinesHolder viewHolder = new PatientDetailLinesHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PatientDetailLinesHolder viewHolder, int position) {
        String dateStr = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            dateStr = formatter.format(mPatientDetailLines.get(position).getJoinDate());
        } catch (Exception exception) {
            Log.e("MediCare", "Exception", exception);
        }
        viewHolder.date.setText(dateStr);
        viewHolder.comment.setText(mPatientDetailLines.get(position).getComments());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mPatientDetailLines.size();
    }
}
