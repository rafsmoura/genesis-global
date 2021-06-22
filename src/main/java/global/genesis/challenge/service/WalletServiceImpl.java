package global.genesis.challenge.service;


import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import global.genesis.challenge.client.CoinCapClient;
import global.genesis.challenge.client.Response;
import global.genesis.challenge.model.WalletPosition;
import global.genesis.challenge.model.api.ApiResponse;
import global.genesis.challenge.model.api.Asset;
import global.genesis.challenge.model.api.History;
import global.genesis.challenge.model.csv.WalletAsset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private CsvService csvService;
    @Autowired
    private CoinCapClient coinCapClient;

    @Value("classpath:wallet.csv")
    private Resource csv;

    private ExecutorService executorService;

    @Override
    public String getWalletPerformance() {

        List<WalletAsset> walletAssetList = new ArrayList<>();

        try {
            List<WalletAsset> walletAssets = csvService.read(csv.getFile());

            executorService = Executors.newFixedThreadPool(3);

            for(WalletAsset walletAsset : walletAssets) {
                
                executorService.execute(() -> {
                    Response response = coinCapClient.send(null, CoinCapClient.ASSET_URL + walletAsset.getSymbol());
                    ApiResponse<Asset> assets = parseAssetResponse(response);

                    Asset asset = assets.getData().stream()
                            .filter(a -> a.getSymbol().equals(walletAsset.getSymbol()))
                            .findAny()
                            .orElse(null);

                    String historyUrl = CoinCapClient.PREFIX_HISTORY_URL
                            .concat(asset.getId())
                            .concat(CoinCapClient.SUFIX_HISTORY_URL);

                    response = coinCapClient.send(null, historyUrl);

                    ApiResponse<History> histories = parseHistoryResponse(response);

                    History history = histories.getData().get(0);

                    calculateAsset(walletAsset, history);

                    walletAssetList.add(walletAsset);

                    executorService.shutdown();
                });
            }
            boolean finished = executorService.awaitTermination(2, TimeUnit.MINUTES);
            if (finished) {
                Collections.sort(walletAssetList);
                print(walletAssetList);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void print(List<WalletAsset> list) {
        WalletPosition walletPosition = new WalletPosition();
        Float total = list.stream().map(item -> item.getAmount())
                .reduce((float) 0,(a, b) -> a + b);

        walletPosition.setTotal(total);
        walletPosition.setBestAsset(list.get(0).getSymbol());
        walletPosition.setBestPerfomance(list.get(0).getRatePosition());
        walletPosition.setWorstAsset(list.get(list.size() - 1).getSymbol());
        walletPosition.setWorstPerfomance(list.get(list.size() - 1).getRatePosition());

        System.out.println(walletPosition);
    }

    private void calculateAsset(WalletAsset walletAsset, History history) {
        walletAsset.setHistoryPrice(history.getPriceUsd());
        Float amount = Float.parseFloat(walletAsset.getQuantity()) * Float.parseFloat(history.getPriceUsd());
        walletAsset.setAmount(amount);

        Float currentPosition = Float.parseFloat(walletAsset.getQuantity()) * Float.parseFloat(history.getPriceUsd());
        Float oldPosition = Float.parseFloat(walletAsset.getQuantity()) * Float.parseFloat(walletAsset.getPrice());
        walletAsset.setRatePosition((currentPosition - oldPosition) / oldPosition * 100);
    }
    private ApiResponse<Asset> parseAssetResponse(Response response) {
        TypeToken<ApiResponse<Asset>> typeToken = new TypeToken<ApiResponse<Asset>>() {};
        ApiResponse<Asset> assetApiResponse = new GsonBuilder().create().fromJson(response.getJsonResponse(), typeToken.getType());
        return assetApiResponse;
    };

    private ApiResponse<History> parseHistoryResponse(Response response) {
        TypeToken<ApiResponse<History>> typeToken = new TypeToken<ApiResponse<History>>() {};
        ApiResponse<History> assetApiResponse = new GsonBuilder().create().fromJson(response.getJsonResponse(), typeToken.getType());
        return assetApiResponse;
    };

}
