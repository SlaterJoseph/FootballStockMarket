package com.project.footballstockmarket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class CollectionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionService.class);

    /**
     * A schedule method which updates stats close to the new season
     */
    @Scheduled(cron = "${schedule.cron.seasonal}", zone = "EST")
    public void getSeasonalStats(){
        LOGGER.debug("Updating Seasonal Stats");
    }

    /**
     * A schedule method which updates weekly stats at the end of a given football week
     */
    @Scheduled(cron = "${schedule.cron.weekly}", zone = "EST")
    public void getWeeklyStats(){
        LOGGER.debug("Updating Weekly Stats");
    }

    /**
     * A scheduled event which updates depth charts at the start of a given week
     */
    @Scheduled(cron = "${schedule.cron.depth-charts}", zone = "EST")
    public void updateDepthCharts(){
        LOGGER.debug("Updating Depth Charts");
    }


}
