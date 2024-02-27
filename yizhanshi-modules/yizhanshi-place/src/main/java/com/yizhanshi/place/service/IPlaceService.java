package com.yizhanshi.place.service;

import com.yizhanshi.place.domain.Place;
import com.yizhanshi.system.api.domain.SysUser;

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
     * 通过场地ID查询用户
     *
     * @param placeId  场地ID
     * @return 场地对象信息
     */
    public Place selectPlaceById(Long placeId);
    /**
     * 增加场地信息
     * @param place
     * @return 结果
     */
    public int insertPlace(Place place);
    /**
     * 修改场地信息
     *
     * @param place 场地信息
     * @return 结果
     */
    public int updatePlace(Place place);

    /**
     * 删除场地信息
     *
     * @param placeIds 场地id
     * @return 结果
     */
    public int deletePlace(Long[] placeIds);


}
