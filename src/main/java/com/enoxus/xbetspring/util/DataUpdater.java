package com.enoxus.xbetspring.util;

import com.enoxus.xbetspring.service.ApiService;
import com.enoxus.xbetspring.service.BetService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataUpdater implements Runnable {

    @Autowired
    private ApiService apiService;

    @Autowired
    private BetService betService;

    @Autowired
    private DateHelper dateHelper;

    @SneakyThrows
    @Override
    public void run() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date initialDate = new Date();

        System.out.println("Data updater started, requesting data for current week");

        for (String date : dateHelper.getThisWeek()) {
            apiService.getMatchesForDate(date);
            System.out.println("Received matches for " + date);
        }

        System.out.println("Received initial data, waiting");

        while (true) {
            try {
                Thread.sleep(1000 * 60 * 60 * 24);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            apiService.updateMatchesForDate(formatter.format(initialDate));

            initialDate = new Date();
            Date nextDate = new Date(initialDate.getTime() + 86400000 * 6);

            apiService.getMatchesForDate(formatter.format(nextDate));

            betService.verifyAndCloseBetsOnFinishedMatches();
        }
    }
}
