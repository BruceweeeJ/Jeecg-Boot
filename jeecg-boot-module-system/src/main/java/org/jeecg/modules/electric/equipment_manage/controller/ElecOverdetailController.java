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
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOverdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOveradjust;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOverdetail;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecOverdetailMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecOveradjustService;
import org.jeecg.modules.electric.equipment_manage.service.IElecOverdetailService;

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
 * @Description: ELEC_OVERDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@RestController
@RequestMapping("/equipment_manage/elecOverdetail")
@Slf4j
public class ElecOverdetailController extends JeecgController<ElecOverdetail, IElecOverdetailService> {
	@Autowired
	private IElecOverdetailService elecOverdetailService;
	@Autowired
	private IElecOveradjustService elecOveradjustService;
	@Autowired
	private ElecOverdetailMapper elecOverdetailMapper;
	
	/**
	 * 分页列表查询
	 *
	 * @param elecOveradjustdetailDTO
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ElecOveradjustdetailDTO elecOveradjustdetailDTO,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<Page<ElecOveradjustdetailDTO>> result = new Result<Page<ElecOveradjustdetailDTO>>();
		Page<ElecOveradjustdetailDTO> pageList = new Page<ElecOveradjustdetailDTO>(pageNo,pageSize);
		pageList = elecOveradjustService.lookOverdetail(pageList,elecOveradjustdetailDTO.getEqid());
		result.setSuccess(true);
		result.setCode(200);
		result.setResult(pageList);
		return result;
	}
	
	/**
	 *   添加
	 *
	 * @param elecOverdetail
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElecOverdetail elecOverdetail) {
		ElecOveradjust elecOveradjust = elecOveradjustService.getById(elecOverdetail.getId());
		try {
			if (elecOverdetail.getEqoverdate().getTime() > elecOveradjust.getEqoverdate().getTime() ) {
				elecOveradjust.setEqoverunit(elecOverdetail.getEqoverunit());
				elecOveradjust.setEqoverdate(elecOverdetail.getEqoverdate());
				elecOveradjustService.updateById(elecOveradjust);
			}
		} catch (Exception e) {
			elecOveradjust.setEqoverdate(elecOverdetail.getEqoverdate());
			elecOveradjust.setEqoverunit(elecOverdetail.getEqoverunit());
			elecOveradjustService.updateById(elecOveradjust);
			e.printStackTrace();
		}
		String ID = UUID.randomUUID().toString().replaceAll("-","");
		String eqid = elecOverdetail.getId();
		elecOverdetail.setId(ID);
		elecOverdetail.setEqid(eqid);
		elecOverdetailService.save(elecOverdetail);
		return Result.ok("添加成功！");
	}

	 @GetMapping(value = "/lookDetail")
	 public Result<?> lookDetail(@RequestParam(name="id",required=true)String id) {
		 ElecOveradjustdetailDTO elecOveradjustdetailDTO = elecOverdetailMapper.lookDetail(id);
		 if(elecOveradjustdetailDTO==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(elecOveradjustdetailDTO);
	 }

	/**
	 *  编辑
	 *
	 * @param elecOverdetail
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody ElecOverdetail elecOverdetail) {
		ElecOveradjust elecOveradjust = elecOveradjustService.getById(elecOverdetail.getEqid());
		try {
			if (elecOverdetail.getEqoverdate().getTime() >= elecOveradjust.getEqoverdate().getTime()) {
				elecOveradjust.setEqoverunit(elecOverdetail.getEqoverunit());
				elecOveradjust.setEqoverdate(elecOverdetail.getEqoverdate());
				elecOveradjustService.updateById(elecOveradjust);
			}
		} catch (Exception e) {
			elecOveradjust.setEqoverdate(elecOverdetail.getEqoverdate());
			elecOveradjust.setEqoverunit(elecOverdetail.getEqoverunit());
			elecOveradjustService.updateById(elecOveradjust);
			e.printStackTrace();
		}
		elecOverdetailService.updateById(elecOverdetail);
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
		elecOverdetailService.removeById(id);
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
		this.elecOverdetailService.removeByIds(Arrays.asList(ids.split(",")));
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
		ElecOverdetail elecOverdetail = elecOverdetailService.getById(id);
		if(elecOverdetail==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(elecOverdetail);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param elecOverdetail
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ElecOverdetail elecOverdetail) {
        return super.exportXls(request, elecOverdetail, ElecOverdetail.class, "ELEC_OVERDETAIL");
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
        return super.importExcel(request, response, ElecOverdetail.class);
    }

}
