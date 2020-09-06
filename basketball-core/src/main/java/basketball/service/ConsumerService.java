package basketball.service;

import basketball.Consumer;
import basketball.ConsumerExample;
import basketball.dao.ConsumerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;


    public int insert(Consumer record){
        return consumerMapper.insert(record);
    }


    public int delete(Integer id){
        return consumerMapper.deleteByPrimaryKey(id);
    }

    public List<Consumer>  selectList(ConsumerExample consumerExample){
        Consumer consumer = new Consumer();
        return consumerMapper.selectByExample(consumerExample);
    }


    public Consumer selectById(int id){
        return consumerMapper.selectByPrimaryKey(id);
    }

}
