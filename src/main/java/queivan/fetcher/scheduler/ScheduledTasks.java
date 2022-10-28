package queivan.fetcher.scheduler;

import lombok.RequiredArgsConstructor;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import queivan.fetcher.domain.LogDto;
import queivan.fetcher.service.FetcherService;
import queivan.fetcher.service.LogService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final FetcherService fetcher;
    private final LogService logger;

    @Scheduled(cron = "0 0 */12 * * *")
    public void fetchAllConfigs() throws IOException, InterruptedException {
        logger.info(LogDto.builder()
                .content("Fetching all active configs")
                .issuerId("SYSTEM")
                .build());
        fetcher.fetchAllConfigs();
    }

}
