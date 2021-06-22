package global.genesis.challenge.model.api;

public class History {

    private String priceUsd;
    private Long time;
    private String date;

    public String getPriceUsd() {
        return priceUsd;
    }

    public void setPriceUsd(String priceUsd) {
        this.priceUsd = priceUsd;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "priceUsd='" + priceUsd + '\'' +
                ", time=" + time +
                ", date='" + date + '\'' +
                '}';
    }
}
