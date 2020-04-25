package com.sujithkumar.deltatask1;

import java.util.ArrayList;
import java.util.Random;

class logic {
    private Integer corrrectans;
    private ArrayList<Integer> option = new ArrayList<>(3);
    private ArrayList<Integer> correct = new ArrayList<>();
    private ArrayList<Integer> wrong = new ArrayList<>();
    private Random rand = new Random();

    logic() {
        option.add(0);
        option.add(0);
        option.add(0);
    }

    void findoptions2(int num) {
        corrrectans = rand.nextInt(3);
        for (int i = 2; i <= (Math.min(num, 1000)); i++) {
            if ((i * 2 <= num && num % i == 0) || i == num) {
                correct.add(i);
            } else {
                wrong.add(i);
            }
        }
        option.set(corrrectans, correct.get(rand.nextInt(correct.size())));
        if (corrrectans != 0)
            option.set(0, wrong.get(rand.nextInt(wrong.size())));
        if (corrrectans != 1)
            option.set(1, wrong.get(rand.nextInt(wrong.size())));
        if (corrrectans != 2)
            option.set(2, wrong.get(rand.nextInt(wrong.size())));

        correct.clear();
        wrong.clear();
    }

    Integer getCorrrectans() {
        return corrrectans;
    }

    ArrayList<Integer> getOption() {
        return option;
    }
}
