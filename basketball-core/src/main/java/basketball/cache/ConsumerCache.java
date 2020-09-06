package basketball.cache;

import basketball.Consumer;
import basketball.ConsumerExample;
import basketball.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConsumerCache {

    private static List<Consumer> consumerCache;

    private ConsumerService consumerService;

    @Autowired
    ConsumerCache(ConsumerService consumerService){
        this.consumerService = consumerService;
    }
    public void init(){
        consumerCache = consumerService.selectList(new ConsumerExample());
    }
    public List<Consumer> getCache(){
        return consumerCache;
    }

    public void add(Consumer consumer){
        consumerCache.add(consumer);
    }
}
