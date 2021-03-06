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
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUseDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUsedetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUsedetail;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecUsedetailMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecUsedetailService;

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
 * @Description: ELEC_USEDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@RestController
@RequestMapping("/equipment_manage/elecUsedetail")
@Slf4j
public class ElecUsedetailController extends JeecgController<ElecUsedetail, IElecUsedetailService> {
	@Autowired
	private IElecUsedetailService elecUsedetailService;
	@Autowired
	private ElecUsedetailMapper elecUsedetailMapper;
	/**
	 * 分页列表查询
	 *
	 * @param elecUsedetailDTO
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	public Result<?> queryPageList(ElecUsedetailDTO elecUsedetailDTO,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		Result<Page<ElecUsedetailDTO>> result = new Result<Page<ElecUsedetailDTO>>();
		Page<ElecUsedetailDTO> pageList = new Page<ElecUsedetailDTO>(pageNo,pageSize);
		pageList = elecUsedetailService.list(pageList,elecUsedetailDTO.getEqid());
		result.setSuccess(true);
		result.setCode(200);
		result.setResult(pageList);
		return result;
	}

	@GetMapping(value = "/lookDetailByEqid")
	public Result<?> lookDetailByEqid(@RequestParam(name="eqid",required=true) String eqid,
									  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									  HttpServletRequest req) {
		ElecUsedetail elecUsedetail = new ElecUsedetail();
		elecUsedetail.setEqid(eqid);
		QueryWrapper<ElecUsedetail> queryWrapper = QueryGenerator.initQueryWrapper(elecUsedetail, req.getParameterMap());
		Page<ElecUsedetail> page = new Page<ElecUsedetail>(pageNo, pageSize);
		IPage<ElecUsedetail> pageList = elecUsedetailService.page(page, queryWrapper);
		return Result.ok(pageList);
	}

	 @GetMapping(value = "/lookDetail")
	 public Result<?> lookDetail(@RequestParam(name="id",required=true)String id) {
		log.info(id);
		 ElecUsedetailDTO elecUsedetailDTO = elecUsedetailMapper.lookDetail(id);
		 if(elecUsedetailDTO==null) {
			 return Result.error("未找到对应数据");
		 }
		 return Result.ok(elecUsedetailDTO);
	 }
	
	/**
	 *   添加
	 *
	 * @param elecUsedetail
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody ElecUsedetail elecUsedetail) {
		elecUsedetailService.save(elecUsedetail);
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
		elecUsedetailService.updateById(elecUsedetail);
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
		elecUsedetailService.removeById(id);
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
		this.elecUsedetailService.removeByIds(Arrays.asList(ids.split(",")));
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
		ElecUsedetail elecUsedetail = elecUsedetailService.getById(id);
		if(elecUsedetail==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(elecUsedetail);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param elecUsedetail
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, ElecUsedetail elecUsedetail) {
        return super.exportXls(request, elecUsedetail, ElecUsedetail.class, "ELEC_USEDETAIL");
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
        return super.importExcel(request, response, ElecUsedetail.class);
    }

}
