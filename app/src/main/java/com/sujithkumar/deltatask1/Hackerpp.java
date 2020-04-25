package com.sujithkumar.deltatask1;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import static android.os.VibrationEffect.DEFAULT_AMPLITUDE;

public class Hackerpp extends Fragment {

    private static NavDestination currentdestination;
    private ConstraintLayout layout;
    private NavController nav;
    private RadioButton option1, option2, option3;
    private Integer ans, currentscore, highscore;
    private NViewModel rep;
    private TextView show, curr, high, clock;
    private boolean pause = false;
    private Button next, menu;
    private Vibrator vib;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.hackerpp, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initiate(view);
        if (rep.isHackerppdone()) {
            result(rep.getSelected());
        }
    }


    @Override
    public void onPause() {
        pause = true;
        super.onPause();
    }

    @Override
    public void onResume() {
        pause = false;
        super.onResume();
        if (!rep.isHackerppdone()) {
            new CountDownTimer(rep.getTimeleft(), 100) {
                public void onFinish() {
                    result(77);
                }

                public void onTick(long millisUntilFinished) {
                    clock.setText("TIMER \n" + millisUntilFinished / 1000);
                    rep.setTimeleft(millisUntilFinished);
                    if (rep.isHackerppdone()) cancel();
                    if (nav.getCurrentDestination() != currentdestination) cancel();
                }
            }.start();
        }

        if (rep.isHackerppdone() && (!rep.isCancel())) {
            result(rep.getSelected());
            new CountDownTimer(rep.getThreeseconddelay(), 100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    rep.setThreeseconddelay(millisUntilFinished);
                    if (nav.getCurrentDestination() != currentdestination || pause)
                        cancel();
                }

                @Override
                public void onFinish() {
                    rep.setThreeseconddelay(3000);
                    rep.setCancel(true);
                    if (nav.getCurrentDestination() == currentdestination)
                        nav.navigate(R.id.action_hackerpp_to_gameOver);
                }
            }.start();
        }
    }

    private void result(int i) {
        rep.setSelected(i);
        rep.setHackerppdone(true);
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

            layout.setBackgroundColor(Color.argb(80, 255, 0, 0));
            if (!rep.isCancel()) {
                if (rep.getThreeseconddelay() == 3000 && nav.getCurrentDestination() == currentdestination)
                    vibrate();

                new CountDownTimer(rep.getThreeseconddelay(), 100) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        rep.setThreeseconddelay(millisUntilFinished);
                        if (nav.getCurrentDestination() != currentdestination) {
                            cancel();
                            rep.setThreeseconddelay(3000);
                        }
                        if (pause || rep.isCancel()) cancel();
                    }

                    @Override
                    public void onFinish() {
                        rep.setThreeseconddelay(3000);
                        rep.setCancel(true);
                        if (nav.getCurrentDestination() == currentdestination)
                            nav.navigate(R.id.action_hackerpp_to_gameOver);
                    }
                }.start();
            } else {
                if (nav.getCurrentDestination() == currentdestination)
                    nav.navigate(R.id.action_hackerpp_to_gameOver);
            }
        } else {
            layout.setBackgroundColor(Color.argb(80, 0, 255, 0));
            currentscore++;
            rep.setHackerppcurrentscore(currentscore);
            curr.setText(getString(R.string.currentscore, currentscore));
            end();
        }
    }


    private void end() {
        menu.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);
        menu.setEnabled(true);
        next.setEnabled(true);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_hackerpp_to_menu);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nav.navigate(R.id.action_hackerpp_to_enter);
            }
        });
    }


    private void initiate(View view) {
        rep = new ViewModelProvider(requireActivity()).get(NViewModel.class);
        vib = (Vibrator) requireActivity().getSystemService(Context.VIBRATOR_SERVICE);
        nav = Navigation.findNavController(view);
        layout = view.findViewById(R.id.layout);
        show = view.findViewById(R.id.textView);
        option1 = view.findViewById(R.id.option1);
        option2 = view.findViewById(R.id.option2);
        option3 = view.findViewById(R.id.option3);
        curr = view.findViewById(R.id.currentscore);
        high = view.findViewById(R.id.highscore);
        clock = view.findViewById(R.id.timer);
        menu = view.findViewById(R.id.menu);
        next = view.findViewById(R.id.next);
        currentscore = rep.getHackerppcurrentscore();
        highscore = rep.getHackerpphighscore();
        curr.setText(getString(R.string.currentscore, currentscore));
        high.setText(getString(R.string.highscore, highscore));
        currentdestination = nav.getCurrentDestination();
        show.setText(String.valueOf(rep.getEnteredno()));
        option1.setText(String.valueOf(rep.getOption1()));
        option2.setText(String.valueOf(rep.getOption2()));
        option3.setText(String.valueOf(rep.getOption3()));
        ans = rep.getCorrect();
        menu.setEnabled(false);
        menu.setVisibility(View.GONE);
        next.setEnabled(false);
        next.setVisibility(View.GONE);
        clock.setText("TIMER \n " + (rep.getTimeleft() / 1000));
        option1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                result(1);
            }
        });
        option2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                result(2);
            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                result(3);
            }
        });
    }

    private void vibrate() {
        vib.vibrate(VibrationEffect.createOneShot(500, DEFAULT_AMPLITUDE));
    }
}


