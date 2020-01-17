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
 * @Description: ELEC_EQUIPMENT
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Data
@TableName("elec_equipment")
public class ElecEquipment implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**id*/
	@TableId(type = IdType.ID_WORKER_STR)
    private java.lang.String id;
	/**设备编号*/
	@Excel(name = "设备编号", width = 15)
    private java.lang.String eqcode;
	/**设备名称*/
	@Excel(name = "设备名称", width = 15)
    private java.lang.String eqname;
	/**规格型号*/
	@Excel(name = "设备型号", width = 15)
    private java.lang.String eqmodel;
	/**出厂日期*/
	@Excel(name = "出厂日期", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private java.util.Date eqdate;
	/**保修时间*/
	@Excel(name = "保修年限", width = 15)
    private java.lang.Integer eqwarrantydate;
	/**使用温度（下限）*/
	@Excel(name = "使用温度（下限）", width = 15)
    private java.lang.Integer equsetemlow;
	/**使用温度（上限）*/
	@Excel(name = "使用温度（上限）", width = 15)
    private java.lang.Integer equsetemhigh;
	/**使用湿度（下限）*/
	@Excel(name = "使用湿度（下限）", width = 15)
    private java.lang.Integer equsewetlow;
	/**使用湿度（上限）*/
	@Excel(name = "使用湿度（上限）", width = 15)
    private java.lang.Integer equsewethigh;
	/**使用天气*/
	@Excel(name = "使用天气", width = 15)
    private java.lang.String equseweather;
	/**存储温度（下限）*/
	@Excel(name = "存储温度（下限）", width = 15)
    private java.lang.Integer eqstoretemlow;
	/**存储温度（上限）*/
	@Excel(name = "存储温度（上限）", width = 15)
    private java.lang.Integer eqstoretemhigh;
	/**存储湿度（下限）*/
	@Excel(name = "存储湿度（下限）", width = 15)
    private java.lang.Integer eqstorewetlow;
	/**存储湿度（上限）*/
	@Excel(name = "存储湿度（上限）", width = 15)
    private java.lang.Integer eqstorewethigh;
	/**检修周期*/
	@Excel(name = "检修周期", width = 15)
    private java.lang.String eqoverhaul;
	/**电池类型*/
	@Excel(name = "电池类型", width = 15)
    private java.lang.String eqbatype;
	/*制造厂家*/
	@Excel(name = "制造厂家", width = 15)
	private java.lang.String eqmaker;
	/**制造厂家联系方式*/
	@Excel(name = "制造厂家联系方式", width = 15)
	private java.lang.String eqmakercon;
	/**销售厂家*/
	@Excel(name = "销售厂家", width = 15)
	private java.lang.String eqsaler;
	/**销售厂家联系方式*/
	@Excel(name = "销售厂家联系方式", width = 15)
	private java.lang.String eqsalercon;
	/**校准周期*/
	@Excel(name = "校准周期", width = 15)
    private java.lang.String eqadjust;
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
