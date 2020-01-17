package org.jeecg.modules.electric.equipment_manage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecAdjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecAdjustdetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: ELEC_ADJUSTDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface ElecAdjustdetailMapper extends BaseMapper<ElecAdjustdetail> {
    @Select("SELECT ELEC_ADJUSTDETAIL.ID,ELEC_EQUIPMENT.eqcode, ELEC_EQUIPMENT.EQNAME ,ELEC_EQUIPMENT.EQMODEL," +
            "ELEC_EQUIPMENT.EQADJUST,ELEC_ADJUSTDETAIL.EQADJUSTDATE,ELEC_ADJUSTDETAIL.EQADJUSTCONDITION,ELEC_ADJUSTDETAIL.EQADJUSTUNIT," +
            "ELEC_ADJUSTDETAIL.EQADJUSTDESCRIBE FROM ELEC_ADJUSTDETAIL LEFT join ELEC_EQUIPMENT on ELEC_ADJUSTDETAIL.EQID = ELEC_EQUIPMENT.ID " +
            "order by ELEC_ADJUSTDETAIL.CREATE_TIME")
    List<ElecAdjustdetailDTO> getElecOverdetailList(Page<ElecAdjustdetailDTO> page);

    @Select("SELECT * FROM ELEC_ADJUSTDETAIL LEFT join ELEC_EQUIPMENT on ELEC_ADJUSTDETAIL.EQID = ELEC_EQUIPMENT.ID " +
            "where ELEC_ADJUSTDETAIL.ID=#{id}")
    ElecOveradjustdetailDTO lookDetail(@Param("id") String id);
}
