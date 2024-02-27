package com.yizhanshi.place.mapper;

import com.yizhanshi.place.domain.Place;
import com.yizhanshi.system.api.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 场地表 数据层
 *
 * @author  hejiale
 */
public  interface PlaceMapper {
    /**
     * 根据条件分页查询用户列表
     *
     * @param place 场地信息
     * @return 场地信息集合信息
     */
    public List<Place> selectPlaceList(Place place);
    /**
     * 根据场地id查询
     *
     * @param placeId 场地id
     * @return 场地信息信息
     */
    public Place selectPlaceById(Long  placeId);
    /**
     * 新增场地信息
     * @param place
     * @return
     */
    public int insertPlace(Place place);
    /**
     * 修改场地信息
     * @param place
     * @return
     */
    public int updatePlace(Place place);

    /**
     * 删除场地信息
     * @param placeIds
     * @return
     */
    public int deletePlace(Long[] placeIds);


}
