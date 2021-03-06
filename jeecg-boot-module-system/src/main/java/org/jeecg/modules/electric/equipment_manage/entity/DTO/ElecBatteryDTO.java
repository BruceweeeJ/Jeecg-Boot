package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;

@Data
public class ElecBatteryDTO {
    private java.lang.String id;

    private java.lang.String eqcode;
    private java.lang.String eqname;
    private java.lang.String eqmodel;

    private int equseyear;
    private java.util.Date equsedate;
    private java.lang.String eqtext;
}
