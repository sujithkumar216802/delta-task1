package com.sujithkumar.deltatask1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class GameOver extends Fragment {
    private NViewModel rep;
    private NavController nav;
    private TextView high, curr;
    private Button menu;
    private ImageButton reload;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gameover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rep = new ViewModelProvider(requireActivity()).get(NViewModel.class);
        nav = Navigation.findNavController(view);
        high = view.findViewById(R.id.highscore);
        curr = view.findViewById(R.id.currentscore);
        menu = view.findViewById(R.id.menu);
        reload = view.findViewById(R.id.reload);
        curr.setText(getString(R.string.currentscore, rep.getHackerppcurrentscore()));
        high.setText(getString(R.string.highscore, rep.getHackerpphighscore()));
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep.setHackerppcurrentscore(0);
                nav.navigate(R.id.action_gameOver_to_enter);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rep.setHackerppcurrentscore(0);
                nav.navigate(R.id.action_gameOver_to_menu);
            }
        });

    }

}
