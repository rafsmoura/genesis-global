package global.genesis.challenge.client;

public class Request {

   private String jsonRequest;

    public String getJsonRequest() {
        return jsonRequest;
    }

    public void setJsonRequest(String jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

    @Override
    public String toString() {
        return "Request{" +
                "jsonRequest='" + jsonRequest + '\'' +
                '}';
    }
}
