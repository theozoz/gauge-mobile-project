package com.enuygun.steps;


import com.thoughtworks.gauge.Step;

public class StepImplementation {


    @Step("Wait for <5> seconds")
    public void implementation1(Long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
