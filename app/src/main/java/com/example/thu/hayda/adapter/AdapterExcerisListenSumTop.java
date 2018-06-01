package com.example.thu.hayda.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thu.hayda.Class_ExerciesListen;
import com.example.thu.hayda.Class_ExerciesListen_Level;
import com.example.thu.hayda.R;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class AdapterExcerisListenSumTop extends BaseAdapter {
    Activity context;
    ArrayList<Class_ExerciesListen> list;
    TextToSpeech textToSpeech;

    public AdapterExcerisListenSumTop(Activity context, ArrayList<Class_ExerciesListen> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.item_exerciessumlisten_level_three, null);


        final TextView txt_RadioOneLevel = row.findViewById(R.id.txt_RadioOneLevel);
        final TextView txt_RadioTwolevel = row.findViewById(R.id.txt_RadioTwolevel);
        final TextView txt_RadioThreeLevel = row.findViewById(R.id.txt_RadioThreeLevel);
        ImageButton img_VolumLevel = row.findViewById(R.id.img_VolumLevel);
        RadioButton RdbOneLevel = row.findViewById(R.id.RdbOneLevel);
        RadioButton RdbTwoLevel = row.findViewById(R.id.RdbTwoLevel);
        RadioButton RdbThreeLevel = row.findViewById(R.id.RdbThreeLevel);
        RadioGroup Radio_RG_Level = row.findViewById(R.id.Radio_RG_Level);


        final Class_ExerciesListen class_exerciesListen = list.get(position);
        txt_RadioOneLevel.setText(class_exerciesListen.getAnswer_True());
        txt_RadioTwolevel.setText(class_exerciesListen.getAnswer_false());
        txt_RadioThreeLevel.setText(class_exerciesListen.getAnswer_three());
        Random r = new Random();
        final int resultRandom = (r.nextInt(6) + 1);  // max: 13, min 0. (max-min+1)+min
        img_VolumLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status == TextToSpeech.SUCCESS) {
                            int result = textToSpeech.setLanguage(Locale.ENGLISH);
//
//                            Toast.makeText(context, resultRandom+"", Toast.LENGTH_SHORT).show();
                            textToSpeech.setPitch(0.6f);
                            textToSpeech.setSpeechRate(1.0f);
                            //2
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                                textToSpeech.speak(txt_RadioOne.getText().toString(), textToSpeech.QUEUE_FLUSH, null, null);
                                if (resultRandom == 1 || resultRandom==2) {
                                    textToSpeech.speak(txt_RadioOneLevel.getText().toString(), textToSpeech.QUEUE_FLUSH, null, null);
                                } else
                                {
                                    if(resultRandom == 3 || resultRandom==4)
                                    {
                                        textToSpeech.speak(txt_RadioTwolevel.getText().toString(), textToSpeech.QUEUE_FLUSH, null, null);
                                    }
                                    else
                                    {
                                        textToSpeech.speak(txt_RadioThreeLevel.getText().toString(), textToSpeech.QUEUE_FLUSH, null, null);
                                    }
                                }

                            } else
                                textToSpeech.speak(txt_RadioOneLevel.getText().toString(), textToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
            }
        });

        Radio_RG_Level.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.RdbOneLevel:
                        Toast.makeText(context, "Dung roi", Toast.LENGTH_SHORT).show();
                        if (resultRandom == 1 ||resultRandom==2) {
                            Toast.makeText(context, "Dung roi", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(context, "Sai roi", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.RdbTwoLevel:
                        if (resultRandom == 3||resultRandom==4) {
                            Toast.makeText(context, "Dung roi", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(context, "Sai roi", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.RdbThreeLevel:
                        if (resultRandom == 5 || resultRandom == 6) {
                            Toast.makeText(context, "Dung roi", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(context, "Sai roi", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        return row;
    }
}
