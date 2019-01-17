package com.brocode.miniproject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class riskmatrixtable2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildMatrix();
        setContentView(R.layout.riskmatrix_table);
    }

    void buildMatrix(){
        int count=0;
        for(int cell:RiskMatrixFragment.cells){
            TextView textView_edit = findViewById(cell);
            switch(jsonReader.matrix_data[count]){
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

        count=0;

        for(int risks:RiskMatrixFragment.risks) {
            TextView textView_edit=findViewById(risks);
            textView_edit.setText(jsonReader.matrix_risks[count]);
            count++;
        }
    }

}