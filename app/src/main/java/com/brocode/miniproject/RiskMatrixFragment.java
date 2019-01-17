package com.brocode.miniproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;


public class RiskMatrixFragment extends Fragment {

    static int cells[]={R.id.cell1, R.id.cell2, R.id.cell3,
            R.id.cell4, R.id.cell5, R.id.cell6,
            R.id.cell7, R.id.cell8, R.id.cell9,
            R.id.cell10, R.id.cell11, R.id.cell12,
            R.id.cell13, R.id.cell14, R.id.cell15};

    static int risks[]={R.id.risk1, R.id.risk2, R.id.risk3,
                        R.id.risk4, R.id.risk5};

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riskmatrix, container, false);
        setProgressBarValue(view);
        onCardTap(view, container);
        return view;
    }

    void onCardTap(final View view, final ViewGroup cont){
        CardView card_view = view.findViewById(R.id.cardView); // creating a CardView and assigning a value.

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), riskmatrixtable2.class);
                startActivity(intent);

            }
        });
    }

    void setProgressBarValue(View root){
        int sum=0;
        int min=0;
        int max=4*15;
        for(int data:jsonReader.matrix_data){
            sum=sum+data;
        }
        ProgressBar bar= root.findViewById(R.id.progressBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            bar.setMin(min);
            bar.setMax(max);
            bar.setProgress(sum, true);
        } else {
            bar.setMax(max - min);
            bar.setProgress(sum - min);
        }
    }
}
