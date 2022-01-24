package ru.netology.graphics.image;

public class CodeSchema implements TextColorSchema {

    @Override
    public char convert(int color) {
        if (color <= 30) {
            return '@';
        } else if (color <= 50) {
            return '$';
        } else if (color <= 90) {
            return '&';
        } else if (color <= 120) {
            return '#';
        } else if (color <= 150) {
            return '*';
        } else if (color <= 180) {
            return '+';
        } else if (color <= 210) {
            return '-';
        } else {
            return '.';
        }
    }
}