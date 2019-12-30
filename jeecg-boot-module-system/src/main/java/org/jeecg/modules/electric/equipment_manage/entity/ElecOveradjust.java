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
 * @Description: ELEC_OVERADJUST
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Data
@TableName("elec_overadjust")
public class ElecOveradjust implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**设备ID*/
	@Excel(name = "设备ID", width = 15)
    private java.lang.String eqid;
	/**检修日期*/
	@Excel(name = "检修日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date eqoverdatelast;
	/**检修状态*/
	@Excel(name = "检修状态", width = 15)
    private java.lang.String eqoverstate;
	/**检修情况*/
	@Excel(name = "检修情况", width = 15)
    private java.lang.String eqoverconditionlast;
	/**检修单位*/
	@Excel(name = "检修单位", width = 15)
    private java.lang.String eqoverunitlast;
	/**校准日期*/
	@Excel(name = "校准日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date eqadjustdatelast;
	/**校准状态*/
	@Excel(name = "校准状态", width = 15)
    private java.lang.String eqadjuststate;
	/**校准情况*/
	@Excel(name = "校准情况", width = 15)
    private java.lang.String eqadjustconditionlast;
	/**校准单位*/
	@Excel(name = "校准单位", width = 15)
    private java.lang.String eqadjustunitlast;
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
