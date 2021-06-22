package global.genesis.challenge.client;

public class Response {

    private String jsonResponse;


    public String getJsonResponse() {
        return jsonResponse;
    }

    public void setJsonResponse(String jsonResponse) {
        this.jsonResponse = jsonResponse;
    }

    @Override
    public String toString() {
        return "Response{" +
                "jsonResponse='" + jsonResponse + '\'' +
                '}';
    }
}
