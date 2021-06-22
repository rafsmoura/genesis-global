package global.genesis.challenge;

import global.genesis.challenge.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WalletComponent {

    @Autowired
    private WalletService walletService;

    @PostConstruct
    public void init() {
        walletService.getWalletPerformance();
    }

}
