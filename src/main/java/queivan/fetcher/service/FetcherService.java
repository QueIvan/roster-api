package queivan.fetcher.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class FetcherService {

    @Value("${fetcher.location}")
    private String path;
    @Value("${token.url}")
    private String tokenUrl;
    @Value("${token.client.id}")
    private String tokenClientId;
    @Value("${token.client.secret}")
    private String tokenClientSecret;
    @Value("${token.audiance}")
    private String tokenAudiance;
    @Value("${api.url}")
    private String apiUrl;

    private void configureProcess(ProcessBuilder p) {
        Map<String, String> env = p.environment();
        env.put("TOKEN_URL", tokenUrl);
        env.put("TOKEN_CLIENT_ID", tokenClientId);
        env.put("TOKEN_CLIENT_SECRET", tokenClientSecret);
        env.put("TOKEN_AUDIANCE", tokenAudiance);
        env.put("API_URL", apiUrl);
        p.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        p.redirectErrorStream(true);
    }

    public boolean fetchConfig(UUID id) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("node", String.format("%s\\fetcher.js", path), id.toString());
        configureProcess(processBuilder);
        Process process = processBuilder.start();
        return process.waitFor() == 0;
    }

    public Boolean fetchAllConfigs() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder("node", String.format("%s\\fetcher.js", path));
        configureProcess(processBuilder);
        Process process = processBuilder.start();
        return process.waitFor() == 0;
    }

}
