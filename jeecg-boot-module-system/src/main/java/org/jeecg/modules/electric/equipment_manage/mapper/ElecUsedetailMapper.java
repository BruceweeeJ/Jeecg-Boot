package org.jeecg.modules.electric.equipment_manage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUsedetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUsedetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: ELEC_USEDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface ElecUsedetailMapper extends BaseMapper<ElecUsedetail> {
    @Select("SELECT * FROM ELEC_USEDETAIL LEFT join ELEC_EQUIPMENT on ELEC_USEDETAIL.EQID = ELEC_EQUIPMENT.ID\n" +
            "where ELEC_USEDETAIL.EQID=#{eqid} order by ELEC_USEDETAIL.EQUSEDATE desc ")
    List<ElecUsedetailDTO> getElecUsedetailList(Page<ElecUsedetailDTO> page,@Param("eqid") String eqid);

    @Select("SELECT * FROM ELEC_USEDETAIL LEFT join ELEC_EQUIPMENT on ELEC_USEDETAIL.EQID = ELEC_EQUIPMENT.ID where  ELEC_USEDETAIL.ID=#{id}")
    ElecUsedetailDTO lookDetail(@Param("id") String id);
}
