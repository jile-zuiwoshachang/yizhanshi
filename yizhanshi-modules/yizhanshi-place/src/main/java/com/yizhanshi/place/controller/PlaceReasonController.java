package com.yizhanshi.place.controller;

import com.yizhanshi.common.core.web.controller.BaseController;
import com.yizhanshi.common.core.web.domain.AjaxResult;
import com.yizhanshi.common.core.web.page.TableDataInfo;
import com.yizhanshi.common.security.annotation.RequiresPermissions;
import com.yizhanshi.place.api.domain.Place;
import com.yizhanshi.place.api.domain.PlaceReason;
import com.yizhanshi.place.domain.Time;
import com.yizhanshi.place.service.IPlaceReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 原因信息 业务参数获取
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/reason")
public class PlaceReasonController extends BaseController {
    @Autowired
    private IPlaceReasonService placeReasonService;

    /**
     * 查看原因列表——管理员使用
     * @param placeReason
     * @return
     */
    @RequiresPermissions("business:reason:list")
    @GetMapping("/list")
    public TableDataInfo list(@RequestBody PlaceReason placeReason){
        startPage();
        List<PlaceReason> list = placeReasonService.selectPlaceReasonList(placeReason);
        return getDataTable(list);
    }
    /**
     * 根据id查询具体信息
     */
    @RequiresPermissions("business:reason:query")
    @GetMapping("/{reasonId}")
    public AjaxResult query(@PathVariable("reasonId") Long  reasonId){

        return success( placeReasonService.selectReasonById(reasonId));
    }




    /**
     * 新增预约原因列表——管理员使用
     * @param placeReason
     * @return
     */
    @RequiresPermissions("business:placeReason:add")
    @PostMapping
    public AjaxResult add(@RequestBody PlaceReason placeReason){
        return toAjax(placeReasonService.insertPlaceReason(placeReason));
    }

    /**
     * 更新预约原因列表——管理员使用
     * @param placeReason
     * @return
     */
    @RequiresPermissions("business:placeReason:edit")
    @PutMapping
    public AjaxResult edit(@RequestBody PlaceReason placeReason){
        return toAjax(placeReasonService.updatePlaceReason(placeReason));
    }

    /**
     * 根据id删除原因列表
     * @param reasonIds
     * @return
     */
    @RequiresPermissions("business:placeReason:remove")
    @DeleteMapping("/{reasonIds}")
    public AjaxResult remove(@PathVariable("reasonIds") Long[] reasonIds ){
        return toAjax(placeReasonService.deletePlaceReasons(reasonIds));
    }




}
