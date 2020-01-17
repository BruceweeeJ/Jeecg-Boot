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
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecEquipment;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOveradjust;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustDTO;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecOveradjustMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecEquipmentService;
import org.jeecg.modules.electric.equipment_manage.service.IElecOveradjustService;

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
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: ELEC_OVERADJUST
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@RestController
@RequestMapping("/equipment_manage/elecOveradjust")
@Slf4j
public class ElecOveradjustController extends JeecgController<ElecOveradjust, IElecOveradjustService> {
	@Autowired
	private IElecOveradjustService elecOveradjustService;
	@Autowired
	private ElecOveradjustMapper elecOveradjustMapper;
	@Autowired
	private IElecEquipmentService elecEquipmentService;
	
	/**
	 * 分页列表查询
	 *
	 * @param elecOveradjustDTO
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ElecOveradjustDTO elecOveradjustDTO,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
//		ElecOveradjustDTO elecOveradjustDTO = new ElecOveradjustDTO();
//		QueryWrapper<ElecOveradjust> queryWrapper = QueryGenerator.initQueryWrapper(elecOveradjust, req.getParameterMap());
//		Page<ElecOveradjust> page = new Page<ElecOveradjust>(pageNo, pageSize);
//		IPage<ElecOveradjust> pageList = elecOveradjustService.page(page, queryWrapper);
//		return Result.ok(pageList);
		Result<Page<ElecOveradjustDTO>> result = new Result<Page<ElecOveradjustDTO>>();
		Page<ElecOveradjustDTO> pageList = new Page<ElecOveradjustDTO>(pageNo,pageSize);
		pageList = elecOveradjustService.list(pageList);
		result.setSuccess(true);
		result.setCode(200);
		result.setResult(pageList);
		return result;
	}


	/**
	 *   添加
	 *
	 * @param elecOveradjust
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElecOveradjust elecOveradjust) {
		elecOveradjustService.save(elecOveradjust);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param elecOveradjust
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ElecOveradjust elecOveradjust) {
		elecOveradjustService.updateById(elecOveradjust);
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
		elecOveradjustService.removeById(id);
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
		this.elecOveradjustService.removeByIds(Arrays.asList(ids.split(",")));
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
		ElecOveradjust elecOveradjust = elecOveradjustService.getById(id);
		if(elecOveradjust==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(elecOveradjust);
	}

	@GetMapping(value = "/editOver")
	public Result<?> editOver(@RequestParam(name="id",required=true)String id) {
		ElecEquipment elecEquipment = elecEquipmentService.getById(id);
		if(elecEquipment==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(elecEquipment);
	}

	 @GetMapping(value = "/editAdjust")
	 public Result<?> editAdjust(@RequestParam(name="id",required=true)String id) {
		 ElecEquipment elecEquipment = elecEquipmentService.getById(id);
		 if(elecEquipment==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(elecEquipment);
	 }

	 @GetMapping(value = "/lookDetail")
	 public Result<?> lookDetail(@RequestParam(name="id",required=true)String id) {
		 ElecOveradjustdetailDTO elecOveradjustdetailDTO = elecOveradjustService.lookDetail(id);
		 if(elecOveradjustdetailDTO==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(elecOveradjustdetailDTO);
	 }

    /**
    * 导出excel
    *
    * @param request
    * @param elecOveradjust
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ElecOveradjust elecOveradjust) {
        return super.exportXls(request, elecOveradjust, ElecOveradjust.class, "ELEC_OVERADJUST");
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
        return super.importExcel(request, response, ElecOveradjust.class);
    }

}
