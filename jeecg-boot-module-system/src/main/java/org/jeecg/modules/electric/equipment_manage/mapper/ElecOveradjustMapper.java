package org.jeecg.modules.electric.equipment_manage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOveradjust;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustDTO;


/**
 * @Description: ELEC_OVERADJUST
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface ElecOveradjustMapper extends BaseMapper<ElecOveradjust> {
    @Select("SELECT ELEC_OVERADJUST.ID,ELEC_OVERADJUST.EQID,ELEC_EQUIPMENT.EQNAME,ELEC_EQUIPMENT.EQMODEL,ELEC_OVERADJUST.EQOVERDATELAST,\n" +
            "\t\t\t ELEC_OVERADJUST.EQOVERSTATE,ELEC_OVERADJUST.EQADJUSTDATELAST,ELEC_OVERADJUST.EQADJUSTSTATE,ELEC_OVERADJUST.EQTEXT\n" +
            "FROM ELEC_OVERADJUST LEFT join ELEC_EQUIPMENT on ELEC_OVERADJUST.ID = ELEC_EQUIPMENT.ID")
    List<ElecOveradjustDTO> getOveradjustList(Page<ElecOveradjustDTO> page);

    @Select("SELECT * FROM ELEC_OVERADJUST LEFT join ELEC_EQUIPMENT on ELEC_OVERADJUST.ID = ELEC_EQUIPMENT.ID where ELEC_OVERADJUST.ID=#{ID}")
    ElecOveradjustdetailDTO lookDetail(@Param("ID")String ID);
}
