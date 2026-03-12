package ticket.booking.entities;

public class Ticket {

    private String ticketId;
    private String trainId;
    private int row;
    private int col;

    public Ticket(){}

    public Ticket(String ticketId,String trainId,int row,int col){
        this.ticketId=ticketId;
        this.trainId=trainId;
        this.row=row;
        this.col=col;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getTrainId() {
        return trainId;
    }
}
