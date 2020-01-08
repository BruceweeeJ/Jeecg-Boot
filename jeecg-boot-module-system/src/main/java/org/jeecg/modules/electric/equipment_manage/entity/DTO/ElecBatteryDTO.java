package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;

@Data
public class ElecBatteryDTO {
    private java.lang.String id;
    /**所属设备*/
    private java.lang.String eqname;
    /**电池编号*/
    private java.lang.String eqbatcode;
    /**电池名称*/
    private java.lang.String eqbatname;
    /**电池型号*/
    private java.lang.String eqbatmodel;
    /**上次充电日期*/
    private java.util.Date eqchargedate;
    /**电池充电状态*/
    private java.lang.String eqchargestate;
    /**电池使用状态*/
    private java.lang.String eqbatuesstate;
}
