package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;

@Data
public class ElecUsedetailDTO {
    private java.lang.String id;

    private java.lang.String eqcode;
    private java.lang.String eqid;
    private java.lang.String eqname;
    private java.lang.String eqmodel;

    private java.lang.String equsesituation;
    private java.util.Date equsedate;
    private java.lang.String equseunit;
    private java.lang.String equsepeople;
    private java.lang.String equseadmin;
    private java.lang.String eqflagstate;

    private java.util.Date eqreturndate;
    private java.lang.String equsestate;
    private java.lang.String eqstatedetail;
    private java.lang.String eqtext;
}
