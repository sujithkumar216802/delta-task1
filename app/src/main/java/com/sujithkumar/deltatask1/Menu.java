package com.sujithkumar.deltatask1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class Menu extends Fragment {
    private NavController nav;
    private Button normal, hacker, hackerpp;
    private NViewModel rep;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rep = new ViewModelProvider(requireActivity()).get(NViewModel.class);
        rep.setHackerppcurrentscore(0);
        rep.setHackercurrentscore(0);
        nav = Navigation.findNavController(view);
        hackerpp = view.findViewById(R.id.Hackerppbutton);
        hacker = view.findViewById(R.id.HackerButton);
        normal = view.findViewById(R.id.normalButton);
        normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep.setState(1);
                nav.navigate(R.id.action_menu_to_enter);
            }
        });
        hacker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep.setState(2);
                nav.navigate(R.id.action_menu_to_enter);
            }
        });
        hackerpp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep.setState(3);
                nav.navigate(R.id.action_menu_to_enter);
            }
        });

    }

}
