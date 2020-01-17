package org.jeecg.modules.electric.equipment_manage.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bytebuddy.matcher.ElementMatcher;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.electric.equipment_manage.entity.ElecBattery;
import org.jeecg.modules.electric.equipment_manage.entity.ElecEquipment;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOveradjust;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUse;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecEquipmentMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecBatteryService;
import org.jeecg.modules.electric.equipment_manage.service.IElecEquipmentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.electric.equipment_manage.service.IElecOveradjustService;
import org.jeecg.modules.electric.equipment_manage.service.IElecUseService;
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
 * @Description: ELEC_EQUIPMENT
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@RestController
@RequestMapping("/equipment_manage/elecEquipment")
@Slf4j
public class ElecEquipmentController extends JeecgController<ElecEquipment, IElecEquipmentService> {
	@Autowired
	private IElecEquipmentService elecEquipmentService;
	@Autowired
	private IElecOveradjustService elecOveradjustService;
	@Autowired
	private IElecUseService elecUseService;
	@Autowired
	private IElecBatteryService elecBatteryService;
	/**
	 * 分页列表查询
	 *
	 * @param elecEquipment
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ElecEquipment elecEquipment,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//		QueryWrapper<ElecEquipment> queryWrapper = QueryGenerator.initQueryWrapper(elecEquipment, req.getParameterMap());
//		Page<ElecEquipment> page = new Page<ElecEquipment>(pageNo, pageSize);
//		IPage<ElecEquipment> pageList = elecEquipmentService.page(page, queryWrapper);
//		return Result.ok(pageList);
		Result<Page<ElecEquipment>> result = new Result<Page<ElecEquipment>>();
		Page<ElecEquipment> pageList = new Page<ElecEquipment>(pageNo,pageSize);
		pageList = elecEquipmentService.list(pageList);
//		log.info("查询当前页："+pageList.getCurrent());
//		log.info("查询当前页数量："+pageList.getSize());
//		log.info("查询结果数量："+pageList.getRecords().size());
//		log.info("数据总数："+pageList.getTotal());
		result.setSuccess(true);
		result.setCode(200);
		result.setResult(pageList);
		return result;
	}

	/**
	 *   添加
	 *
	 * @param elecEquipment
	 * @return
	 * 重写
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElecEquipment elecEquipment) {
		QueryWrapper<ElecEquipment>queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("eqcode",elecEquipment.getEqcode());
		try {
			if (elecEquipmentService.getOne(queryWrapper)!=null){
				return Result.error("设备编号不唯一，请重新输入");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.error("设备编号不唯一，请重新输入");
		}
		//设备ID为UUID32位编码
		String ID = UUID.randomUUID().toString().replaceAll("-","");

		elecEquipment.setId(ID);
		elecEquipmentService.save(elecEquipment);
		//检修表添加
		ElecOveradjust elecOveradjust = new ElecOveradjust();
		elecOveradjust.setId(ID);
		elecOveradjust.setEqid(elecEquipment.getEqcode());
		elecOveradjustService.save(elecOveradjust);
		//领用表添加
		ElecUse elecUse = new ElecUse();
		elecUse.setId(ID);
		elecUse.setEqcode(elecEquipment.getEqcode());
		elecUse.setEqflag("可领用");
		elecUseService.save(elecUse);
		//电池表添加
		try {
			String batteryType = new String();
			batteryType = elecEquipment.getEqbatype();
			if (batteryType.equals("内置")){
				ElecBattery elecBattery = new ElecBattery();
				elecBattery.setId(ID);
				elecBattery.setEqid(elecEquipment.getEqcode());
				elecBatteryService.save(elecBattery);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.ok("添加成功！");
		}
		return Result.ok("添加成功！");
	}
//	@PostMapping(value = "/add")
//	public Result<?> add(@RequestBody ElecEquipment elecEquipment) {
//		elecEquipmentService.save(elecEquipment);
//		return Result.ok("添加成功！");
//	}
	
	/**
	 *  编辑
	 *
	 * @param elecEquipment
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ElecEquipment elecEquipment) {
		elecEquipmentService.updateById(elecEquipment);
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
		elecEquipmentService.removeById(id);
		elecOveradjustService.removeById(id);
		elecUseService.removeById(id);
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
		this.elecEquipmentService.removeByIds(Arrays.asList(ids.split(",")));
		this.elecOveradjustService.removeByIds(Arrays.asList(ids.split(",")));
		this.elecUseService.removeByIds(Arrays.asList(ids.split(",")));
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
		ElecEquipment elecEquipment = elecEquipmentService.getById(id);
		if(elecEquipment==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(elecEquipment);
	}

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @GetMapping(value = "/showDetail")
	 public Result<?> showDetail(String id) {
		 ElecEquipment elecEquipment = elecEquipmentService.getById(id);
		 if(elecEquipment==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(elecEquipment);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param elecEquipment
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ElecEquipment elecEquipment) {
        return super.exportXls(request, elecEquipment, ElecEquipment.class, "ELEC_EQUIPMENT");
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
        return super.importExcel(request, response, ElecEquipment.class);
    }

}
