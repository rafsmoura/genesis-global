package global.genesis.challenge.service;

import com.opencsv.bean.CsvToBeanBuilder;
import global.genesis.challenge.model.csv.WalletAsset;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
@Service
public class CsvServiceImpl implements CsvService{
    @Override
    public List<WalletAsset> read(File csv) throws IOException {
        return new CsvToBeanBuilder(new FileReader(csv))
                .withType(WalletAsset.class)
                .withSkipLines(1)
                .build()
                .parse();

    }
}
