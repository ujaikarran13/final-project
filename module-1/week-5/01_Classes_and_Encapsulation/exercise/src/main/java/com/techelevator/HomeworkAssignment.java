package com.techelevator;

public class HomeworkAssignment {

    private int earnedMarks;
    private int possibleMarks;
    private String submitterName;


    public HomeworkAssignment(int possibleMarks, String submitterName) {
        this.possibleMarks = possibleMarks;
        this.submitterName = submitterName;
    }

    public int getEarnedMarks() {
        return earnedMarks;
    }

    public void setEarnedMarks(int earnedMarks) {
        this.earnedMarks = earnedMarks;
    }

    public int getPossibleMarks() {
        return possibleMarks;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public String getLetterGrade() {
        double letterGrade = (double) earnedMarks / possibleMarks * 100;

        if (letterGrade >= 90) {
            return "A";
        } else if (letterGrade >= 80 && letterGrade <= 89) {
            return "B";
        } else if (letterGrade >= 70 && letterGrade <= 79) {
            return "C";
        } else if (letterGrade >= 60 && letterGrade <= 69) {
            return "D";
        } else {
            return "F";
        }


    }

}

