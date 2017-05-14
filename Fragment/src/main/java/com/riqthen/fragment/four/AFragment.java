package com.riqthen.fragment.four;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.riqthen.fragment.R;

public class AFragment extends Fragment implements View.OnClickListener {
    private AListener aListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, null);
//        View view = inflater.inflate(R.layout.fragment_a, container, false);
        Button buttonA = (Button) view.findViewById(R.id.btn_a);
        Button buttonB = (Button) view.findViewById(R.id.btn_b);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        aListener = (AListener) context;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_a:
                aListener.changeValue("news");
                break;
            case R.id.btn_b:
                aListener.changeValue("music");
                break;
        }
    }


    public interface AListener {
        void changeValue(String value);
    }
}
