package com.sujithkumar.deltatask1;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;


public class Normal extends Fragment {

    private RadioButton option1, option2, option3;
    private Integer ans;
    private NViewModel rep;
    private Button next, menu;
    private TextView show;
    private NavController nav;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.normal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiate(view);
        if (rep.isNormaldone()) {
            result(rep.getSelected());
        }
    }


    private void result(int i) {
        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        switch (ans) {
            case 1:
                option1.setBackgroundColor(Color.argb(128, 0, 255, 0));
                break;
            case 2:
                option2.setBackgroundColor(Color.argb(128, 0, 255, 0));
                break;
            case 3:
                option3.setBackgroundColor(Color.argb(128, 0, 255, 0));
                break;
        }
        if (ans != i) {
            switch (i) {
                case 1:
                    option1.setBackgroundColor(Color.argb(128, 255, 0, 0));
                    break;
                case 2:
                    option2.setBackgroundColor(Color.argb(128, 255, 0, 0));
                    break;
                case 3:
                    option3.setBackgroundColor(Color.argb(128, 255, 0, 0));
                    break;

            }
            next.setText(R.string.retry);
        }
        rep.setSelected(i);
        rep.setNormaldone(true);
        end();
    }


    private void end() {

        menu.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        menu.setEnabled(true);
        next.setEnabled(true);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_normal_to_menu);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_normal_to_enter);
            }
        });
    }


    private void initiate(View view) {
        rep = new ViewModelProvider(requireActivity()).get(NViewModel.class);
        nav = Navigation.findNavController(view);
        show = view.findViewById(R.id.textView);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        menu = view.findViewById(R.id.menu);
        next = view.findViewById(R.id.next);
        show.setText(String.valueOf(rep.getEnteredno()));
        option1.setText(String.valueOf(rep.getOption1()));
        option2.setText(String.valueOf(rep.getOption2()));
        option3.setText(String.valueOf(rep.getOption3()));
        ans = rep.getCorrect();
        menu.setEnabled(false);
        menu.setVisibility(View.GONE);
        next.setEnabled(false);
        next.setVisibility(View.GONE);
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result(1);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result(2);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result(3);
            }
        });
    }


}
