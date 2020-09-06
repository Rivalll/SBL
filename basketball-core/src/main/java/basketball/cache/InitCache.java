package basketball.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitCache implements CommandLineRunner {

    @Autowired
    private ConsumerCache consumerCache;
    @Override
    public void run(String... args) throws Exception {
        consumerCache.init();
    }
}
