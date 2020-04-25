package com.sujithkumar.deltatask1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import static android.widget.Toast.LENGTH_SHORT;

public class Enter extends Fragment {
    private NavController nav;
    private TextView text, current, high, timer;
    private Button go;
    private NViewModel rep;
    private Integer temp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.enter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiate(view);
    }


    private void initiate(View view) {
        rep = new ViewModelProvider(requireActivity()).get(NViewModel.class);
        nav = Navigation.findNavController(view);
        text = view.findViewById(R.id.input);
        go = view.findViewById(R.id.go);
        current = view.findViewById(R.id.currentscore);
        high = view.findViewById(R.id.highscore);
        timer = view.findViewById(R.id.timer);
        switch (rep.getState()) {
            case 2:
                current.setText(getString(R.string.currentscore, rep.getHackercurrentscore()));
                high.setText(getString(R.string.highscore, rep.getHackerhighscore()));
                break;
            case 3:
                rep.setTimeleft(10000);
                current.setText(getString(R.string.currentscore, rep.getHackerppcurrentscore()));
                high.setText(getString(R.string.highscore, rep.getHackerpphighscore()));
                timer.setVisibility(View.VISIBLE);
                break;
        }
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickgo();
            }
        });
    }

    private void clickgo() {
        try {
            temp = Integer.valueOf((text.getText().toString()));
            if (temp < 3) {
                Toast.makeText(getContext(), getString(R.string.invalidnumber), LENGTH_SHORT).show();
            } else {
                View view = requireActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    assert imm != null;
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }

                rep.setEnteredno(temp);

                switch (rep.getState()) {
                    case 1:
                        rep.setNormaldone(false);
                        nav.navigate(R.id.action_enter_to_normal);
                        break;
                    case 2:
                        rep.setHackerdone(false);
                        nav.navigate(R.id.action_enter_to_hacker);
                        break;
                    case 3:
                        rep.setCancel(false);
                        rep.setHackerppdone(false);
                        nav.navigate(R.id.action_enter_to_hackerpp);
                        break;
                }
            }

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "ENTER A VALID NUMBER", LENGTH_SHORT).show();
        }
    }

}
