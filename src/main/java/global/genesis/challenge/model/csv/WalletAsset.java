package global.genesis.challenge.model.csv;

import com.opencsv.bean.CsvBindByPosition;

public class WalletAsset implements Comparable {

    @CsvBindByPosition(position = 0)
    private String symbol;
    @CsvBindByPosition(position = 1)
    private String quantity;
    @CsvBindByPosition(position = 2)
    private String price;

    private String historyPrice;
    private Float amount;
    private Float ratePosition;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getHistoryPrice() {
        return historyPrice;
    }

    public void setHistoryPrice(String historyPrice) {
        this.historyPrice = historyPrice;
    }

    public Float getRatePosition() {
        return ratePosition;
    }

    public void setRatePosition(Float ratePosition) {
        this.ratePosition = ratePosition;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "WalletAsset{" +
                "symbol='" + symbol + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", historyPrice='" + historyPrice + '\'' +
                ", amount='" + amount + '\'' +
                ", ratePosition=" + ratePosition +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        WalletAsset walletAsset = ((WalletAsset) o);
        if (this.ratePosition > walletAsset.ratePosition) {
            return -1;
        } else if (this.ratePosition < walletAsset.ratePosition) {
            return +1;
        }
        return 0;
    }
}
