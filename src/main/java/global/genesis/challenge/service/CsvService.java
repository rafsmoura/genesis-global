package global.genesis.challenge.service;

import global.genesis.challenge.model.csv.WalletAsset;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface CsvService {

    public List<WalletAsset> read(File csv) throws IOException;
}
