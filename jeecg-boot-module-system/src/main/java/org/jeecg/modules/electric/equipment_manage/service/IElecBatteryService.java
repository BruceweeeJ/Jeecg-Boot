package org.jeecg.modules.electric.equipment_manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecBatteryDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecBattery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ELEC_BATTERY
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IElecBatteryService extends IService<ElecBattery> {
    public Page<ElecBatteryDTO> list(Page<ElecBatteryDTO>page);
}
