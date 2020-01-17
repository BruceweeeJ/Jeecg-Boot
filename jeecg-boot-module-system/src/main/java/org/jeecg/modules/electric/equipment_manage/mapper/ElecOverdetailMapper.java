package org.jeecg.modules.electric.equipment_manage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOverdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOverdetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: ELEC_OVERDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface ElecOverdetailMapper extends BaseMapper<ElecOverdetail> {
    @Select("SELECT ELEC_OVERDETAIL.ID,ELEC_EQUIPMENT.eqcode, ELEC_EQUIPMENT.EQNAME ,ELEC_EQUIPMENT.EQMODEL,ELEC_OVERDETAIL.EQOVERDATE,ELEC_OVERDETAIL.EQOVERCONDITION," +
            "ELEC_OVERDETAIL.EQOVERUNIT,ELEC_OVERDETAIL.EQOVERDESCRIBE FROM ELEC_OVERDETAIL LEFT join ELEC_EQUIPMENT on ELEC_OVERDETAIL.EQID = ELEC_EQUIPMENT.ID order by ELEC_OVERDETAIL.CREATE_TIME")
    List<ElecOverdetailDTO> getElecOverdetailList(Page<ElecOverdetailDTO> page);

    @Select("SELECT * FROM ELEC_OVERDETAIL LEFT join ELEC_EQUIPMENT on ELEC_OVERDETAIL.EQID = ELEC_EQUIPMENT.ID " +
            "where ELEC_OVERDETAIL.ID=#{id}")
    ElecOveradjustdetailDTO lookDetail(@Param("id") String id);
}
