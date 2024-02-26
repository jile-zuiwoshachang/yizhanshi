package com.yizhanshi.place.controller;

import com.yizhanshi.common.core.domain.R;
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
import com.yizhanshi.place.domain.Place;
import com.yizhanshi.place.service.IPlaceService;
import com.yizhanshi.system.api.domain.SysCredit;
import com.yizhanshi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 场地信息 业务处理
 *
 * @author hejiale
 */
@RestController
@RequestMapping("/place")
public class PlaceController extends BaseController {
    @Autowired
    private IPlaceService placeService;

    /**
     * 场地信息-管理员使用
     */
    @RequiresPermissions("system:place:list")
    @GetMapping("/list")
    public TableDataInfo list(Place place) {
        startPage();
        List<Place> list = placeService.selectPlaceList(place);
        return getDataTable(list);
    }
    @Log(title = "场地管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:place:export")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Place place)
    {
        List<Place> list = placeService.selectPlaceList(place);
        ExcelUtil<Place> util = new ExcelUtil<Place>(Place.class);
        util.exportExcel(response, list, "场地信息数据");
    }
    /**
     * 新增场地信息
     * @param place
     * @return
     */
    @Log(title = "场地管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult addPlace(@Validated @RequestBody Place place) {

        place.setCreateBy(SecurityUtils.getUsername());
        return toAjax(placeService.insertPlace(place));
    }

}
