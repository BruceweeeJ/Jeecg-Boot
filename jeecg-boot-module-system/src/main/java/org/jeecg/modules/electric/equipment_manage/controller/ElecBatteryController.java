package org.jeecg.modules.electric.equipment_manage.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecBatteryDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecBattery;
import org.jeecg.modules.electric.equipment_manage.service.IElecBatteryService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: ELEC_BATTERY
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@RestController
@RequestMapping("/equipment_manage/elecBattery")
@Slf4j
public class ElecBatteryController extends JeecgController<ElecBattery, IElecBatteryService> {
	@Autowired
	private IElecBatteryService elecBatteryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param elecBattery
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ElecBatteryDTO elecBatteryDTO,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//		QueryWrapper<ElecBattery> queryWrapper = QueryGenerator.initQueryWrapper(elecBattery, req.getParameterMap());
//		Page<ElecBattery> page = new Page<ElecBattery>(pageNo, pageSize);
//		IPage<ElecBattery> pageList = elecBatteryService.page(page, queryWrapper);
//		return Result.ok(pageList);
		Result<Page<ElecBatteryDTO>> result = new Result<Page<ElecBatteryDTO>>();
		Page<ElecBatteryDTO> pageList = new Page<ElecBatteryDTO>(pageNo,pageSize);
		pageList = elecBatteryService.list(pageList);
		log.info("查询当前页："+pageList.getCurrent());
		log.info("查询当前页数量："+pageList.getSize());
		log.info("查询结果数量："+pageList.getRecords().size());
		log.info("数据总数："+pageList.getTotal());
		result.setSuccess(true);
		result.setCode(200);
		result.setResult(pageList);
		return result;
	}
	
	/**
	 *   添加
	 *
	 * @param elecBattery
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElecBattery elecBattery) {
		elecBatteryService.save(elecBattery);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param elecBattery
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ElecBattery elecBattery) {
		elecBatteryService.updateById(elecBattery);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		elecBatteryService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.elecBatteryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		ElecBattery elecBattery = elecBatteryService.getById(id);
		if(elecBattery==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(elecBattery);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param elecBattery
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ElecBattery elecBattery) {
        return super.exportXls(request, elecBattery, ElecBattery.class, "ELEC_BATTERY");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, ElecBattery.class);
    }

}
