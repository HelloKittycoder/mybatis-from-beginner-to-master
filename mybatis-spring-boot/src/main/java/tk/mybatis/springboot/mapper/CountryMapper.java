package tk.mybatis.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.springboot.model.Country;

import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/30 12:03
 */
// 由于Application中@MapperScan注解指定了要扫描的Mapper所在包，所以这里的@Mapper就不用写了
// @Mapper
public interface CountryMapper {

    /**
     * 查询全部数据
     * @return
     */
    List<Country> selectAll();
}
