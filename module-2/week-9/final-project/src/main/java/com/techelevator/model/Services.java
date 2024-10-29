package com.techelevator.model;

public class Services {

    private int servicesId;
    private String serviceDescription;
    private int costPerHour;

    public Services(){}
    public Services(int servicesId, String serviceDescription, int costPerHour) {
        this.servicesId = servicesId;
        this.serviceDescription = serviceDescription;
        this.costPerHour = costPerHour;
    }

    public int getServicesId() {
        return servicesId;
    }
    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }
    public String getServiceDescription() {
        return serviceDescription;
    }
    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
    public int getCostPerHour() {
        return costPerHour;
    }
    public void setCostPerHour(int costPerHour) {
        this.costPerHour = costPerHour;



    }

}
