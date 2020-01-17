package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;

@Data
public class ElecAdjustdetailDTO {
    private java.lang.String id;

    private java.lang.String eqcode;
    private java.lang.String eqname;
    private java.lang.String eqmodel;
    private java.lang.String eqadjust;

    private java.util.Date eqadjustdate;
    private java.lang.String eqadjustcondition;
    private java.lang.String eqadjustunit;
    private java.lang.String eqadjustdescribe;

    private java.lang.String eqtext;
}
