package com.example.gidro.myapplication.utils;

import com.example.gidro.myapplication.R;

/**
 * Created by Gidro on 06.06.2017.
 */

public class Utils {

    public static int numberToDrawableIdPriority(int number){

        int id = 0;

        switch (number) {
            case 1:

                id = R.drawable.high_priority;

                break;
            case 2:

                id = R.drawable.medium_priority;

                break;

            case 3:

                id = R.drawable.low_priority;

                break;
        }

        return id;
    }

}
