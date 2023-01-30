package tk.mybatis.simple;

import tk.mybatis.simple.model.Country;

import java.util.List;

/**
 * @author shucheng
 * @date 2023/1/30 22:02
 */
public interface SimpleMapper {

    Country selectCountry(Long id);

    List<Country> selectAll();
}
