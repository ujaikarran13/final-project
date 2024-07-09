package com.techelevator;

public class FruitTree {

    private String typeOfFruit;
    private int piecesOfFruitLeft;


    public FruitTree (String typeOfFruit, int startingPiecesOfFruit) {
        this.typeOfFruit = typeOfFruit;
        this.piecesOfFruitLeft = startingPiecesOfFruit;
    }
    public boolean pickFruit(int numberofPiecesToRemove){
        if (piecesOfFruitLeft >= numberofPiecesToRemove){
            piecesOfFruitLeft -= numberofPiecesToRemove;
            return true;
        } else {
            return false;
        }
    }
    public  String getTypeOfFruit(){
        return typeOfFruit;
    }
    public void setTypeOfFruit(String typeOfFruit){
        this.typeOfFruit = typeOfFruit;
    }
    public int getPiecesOfFruitLeft(){
        return piecesOfFruitLeft;
    }
    public void setPiecesOfFruitLeft(int piecesOfFruitLeft){
        this.piecesOfFruitLeft = piecesOfFruitLeft;
    }
}
