package org.jeecg.modules.electric.equipment_manage.mapper;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecBatteryDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecBattery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: ELEC_BATTERY
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface ElecBatteryMapper extends BaseMapper<ElecBattery> {
    @Select("SELECT * FROM ELEC_BATTERY LEFT join ELEC_EQUIPMENT on ELEC_BATTERY.ID = ELEC_EQUIPMENT.ID order by ELEC_BATTERY.CREATE_TIME")
    List<ElecBatteryDTO> getBatteryList(Page<ElecBatteryDTO>page);

    @Select("SELECT * FROM ELEC_BATTERY LEFT join ELEC_EQUIPMENT on ELEC_BATTERY.ID = ELEC_EQUIPMENT.ID " +
            "left join ELEC_USE on ELEC_BATTERY.ID = ELEC_USE.ID where ELEC_BATTERY.ID=#{id}")
    ElecBatteryDTO lookDetail(@Param("id") String id);
}
