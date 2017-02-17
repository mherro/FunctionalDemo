package demo.matt.java;

import java.util.Date;

public class Robot implements Alarmer {
    
    private String name;
    private Integer size;
    private String color;
    private Date birthDate;
    private Integer batteryLevel;
    private Integer numberOfWheels;
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getSize() {
        return size;
    }
    public void setSize(Integer size) {
        this.size = size;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    public Integer getBatteryLevel() {
        return batteryLevel;
    }
    public void setBatteryLevel(Integer batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
    public Integer getNumberOfWheels() {
        return numberOfWheels;
    }
    public void setNumberOfWheels(Integer numberOfWheels) {
        this.numberOfWheels = numberOfWheels;
    }
    
    

    @Override
    public String toString() {
        return "Robot [name=" + name + ", size=" + size + ", color=" + color + ", birthDate=" + birthDate + ", batteryLevel=" + batteryLevel + ", numberOfWheels=" + numberOfWheels
            + "]";
    }

    
}
