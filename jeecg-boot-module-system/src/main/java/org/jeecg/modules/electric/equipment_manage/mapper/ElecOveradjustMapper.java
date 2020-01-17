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
    @Select("SELECT * FROM ELEC_OVERADJUST LEFT join ELEC_EQUIPMENT on ELEC_OVERADJUST.ID = ELEC_EQUIPMENT.ID order by ELEC_OVERADJUST.CREATE_TIME")
    List<ElecOveradjustDTO> getOveradjustList(Page<ElecOveradjustDTO> page);

    @Select("SELECT * FROM ELEC_OVERADJUST LEFT join ELEC_EQUIPMENT on ELEC_OVERADJUST.ID = ELEC_EQUIPMENT.ID where ELEC_OVERADJUST.ID=#{ID}")
    ElecOveradjustdetailDTO lookDetail(@Param("ID")String ID);

    @Select("SELECT * FROM ELEC_OVERDETAIL LEFT join ELEC_EQUIPMENT on ELEC_OVERDETAIL.EQID = ELEC_EQUIPMENT.ID " +
            "where ELEC_OVERDETAIL.EQID=#{eqid} order by ELEC_OVERDETAIL.EQOVERDATE desc ")
    List<ElecOveradjustdetailDTO> lookOverdetail(Page<ElecOveradjustdetailDTO> page,@Param("eqid") String eqid);

    @Select("SELECT * FROM ELEC_ADJUSTDETAIL LEFT join ELEC_EQUIPMENT on ELEC_ADJUSTDETAIL.EQID = ELEC_EQUIPMENT.ID " +
            "where ELEC_ADJUSTDETAIL.EQID=#{eqid} order by ELEC_ADJUSTDETAIL.EQADJUSTDATE desc ")
    List<ElecOveradjustdetailDTO> lookAdjustdetail(Page<ElecOveradjustdetailDTO> page,@Param("eqid") String eqid);
}
