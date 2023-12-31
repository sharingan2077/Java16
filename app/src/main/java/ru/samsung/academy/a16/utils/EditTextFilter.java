package ru.samsung.academy.a16.utils;

import android.text.InputFilter;
import android.text.Spanned;

public class EditTextFilter implements InputFilter {

    private int min;
    private int max;

    public EditTextFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        if (dest.toString().length() == 0 && source.equals("0")) return "";
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return c >= a && c <= b;
    }
}
