package com.brocode.miniproject;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RiskMatrixTable extends RiskMatrixFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.riskmatrix_table, container, false);
        buildMatrix(rootView);
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser) {
            Activity a = getActivity();
            if(a != null) a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    void buildMatrix(View root){
        int count=0;
        for(int cell:cells){
            TextView textView_edit=root.findViewById(cell);
            switch(count){
                case 1: textView_edit.setBackgroundColor(Color.parseColor("#01DF01"));
                        textView_edit.setText("Low");
                        break;
                case 2: textView_edit.setBackgroundColor(Color.parseColor("#FFE700"));
                    textView_edit.setText("Medium");
                    break;
                case 3: textView_edit.setBackgroundColor(Color.parseColor("#FF8D00"));
                    textView_edit.setText("High");
                    break;
                case 4: textView_edit.setBackgroundColor(Color.parseColor("#FF0000"));
                    textView_edit.setText("Catastrophic");
                    break;
            }
            count++;
        }
    }
}
