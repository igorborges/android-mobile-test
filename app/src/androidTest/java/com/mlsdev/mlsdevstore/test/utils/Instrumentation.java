package com.mlsdev.mlsdevstore.test.utils;

import android.os.Bundle;

import androidx.test.runner.MonitoringInstrumentation;

import com.mlsdev.mlsdevstore.test.BuildConfig;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;

@CucumberOptions(
        features = "features",
        glue = "com.mlsdev.mlsdevstore.test"
        ,tags = {"@all"}
        ,monochrome = true
        ,plugin = {"pretty"}
//        ,plugin = {"html:target/cucumber-html-report"}
)

public class Instrumentation extends MonitoringInstrumentation {

    private static final String TAGS_KEY = "tags";

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);

        // Reading runner params
        String tags = BuildConfig.TAGS;
        if (!tags.isEmpty()) {
            arguments.putString(TAGS_KEY, tags);
        }

        instrumentationCore.create(arguments);
        start();
    }
    @Override
    public void onStart() {
        super.onStart();
        waitForIdleSync();
        instrumentationCore.start();
    }
}
