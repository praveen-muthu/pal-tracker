package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    String port;
    String memoryLimit;
    String instanceIndex;
    String instanceAddr;

    public EnvController(
            @Value("${PORT: NOT SET}") String port,
            @Value("${MEMORY_LIMIT: NOT SET}") String memoryLimit,
            @Value("${CF_INSTANCE_INDEX: NOT SET}") String instanceIndex,
            @Value("${CF_INSTANCE_ADDR: NOT SET}") String instanceAddr) {
        this.port = port;
        this.memoryLimit = memoryLimit;
        this.instanceIndex = instanceIndex;
        this.instanceAddr = instanceAddr;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv(){
        Map<String, String> env = new HashMap<String, String>();

        env.put("PORT", port);
        env.put("MEMORY_LIMIT", memoryLimit);
        env.put("CF_INSTANCE_INDEX", instanceIndex);
        env.put("CF_INSTANCE_ADDR", instanceAddr);

        return env;
    }
}
