package queivan.fetcher.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import queivan.fetcher.domain.LogDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class FetcherService {

    @Value("${fetcher.location}")
    private String path;
    @Value("${token.url}")
    private String tokenUrl;
    @Value("${token.client.id}")
    private String tokenClientId;
    @Value("${token.client.secret}")
    private String tokenClientSecret;
    @Value("${token.audience}")
    private String tokenAudience;
    @Value("${token.location}")
    private String tokenLocation;
    @Value("${api.url}")
    private String apiUrl;

    private final LogService service;

    private void readOutput(Process p) throws IOException {
        BufferedReader subProcessInputReader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = subProcessInputReader.readLine()) != null)
            service.info(LogDto.builder()
                    .content(line)
                    .issuerId("FETCHER")
                    .build());
    }

    private void configureProcess(ProcessBuilder p) {
        Map<String, String> env = p.environment();
        env.put("TOKEN_URL", tokenUrl);
        env.put("TOKEN_CLIENT_ID", tokenClientId);
        env.put("TOKEN_CLIENT_SECRET", tokenClientSecret);
        env.put("TOKEN_AUDIENCE", tokenAudience);
        env.put("TOKEN_LOCATION", tokenLocation);
        env.put("API_URL", apiUrl);
        p.redirectErrorStream(true);
    }

    public boolean fetchConfig(UUID id) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("node", String.format("%s/fetcher.js", path), id.toString());
        configureProcess(processBuilder);
        Process process = processBuilder.start();
        readOutput(process);
        return process.waitFor() == 0;
    }

    public Boolean fetchAllConfigs() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("node", String.format("%s/fetcher.js", path));
        configureProcess(processBuilder);
        Process process = processBuilder.start();
        readOutput(process);
        return process.waitFor() == 0;
    }

}
