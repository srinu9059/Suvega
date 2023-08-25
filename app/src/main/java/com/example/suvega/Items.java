package com.example.suvega;

public class Items {
    String vehicleNumber;
    String location;
    int navigation;
    int profilePic;
    String name;
    String id;
    int phone;

    public Items(String vehicleNumber,String location,int navigation,int profilePic,String name,String id,int phone){
        this.vehicleNumber=vehicleNumber;
        this.location=location;
        this.navigation=navigation;
        this.profilePic=profilePic;
        this.name=name;
        this.id=id;
        this.phone=phone;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNavigation() {
        return navigation;
    }

    public void setNavigation(int navigation) {
        this.navigation = navigation;
    }

    public int getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(int profilePic) {
        this.profilePic = profilePic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
