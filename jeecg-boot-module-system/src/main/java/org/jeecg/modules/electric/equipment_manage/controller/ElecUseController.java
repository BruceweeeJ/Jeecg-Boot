package org.jeecg.modules.electric.equipment_manage.controller;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.extension.api.R;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUseDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUsedetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecBattery;
import org.jeecg.modules.electric.equipment_manage.entity.ElecEquipment;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUse;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUsedetail;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecUseMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecBatteryService;
import org.jeecg.modules.electric.equipment_manage.service.IElecEquipmentService;
import org.jeecg.modules.electric.equipment_manage.service.IElecUseService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.electric.equipment_manage.service.IElecUsedetailService;
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
 * @Description: ELEC_USE
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@RestController
@RequestMapping("/equipment_manage/elecUse")
@Slf4j
public class ElecUseController extends JeecgController<ElecUse, IElecUseService> {
	@Autowired
	private IElecUseService elecUseService;
	@Autowired
	private ElecUseMapper elecUseMapper;
	@Autowired
	private IElecUsedetailService elecUsedetailService;
	@Autowired
	private IElecEquipmentService elecEquipmentService;
	@Autowired
	private IElecBatteryService elecBatteryService;
	/**
	 * 分页列表查询
	 *
	 * @param elecUseDTO
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ElecUseDTO elecUseDTO,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<Page<ElecUseDTO>> result = new Result<Page<ElecUseDTO>>();
		Page<ElecUseDTO> pageList = new Page<ElecUseDTO>(pageNo,pageSize);
		pageList = elecUseService.list(pageList);
		result.setSuccess(true);
		result.setCode(200);
		result.setResult(pageList);
		return result;
	}

	 @GetMapping(value = "/lookDetail")
	 public Result<?> lookDetail(@RequestParam(name="id",required=true)String id) {
		 ElecUseDTO elecUseDTO = elecUseService.lookDetail(id);
		 if(elecUseDTO==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(elecUseDTO);
	 }

	/**
	 *   添加
	 *
	 * @param elecUse
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElecUse elecUse) {
		elecUseService.save(elecUse);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param elecUsedetail
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ElecUsedetail elecUsedetail) {
		ElecUse elecUse = elecUseService.getById(elecUsedetail.getId());
		if (elecUse.getEqflag().equals("可领用")){
			String ID = UUID.randomUUID().toString().replaceAll("-","");
			elecUse.setEqflag("已领用");
			elecUse.setEqusedate(elecUsedetail.getEqusedate());
			elecUseService.updateById(elecUse);

			elecUsedetail.setEqid(elecUsedetail.getId());
			elecUsedetail.setEqusedate(new Date());
			elecUsedetail.setId(ID);
			elecUsedetail.setEqflagstate("未归还");
			elecUsedetailService.save(elecUsedetail);

			return Result.ok("领用成功!");
		}
		else {
			return Result.error("领用失败");
		}
	}

	@PutMapping(value = "/Return")
	public Result<?> Return(@RequestBody ElecUsedetail usedetail){
		log.info(String.valueOf(usedetail));
		ElecUse elecUse = elecUseService.getById(usedetail.getEqid());
		ElecUsedetail elecUsedetail = elecUseMapper.getElecusedetail(usedetail.getEqid());
		ElecBattery elecBattery = elecBatteryService.getById(usedetail.getEqid());
		ElecEquipment elecEquipment = elecEquipmentService.getById(usedetail.getEqid());
		Date date = new Date();
		if (elecEquipment.getEqbatype().equals("内置")){
			elecBattery.setEqusedate(date);
			elecBatteryService.updateById(elecBattery);
		}
		elecUse.setEqusedate(date);
		elecUse.setEqflag("可领用");
		elecUse.setEqstatedetail(usedetail.getEqstatedetail());
		elecUse.setEqusestate(usedetail.getEqusestate());
		elecUsedetail.setEqflagstate("已归还");
		elecUsedetail.setEqreturndate(date);
		elecUseService.updateById(elecUse);
		elecUsedetailService.updateById(elecUsedetail);
		return Result.ok("归还成功");
	 }

	 @GetMapping(value = "/returnInfo")
	 public Result<?> returnInfo(@RequestParam(name="id",required=true)String id){
		 ElecUsedetail elecUsedetail = elecUseMapper.getElecusedetail(id);
		 return Result.ok(elecUsedetail);
	 }
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		elecUseService.removeById(id);
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
		this.elecUseService.removeByIds(Arrays.asList(ids.split(",")));
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
		ElecUse elecUse = elecUseService.getById(id);
		if(elecUse==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(elecUse);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param elecUse
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ElecUse elecUse) {
        return super.exportXls(request, elecUse, ElecUse.class, "ELEC_USE");
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
        return super.importExcel(request, response, ElecUse.class);
    }

}
