package sample;

import java.time.LocalDate;

//Build the class to handle the eventlist
public class LocalEvent {

    //Attribute of event
    private String description;
    private LocalDate date;

    //Getter for description
    public String getDescription() { return description; }

    //Setter for description
    public void setDescription(String description) { this.description = description; }

    //Getter for date
    public LocalDate getDate() { return date; }

    //Setter for date
    public void setDate(LocalDate date) { this.date = date; }

    //Constructor for event
    public LocalEvent(LocalDate date, String description) {
        this.setDate(date);
        this.setDescription(description);
    }
    //Return the content and show in list
    @Override
    public String toString(){ return "At: " + this.getDate() + " | " + this.getDescription(); }
}