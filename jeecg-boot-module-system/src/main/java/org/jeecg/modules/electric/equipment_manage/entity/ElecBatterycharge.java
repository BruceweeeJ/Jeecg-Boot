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
 * @Description: ELEC_BATTERYCHARGE
 * @Author: jeecg-boot
 * @Date:   2019-12-28
 * @Version: V1.0
 */
@Data
@TableName("elec_batterycharge")
public class ElecBatterycharge implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**电池ID*/
	@Excel(name = "电池ID", width = 15)
    private java.lang.String eqbatid;
	/**充电日期*/
	@Excel(name = "充电日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date eqchardate;
	/**充电时长*/
	@Excel(name = "充电时长", width = 15)
    private java.lang.Double eqchartime;
	/**电池管理员*/
	@Excel(name = "电池管理员", width = 15)
    private java.lang.String eqbatadmin;
	/**电池使用状态*/
	@Excel(name = "电池使用状态", width = 15)
    private java.lang.String equesstate;
	/**电池充电详情*/
	@Excel(name = "电池充电详情", width = 15)
    private java.lang.String eqcharge;
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
