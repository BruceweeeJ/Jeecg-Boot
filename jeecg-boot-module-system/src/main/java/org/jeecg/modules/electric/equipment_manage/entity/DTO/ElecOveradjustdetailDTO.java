package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;

@Data
public class ElecOveradjustdetailDTO {
    private java.lang.String id;
    /*
        equipment表信息
     */
    private java.lang.String eqcode;
    private java.lang.String eqname;
    private java.lang.String eqmodel;



    /*
        overadjust表信息
     */
    /**检修日期*/
    private java.lang.String eqid;
    private java.util.Date eqoverdate;
    /**检修情况*/
    private java.lang.String eqovercondition;
    /**检修单位*/
    private java.lang.String eqoverunit;
    private java.lang.String eqoverdescribe;

    //校准周期
    private java.lang.String eqadjust;
    /**校准日期*/
    private java.util.Date eqadjustdate;
    /**校准情况*/
    private java.lang.String eqadjustcondition;
    /**校准单位*/
    private java.lang.String eqadjustunit;
    private java.lang.String eqadjustdescribe;

    /**备忘*/
    private java.lang.String eqtext;

}
