package tk.mybatis.simple.mapper;

import tk.mybatis.simple.model.Country;

import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/12 12:38
 */
public interface CountryMapper {

    /**
     * 查询全部用户
     * @return
     */
    List<Country> selectAll();
}
