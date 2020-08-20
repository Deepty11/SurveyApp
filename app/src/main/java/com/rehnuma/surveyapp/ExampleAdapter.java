package com.rehnuma.surveyapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rehnuma.surveyapp.model.SurveyQuestions;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleHolder> {
    private static ArrayList<SurveyQuestions> mArray;


    public static class ExampleHolder extends RecyclerView.ViewHolder{
        TextView timestamp;
        TextView question1;
        TextView question2;
        TextView question3;
        TextView question4;
        TextView question5;
        LinearLayout expandableLayout;

        public ExampleHolder(@NonNull View itemView) {
            super(itemView);

            timestamp=itemView.findViewById(R.id.timestamp);
            question1=itemView.findViewById(R.id.q1);
            question2=itemView.findViewById(R.id.q2);
            question3=itemView.findViewById(R.id.q3);
            question4=itemView.findViewById(R.id.q4);
            question5=itemView.findViewById(R.id.q5);
            //expandableLayout=itemView.findViewById(R.id.expandableLayout);
            timestamp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SurveyQuestions sq=mArray.get(getAdapterPosition());
                    sq.setExpanded(!sq.isExpanded());

                }
            });
        }
    }

    public ExampleAdapter(ArrayList<SurveyQuestions> result){
        mArray=result;

    }

    @NonNull
    @Override
    public ExampleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.test_card,parent,false);
        ExampleHolder eh=new ExampleHolder(v);
        return eh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleHolder holder, int position) {
        SurveyQuestions sq=mArray.get(position);
        holder.timestamp.setText(sq.getTimestamp());
        holder.question1.setText(sq.getQuestion1());
        holder.question2.setText(sq.getQuestion2());
        holder.question3.setText(sq.getQuestion3());
        holder.question4.setText(sq.getQuestion4());
        holder.question5.setText(sq.getQuestion5());
        boolean isexpanded=mArray.get(position).isExpanded();
        //holder.expandableLayout.setVisibility(isexpanded ? View.VISIBLE:View.GONE);

    }

    @Override
    public int getItemCount() {
        return mArray.size();
    }


}
