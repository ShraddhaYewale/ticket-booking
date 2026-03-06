package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {

    private ObjectMapper objectMapper=new ObjectMapper();

    private List<User> users;
    private List<Train> trains;

    private User user;

    private final String USER_FILE_PATH="app/src/main/java/ticket/booking/localDB/users.json";

    public UserBookingService() throws IOException{
        loadUsers();
        trains=new TrainService().getTrains();
    }

    public UserBookingService(User user) throws IOException{
        this.user=user;
        loadUsers();
        trains=new TrainService().getTrains();
    }

    private void loadUsers() throws IOException{
        users=objectMapper.readValue(
                new File(USER_FILE_PATH),
                new TypeReference<List<User>>() {}
        );
    }

    public boolean signUp(User user){

        users.add(user);

        try{
            objectMapper.writeValue(new File(USER_FILE_PATH),users);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean loginUser(){

        for(User u:users){
            if(u.getName().equals(user.getName())
                    && u.getHashedPassword().equals(user.getHashedPassword())){
                user=u;
                return true;
            }
        }

        return false;
    }

    public void fetchBookings(){
        user.printTickets();
    }

    public boolean cancelBooking(String ticketId){

        return user.getTicketsBooked().removeIf(
                t->t.getTicketId().equals(ticketId)
        );
    }

    public List<Train> getTrains(String source,String dest){

        List<Train> result=new ArrayList<>();

        for(Train t:trains){

            List<String> stations=t.getStations();

            int s=stations.indexOf(source);
            int d=stations.indexOf(dest);

            if(s!=-1 && d!=-1 && s<d){
                result.add(t);
            }
        }

        return result;
    }

    public List<List<Integer>> fetchSeats(Train train){
        return train.getSeats();
    }

    public String bookTrainSeat(Train train,int row,int col){

        List<List<Integer>> seats=train.getSeats();

        if(seats.get(row).get(col)==1)
            return null;

        seats.get(row).set(col,1);

        String ticketId="T"+System.currentTimeMillis();

        Ticket ticket=new Ticket(ticketId,train.getTrainId(),row,col);

        user.getTicketsBooked().add(ticket);

        return ticketId;
    }
}