package com.yizhanshi.place.service;

import com.yizhanshi.place.domain.Place;

import java.util.List;
/**
 * 场地 业务层
 *
 * @author hejiale
 */
public interface IPlaceService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param place 场地信息
     * @return 场地信息集合信息
     */
    public List<Place> selectPlaceList(Place place);

    /**
     * 增加场地信息
     * @param place
     * @return 结果
     */
    public int insertPlace(Place place);
}
