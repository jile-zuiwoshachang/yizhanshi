package com.yizhanshi.place.controller;

import com.yizhanshi.common.core.domain.R;
import com.yizhanshi.common.core.utils.DateUtils;
import com.yizhanshi.common.core.utils.StringUtils;
import com.yizhanshi.common.core.utils.poi.ExcelUtil;
import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.log.annotation.Log;
import com.yizhanshi.common.log.enums.BusinessType;
import com.yizhanshi.common.security.annotation.InnerAuth;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.common.security.utils.SecurityUtils;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.domain.PlaceApply;
import com.yizhanshi.place.domain.vo.AllPlace;
import com.yizhanshi.place.service.IPlaceApplyService;
import com.yizhanshi.place.service.IPlaceService;
import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 场地信息 业务处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/placeInfo")
public class PlaceController extends BaseController {
    @Autowired
    private IPlaceService placeService;
    @Autowired
    private IPlaceApplyService placeApplyService;

    /**
     * 场地信息-管理员使用
     */
    @RequiresPermissions("business:place:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody Place place) {
        startPage();
        List<Place> list = placeService.selectPlaceList(place);
        return getDataTable(list);
    }

    /**
     * 导出场地信息——管理员使用
     * @param response
     * @param place
     */
    @Log(title = "场地管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:place:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response,@RequestBody Place place)
    {
        List<Place> list = placeService.selectPlaceList(place);
        ExcelUtil<Place> util = new ExcelUtil<Place>(Place.class);
        util.exportExcel(response, list, "场地信息数据");
    }
    /**
     * 根据场地编号获取详细信息
     */
    @RequiresPermissions("business:place:query")
    @GetMapping(value = { "/{placeId}" })
    public AjaxResult getInfo(@PathVariable(value = "placeId") Long placeId){
        return success(placeService.selectPlaceById(placeId));
    }

    /**
     * 新增场地信息——管理员使用
     * @param place
     * @return
     */
    @Log(title = "场地管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addPlace(@Validated @RequestBody Place place) {

        place.setCreateBy(SecurityUtils.getUsername());
        return toAjax(placeService.insertPlace(place));
    }

    /**
     * 修改场地——管理员使用
     * @param place
     * @return
     */
    @RequiresPermissions("business:place:edit")
    @Log(title = "场地管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult editPlace(@Validated @RequestBody Place place)
    {

        place.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(placeService.updatePlace(place));
    }

    /**
     * 删除场地——管理员使用
     */
    @RequiresPermissions("business:place:remove")
    @Log(title = "场地管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{placeIds}")
    public AjaxResult removePlace(@PathVariable Long[] placeIds)
    {
        if (!CollectionUtils.isEmpty(placeApplyService.selectByPlaceIds(placeIds)))
        {
            return error("存在场地申请记录不可删除");
        }
        return toAjax(placeService.deletePlace(placeIds));
    }
    /**
     * 查看可预约的场地信息
     * 用于场地申请
     * 前端传递参数为chooseDay
     *
     * @author  hejiale
     */
    @GetMapping("/allPlace")
    public TableDataInfo selectAllPlace(@RequestBody Place place){
        startPage();
        //根据查询条件和分页得到初步的场地信息
        List<Place> list = placeService.selectPlaceList(place);
        Long[] placeIdList = list.stream().map(Place::getPlaceId).toArray(Long[]::new);

        List<AllPlace> allPlaces=new ArrayList<>();
        for(int i=0;i<placeIdList.length;i++){
            //获得每个placeid的chooseDay那天的场地申请记录
            Date chooseDay=DateUtils.parseDate(place.getParams().get("chooseDay"));
            //为转化加上保险
            List<PlaceApply> placeApplyList= placeApplyService.selectAllPlace(placeIdList[i],  DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,chooseDay));
            AllPlace allPlace=new AllPlace(list.get(i),placeApplyList);
            allPlaces.add(allPlace);
        }
        return getDataTable(allPlaces);
    }
}
