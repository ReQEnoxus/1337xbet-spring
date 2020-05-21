package com.enoxus.xbetspring.schedulers;

import com.enoxus.xbetspring.service.ApiService;
import com.enoxus.xbetspring.service.BetService;
import com.enoxus.xbetspring.util.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
public class UpdateScheduler {

    @Autowired
    private ApiService apiService;

    @Autowired
    private BetService betService;

    @Scheduled(cron = "0 0 1 * * *")
    public void run() {

        Date initialDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        apiService.updateMatchesForDate(formatter.format(initialDate));

        Date nextDate = new Date(System.currentTimeMillis() + 86400000 * 6);

        apiService.getMatchesForDate(formatter.format(nextDate));
        betService.verifyAndCloseBetsOnFinishedMatches();
    }
}
