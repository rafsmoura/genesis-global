package global.genesis.challenge.model;

import java.text.DecimalFormat;
import java.util.Locale;

public class WalletPosition {
    private Float total;
    private String bestAsset;
    private Float bestPerfomance;
    private String worstAsset;
    private Float worstPerfomance;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public String getBestAsset() {
        return bestAsset;
    }

    public void setBestAsset(String bestAsset) {
        this.bestAsset = bestAsset;
    }

    public Float getBestPerfomance() {
        return bestPerfomance;
    }

    public void setBestPerfomance(Float bestPerfomance) {
        this.bestPerfomance = bestPerfomance;
    }

    public String getWorstAsset() {
        return worstAsset;
    }

    public void setWorstAsset(String worstAsset) {
        this.worstAsset = worstAsset;
    }

    public Float getWorstPerfomance() {
        return worstPerfomance;
    }

    public void setWorstPerfomance(Float worstPerfomance) {
        this.worstPerfomance = worstPerfomance;
    }

    private String format(Float value) {
        return String.format(Locale.US,"%.2f", value);
    }

    @Override
    public String toString() {
        return  "total=" + total +
                ", best_asset='" + bestAsset + '\'' +
                ", best_perfomance=" + format(bestPerfomance) +
                ", worst_asset='" + worstAsset + '\'' +
                ", worst_perfomance=" + format(worstPerfomance);
    }
}
