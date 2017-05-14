package com.riqthen.fragment.four;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riqthen.fragment.R;


public class BFragment extends Fragment {
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, null);
        textView = (TextView) view.findViewById(R.id.tv);
        return view;
    }

    public void changeText(String value) {
        textView.setText(value);
    }
}
