package global.genesis.challenge.model.api;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse<T>{

   private  List<T> data = new ArrayList<>();
   private Long timestamp;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "data=" + data +
                ", timestamp=" + timestamp +
                '}';
    }
}
