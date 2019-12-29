package org.jeecg.modules.electric.equipment_manage.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;

/**
 * @Description: ELEC_USEDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-28
 * @Version: V1.0
 */
@Data
@TableName("elec_usedetail")
public class ElecUsedetail implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**设备ID*/
	@Excel(name = "设备ID", width = 15)
    private java.lang.String eqid;
	/**领用日期*/
	@Excel(name = "领用日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date equsedate;
	/**领用单位*/
	@Excel(name = "领用单位", width = 15)
    private java.lang.String equseunit;
	/**领用责任人*/
	@Excel(name = "领用责任人", width = 15)
    private java.lang.String equsepeople;
	/**设备管理员*/
	@Excel(name = "设备管理员", width = 15)
    private java.lang.String equseadmin;
	/**当前借出状态*/
	@Excel(name = "当前借出状态", width = 15)
    private java.lang.String eqflagstate;
	/**归还日期*/
	@Excel(name = "归还日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date eqreturndate;
	/**设备使用情况*/
	@Excel(name = "设备使用情况", width = 15)
    private java.lang.String equsestate;
	/**领用情况说明*/
	@Excel(name = "领用情况说明", width = 15)
    private java.lang.String equsesituation;
	/**设备情况详细描述*/
	@Excel(name = "设备情况详细描述", width = 15)
    private java.lang.String eqstatedetail;
	/**备注*/
	@Excel(name = "备注", width = 15)
    private java.lang.String eqtext;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    private java.lang.String createBy;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date createTime;
	/**更新人*/
	@Excel(name = "更新人", width = 15)
    private java.lang.String updateBy;
	/**更新日期*/
	@Excel(name = "更新日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date updateTime;
}
