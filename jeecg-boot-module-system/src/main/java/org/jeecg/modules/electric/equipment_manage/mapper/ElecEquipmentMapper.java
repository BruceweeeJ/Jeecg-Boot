package org.jeecg.modules.electric.equipment_manage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.electric.equipment_manage.entity.ElecEquipment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: ELEC_EQUIPMENT
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface ElecEquipmentMapper extends BaseMapper<ElecEquipment> {
    @Select("SELECT * FROM ELEC_EQUIPMENT order by CREATE_TIME")
    List<ElecEquipment> getElecEquipmentList(Page<ElecEquipment> page);
}
