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
    @Select("SELECT ELEC_BATTERY.ID,ELEC_BATTERY.EQBATCODE,ELEC_BATTERY.EQBATNAME,ELEC_BATTERY.EQBATMODEL,\n" +
            "ELEC_EQUIPMENT.EQNAME,ELEC_BATTERY.EQCHARGEDATE,ELEC_BATTERY.EQCHARGESTATE,ELEC_BATTERY.EQBATUESSTATE\n" +
            "FROM ELEC_BATTERY LEFT join ELEC_EQUIPMENT on ELEC_BATTERY.EQID = ELEC_EQUIPMENT.ID")
    List<ElecBatteryDTO> getBatteryList(Page<ElecBatteryDTO>page);
}
