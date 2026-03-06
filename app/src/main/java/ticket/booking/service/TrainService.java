package ticket.booking.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrainService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private final String TRAIN_FILE_PATH="app/src/main/java/ticket/booking/localDB/trains.json";

    public List<Train> getTrains() throws IOException{

        return objectMapper.readValue(
                new File(TRAIN_FILE_PATH),
                new TypeReference<List<Train>>() {}
        );
    }
}