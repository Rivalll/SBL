package basketball.service;

import basketball.BasketballApplication;
import basketball.Consumer;
import basketball.ConsumerExample;
import basketball.dao.ConsumerMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ConsumerServiceTest {

    @InjectMocks
    private ConsumerService consumerService;

    @Mock
    private ConsumerMapper consumerMapper;

    @Before
    public void pre(){
        MockitoAnnotations.initMocks(this);
    }

//    @Before
//    public void proHanler(){
//        consumerMapper = mock(ConsumerMapper.class);
//        ConsumerExample consumerExample = new ConsumerExample();
//        consumerExample.or().andIdEqualTo(20);
//        Consumer consumer = new Consumer();
//        consumer.setName("james");
//        List<Consumer> consumers = new LinkedList<>();
//        consumers.add(consumer);
//        when(consumerMapper.selectByExample(consumerExample)).thenReturn(consumers);
//    }

    @Test
    void selectList() {
        ConsumerExample consumerExample = new ConsumerExample();
        consumerExample.or().andIdEqualTo(20);
        Consumer consumer = new Consumer();
        consumer.setName("james");
        List<Consumer> consumers = new LinkedList<>();
        consumers.add(consumer);
        when(consumerMapper.selectByExample(consumerExample)).thenReturn(consumers);
        List<Consumer> consumerList = consumerService.selectList(consumerExample);
        System.out.println(consumerList.get(0).getName());
//        consumerMapper = mock(ConsumerMapper.class);
//        ConsumerExample consumerExample2 = new ConsumerExample();
//        consumerExample2.or().andIdEqualTo(20);
//        Consumer consumer = new Consumer();
//        consumer.setName("james");
//        List<Consumer> consumers = new LinkedList<>();
//        consumers.add(consumer);
//        when(consumerMapper.selectByExample(consumerExample2)).thenReturn(consumers);
//        try{
//            Field declaredField = ConsumerService.class.getDeclaredField("consumerMapper");
//            declaredField.setAccessible(true);
//            ReflectionUtils.setField(declaredField, consumerService, consumerMapper);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        ConsumerExample consumerExample = new ConsumerExample();
//        consumerExample.or().andIdEqualTo(20);
//        List<Consumer> consumerList = consumerService.selectList(consumerExample);
//        System.out.println(consumerList.get(0).getName());
    }
    @Test
    void insert(){
        Consumer consumer = new Consumer();
        consumer.setName("wade");
        consumer.setCountry("USA");
        consumerService.insert(consumer);
    }
}