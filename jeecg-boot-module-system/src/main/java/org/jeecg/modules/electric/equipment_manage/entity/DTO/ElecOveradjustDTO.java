package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;


@Data
public class ElecOveradjustDTO {
    private java.lang.String id;
    private java.lang.String eqid;
    private java.lang.String eqname;
    private java.lang.String eqmodel;

    private java.util.Date eqoverdate;
    private java.lang.String eqoverstate;
    private java.util.Date eqadjustdate;
    private java.lang.String eqadjustcondition;

    private java.lang.String eqoverunit;
    private java.lang.String eqadjustunit;

    private java.lang.String eqtext;

}
