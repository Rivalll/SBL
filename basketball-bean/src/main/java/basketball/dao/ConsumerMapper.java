package basketball.dao;

import basketball.Consumer;
import basketball.ConsumerExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.cache.annotation.Cacheable;

public interface ConsumerMapper {
    @SelectProvider(type=ConsumerSqlProvider.class, method="countByExample")
    int countByExample(ConsumerExample example);

    @DeleteProvider(type=ConsumerSqlProvider.class, method="deleteByExample")
    int deleteByExample(ConsumerExample example);

    @Delete({
        "delete from consumer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into consumer (name, country)",
        "values (#{name,jdbcType=VARCHAR}, #{country,jdbcType=VARCHAR})"
    })
//    @SelectKey(statement="select last_insert_id()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Consumer record);

    @InsertProvider(type=ConsumerSqlProvider.class, method="insertSelective")
    @SelectKey(statement="id", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Consumer record);



    @SelectProvider(type=ConsumerSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR)
    })
    List<Consumer> selectByExample(ConsumerExample example);

    @Select({
        "select",
        "id, name, country",
        "from consumer",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="country", property="country", jdbcType=JdbcType.VARCHAR)
    })
    Consumer selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ConsumerSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Consumer record, @Param("example") ConsumerExample example);

    @UpdateProvider(type=ConsumerSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Consumer record, @Param("example") ConsumerExample example);

    @UpdateProvider(type=ConsumerSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Consumer record);

    @Update({
        "update consumer",
        "set name = #{name,jdbcType=VARCHAR},",
          "country = #{country,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Consumer record);
}