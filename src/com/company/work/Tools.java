package com.company.work;

import java.util.Objects;

/**
 * Created by Pavel G on 14.10.2016.
 */
public class Tools {

    public static void print(String data) {
        System.out.println(data);
    }

    public static void isNull(Object... objects) {
        for (Object object : objects) {
            if (object == null)
                throw new NullPointerException("Object most not null");
        }

    }
}
