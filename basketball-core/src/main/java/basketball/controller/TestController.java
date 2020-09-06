package basketball.controller;

import basketball.Consumer;
import basketball.ConsumerExample;
import basketball.cache.ConsumerCache;
import basketball.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/consumer")
public class TestController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private ConsumerCache consumerCache;

    @PostMapping("insert")
    public void insert(){
        Consumer consumer = new Consumer();
        consumer.setName("consumer1");
        consumer.setCountry("China");
        consumerService.insert(consumer);
        consumerCache.add(consumer);
    }

    @GetMapping("select")
    public void select(){
//        List<Consumer> consumerList = consumerService.selectList(new ConsumerExample());
        List<Consumer> consumerList = consumerCache.getCache();
        consumerList.stream().forEach(x -> {
            System.out.println(x.toString());
        });
    }

    @GetMapping("test")
    public String test(){
        System.out.println("test");
        String string = null;
        Objects.requireNonNull(string, "这个string不可为空");

        return "test";
    }


}
