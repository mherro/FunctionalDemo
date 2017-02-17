package demo.matt.java;

public interface Alarmer {

    default String raiseAlarm() {
        String alarmSound = "WHOOP! WHOOP! WHOOP!";
        return alarmSound;
    }
    
}
