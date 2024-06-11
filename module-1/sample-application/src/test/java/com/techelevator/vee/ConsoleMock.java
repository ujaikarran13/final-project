package com.techelevator.vee;

import com.techelevator.util.BasicConsole;
import com.techelevator.vee.model.Vacation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class ConsoleMock implements BasicConsole {
    public final static BigDecimal NULL_BIGDECIMAL = BigDecimal.valueOf(-1);
    public List<String> bulletedItems = new ArrayList<>();
    public List<String> capturedErrorMessages = new ArrayList<>();
    public Queue<String> stringPromptQueue = new ArrayDeque<>();
    public Queue<LocalDate> localDatePromptQueue = new ArrayDeque<>();
    public Queue<Integer> integerPromptQueue = new ArrayDeque<>();
    public Queue<BigDecimal> bigDecimalPromptQueue = new ArrayDeque<>();
    public Queue<Boolean> yesNoPromptQueue = new ArrayDeque<>();
    public List<String[]> capturedMenuPrompts = new ArrayList<>();
    public Queue<String> menuSelectionPromptQueue = new ArrayDeque<>();
    public Queue<Double> doublePromptQueue = new ArrayDeque<>();

    public void Reset() {
        this.bulletedItems.clear();
        this.capturedErrorMessages.clear();
        this.stringPromptQueue.clear();
        this.localDatePromptQueue.clear();
        this.integerPromptQueue.clear();
        this.bigDecimalPromptQueue.clear();
    }

    @Override
    public void pauseOutput() {

    }

    @Override
    public void printMessage(String message) {

    }

    @Override
    public void printErrorMessage(String message) {
        this.capturedErrorMessages.add(message);
    }

    @Override
    public void printDivider() {

    }

    @Override
    public void printBanner(String message) {

    }

    @Override
    public void printBulletedItems(String[] items) {
        this.bulletedItems.addAll(Arrays.asList(items));
    }

    @Override
    public String getMenuSelection(String[] options) {
        return this.getMenuSelection(options, false);
    }

    @Override
    public String getMenuSelection(String[] options, boolean allowNullResponse) {
        this.capturedMenuPrompts.add(options);
        return this.menuSelectionPromptQueue.remove();
    }

    @Override
    public Integer getMenuSelectionIndex(String[] options, boolean allowNullResponse) {
        this.capturedMenuPrompts.add(options);
        String selection = this.menuSelectionPromptQueue.remove();
        return List.of(options).indexOf(selection);
    }

    @Override
    public String promptForString(String prompt) {
        return this.stringPromptQueue.remove();
    }

    @Override
    public boolean promptForYesNo(String prompt) {
        return this.yesNoPromptQueue.remove();
    }

    @Override
    public Integer promptForInteger(String prompt) {
        Integer val = this.integerPromptQueue.remove();

        // since a NULL value cannot be queued, use MIN_VALUE
        // as a proxy
        if (val == Integer.MIN_VALUE) {
            val = null;
        }
        return val;
    }

    @Override
    public Double promptForDouble(String prompt) {
        return this.doublePromptQueue.remove();
    }

    @Override
    public BigDecimal promptForBigDecimal(String prompt) {
        BigDecimal value;
        value = this.bigDecimalPromptQueue.remove();

        // since a NULL value cannot be queued, use -1
        // as a proxy
        if (value == NULL_BIGDECIMAL) value = null;
        return value;
    }

    @Override
    public LocalDate promptForLocalDate(String prompt) {
        return this.localDatePromptQueue.remove();
    }
}
