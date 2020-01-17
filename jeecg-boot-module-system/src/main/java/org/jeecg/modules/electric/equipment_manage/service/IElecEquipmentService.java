package org.jeecg.modules.electric.equipment_manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.ElecEquipment;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ELEC_EQUIPMENT
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IElecEquipmentService extends IService<ElecEquipment> {
    public Page<ElecEquipment> list(Page<ElecEquipment>page);
}
