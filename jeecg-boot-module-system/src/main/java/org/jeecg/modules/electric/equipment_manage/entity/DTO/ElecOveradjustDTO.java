package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;


@Data
public class ElecOveradjustDTO {
    private java.lang.String id;
    private java.lang.String eqid;

    private java.lang.String eqname;
    private java.lang.String eqmodel;

    private java.util.Date eqoverdatelast;
    private java.lang.String eqoverstate;
    private java.util.Date eqadjustdatelast;
    private java.lang.String eqadjuststate;

    private java.lang.String eqtext;

}
