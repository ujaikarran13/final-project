package com.techelevator;

public class Television {

    private boolean isOn;
    private int currentChannel;
    private int currentVolume;
    private static final int MIN_CHANNEL = 3;
    private static final int MAX_CHANNEL = 18;
    private static final int MIN_VOLUME = 0;
    private static final int MAX_VOLUME = 10;

    public Television () {
        this.isOn = false;
        this.currentChannel = 3;
        this.currentVolume = 2;
    }

    public void turnOff(){
        this.isOn = false;
    }
        public void turnOn() {
            this.isOn = true;
            this.currentChannel = 3;
            this.currentVolume = 2;
        }
        public void changeChannel(int newChannel){
        if(isOn && newChannel >=3 && newChannel <=18){
            this.currentChannel = newChannel;
        }
    }
    public void channelUp(){
        if (isOn){
            this.currentChannel++;
            if(this.currentChannel > MAX_CHANNEL){
                this.currentChannel = MIN_CHANNEL;
            }
        }
    }
        public void channelDown(){
        if(isOn){
            this.currentChannel--;
            if(this.currentChannel < MIN_CHANNEL){
                this.currentChannel = MAX_CHANNEL;
            }
        }
    }
        public void raiseVolume(){
        if (isOn && this.currentVolume <MAX_VOLUME){
            this.currentVolume++;
        }
    }
    public void lowerVolume(){
        if (isOn && this.currentVolume > MIN_VOLUME){
            this.currentVolume--;
        }
}
    public boolean isOn(){
        return isOn;
    }
    public int getCurrentChannel(){
        return currentChannel;
    }
    public int getCurrentVolume(){
        return currentVolume;
    }
}



