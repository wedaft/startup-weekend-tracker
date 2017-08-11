package models;

import java.util.ArrayList;
import java.util.List;

public class Event {
    int id;
    private String name;
    private String description;
    private List<Attendee> allAttendees;
    private static ArrayList<Event> allEvents = new ArrayList<Event>();
    private static int eventListSize;

    //Constructor
    public Event(String name,String description){
        this.name = name;
        this.description = description;
        this.allAttendees = allAttendees;
        eventListSize++;
        this.id = eventListSize;
        allEvents.add(this);
    }

    public static Event createNewEvent(){
        Event newEvent = new Event("Test Event","Test Description");
        return newEvent;
    }
    //Getters + Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Attendee> getAllAttendees() {
        return allAttendees;
    }

    public void setAllAttendees(List<Attendee> allAttendees) {
        this.allAttendees = allAttendees;
    }

    public static List<Event> getAllEvents() {
        return allEvents;
    }

    public static void setAllEvents(ArrayList<Event> allEvents) {
        Event.allEvents = allEvents;
    }
}