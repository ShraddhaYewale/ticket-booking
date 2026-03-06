package ticket.booking.entities;

import java.util.List;

public class User {

    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userId;

    public User(){}

    public User(String name,String password,String hashedPassword,List<Ticket> ticketsBooked,String userId){
        this.name=name;
        this.password=password;
        this.hashedPassword=hashedPassword;
        this.ticketsBooked=ticketsBooked;
        this.userId=userId;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public void printTickets(){

        if(ticketsBooked==null || ticketsBooked.isEmpty()){
            System.out.println("No tickets booked.");
            return;
        }

        for(Ticket t: ticketsBooked){
            System.out.println("Ticket ID: "+t.getTicketId()+" Train: "+t.getTrainId());
        }
    }
}