package com.github.kypeli.databindingexample;

import android.databinding.ObservableField;

public class MainViewModel {
    public ObservableField<String> helloText = new ObservableField<>("<nothing>");

    public void update() {
        helloText.set("Hello World from view model!");
    }
}
