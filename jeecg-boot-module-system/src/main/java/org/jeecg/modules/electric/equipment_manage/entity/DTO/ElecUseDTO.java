package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;

@Data
public class ElecUseDTO {
    private java.lang.String id;
    private java.lang.String eqname;
    private java.lang.String eqmodel;

    /**设备编号*/
    private java.lang.String eqcode;
    /**领用状态*/
    private java.lang.String eqflag;
    /**近期使用日期*/
    private java.util.Date equsedate;
    /**设备使用情况*/
    private java.lang.String equsestate;
    /**设备情况详细描述*/
    private java.lang.String eqstatedetail;


}
