package org.jeecg.modules.electric.equipment_manage.entity.DTO;

import lombok.Data;

@Data
public class ElecOveradjustdetailDTO {
    private java.lang.String id;
    /*
        equipment表信息
     */

    private java.lang.String eqname;
    private java.lang.String eqmodel;
    private java.lang.String eqoverhaul;//检修周期
    private java.lang.String eqadjust;//校准周期

    /*
        overadjust表信息
     */
    /**检修日期*/
    private java.lang.String eqid;
    private java.util.Date eqoverdatelast;
    /**检修状态*/
    private java.lang.String eqoverstate;
    /**检修情况*/
    private java.lang.String eqoverconditionlast;
    /**检修单位*/
    private java.lang.String eqoverunitlast;


    /**校准日期*/
    private java.util.Date eqadjustdatelast;
    /**校准状态*/
    private java.lang.String eqadjuststate;
    /**校准情况*/
    private java.lang.String eqadjustconditionlast;
    /**校准单位*/
    private java.lang.String eqadjustunitlast;

    /**备忘*/
    private java.lang.String eqtext;

}
