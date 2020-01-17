package org.jeecg.modules.electric.equipment_manage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUseDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUsedetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUsedetail;

/**
 * @Description: ELEC_USE
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface ElecUseMapper extends BaseMapper<ElecUse> {
    @Select("SELECT * FROM ELEC_USE LEFT join ELEC_EQUIPMENT on ELEC_USE.ID = ELEC_EQUIPMENT.ID order by ELEC_USE.CREATE_TIME")
    List<ElecUseDTO> getUseList(Page<ElecUseDTO> page);

    @Select("SELECT * FROM ELEC_USE LEFT join ELEC_EQUIPMENT on ELEC_USE.ID = ELEC_EQUIPMENT.ID where  ELEC_USE.ID=#{id}")
    ElecUseDTO lookDetail(@Param("id") String id);

    @Select("select * from (select * from ELEC_USEDETAIL where EQID = #{id} order by EQUSEDATE desc) where rownum = 1")
    ElecUsedetail getElecusedetail(@Param("id")String id);

    @Select("select * from (select * from ELEC_USEDETAIL  LEFT join ELEC_EQUIPMENT on ELEC_USEDETAIL.EQID = ELEC_EQUIPMENT.ID  " +
            "where ELEC_USEDETAIL.EQID = 'dad095e6562a470da1fee61115e37734' order by ELEC_USEDETAIL.EQUSEDATE desc)   where rownum = 1")
    ElecUsedetailDTO getReturninfo(@Param("id") String id);


}
