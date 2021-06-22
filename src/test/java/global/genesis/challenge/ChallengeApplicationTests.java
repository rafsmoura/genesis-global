package global.genesis.challenge;

import global.genesis.challenge.service.WalletService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ChallengeApplicationTests {

    private final String EXPECTED_RETURN = "total=16984.62, best_asset='BTC', best_perfomance=1.51, worst_asset='ETH', worst_perfomance=1.01";
    @Autowired
    private WalletService walletService;


    @Test
    void perfomanceTest() throws IOException {
        String result = walletService.getWalletPerformance();
        assertNotNull(result);
        assertEquals(result, EXPECTED_RETURN);
    }

}

