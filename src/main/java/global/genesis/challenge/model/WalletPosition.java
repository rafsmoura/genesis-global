package global.genesis.challenge.model;

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

    @Override
    public String toString() {
        return  "total=" + total +
                ", best_asset='" + bestAsset + '\'' +
                ", best_perfomance=" + bestPerfomance +
                ", worst_asset='" + worstAsset + '\'' +
                ", worst_perfomance=" + worstPerfomance;
    }
}
