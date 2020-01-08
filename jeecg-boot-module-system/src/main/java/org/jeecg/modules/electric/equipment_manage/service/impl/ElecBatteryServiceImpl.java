package org.jeecg.modules.electric.equipment_manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecBatteryDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecBattery;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecBatteryMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecBatteryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: ELEC_BATTERY
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class ElecBatteryServiceImpl extends ServiceImpl<ElecBatteryMapper, ElecBattery> implements IElecBatteryService {
    @Resource
    private ElecBatteryMapper elecBatteryMapper;
    @Override
    public Page<ElecBatteryDTO> list(Page<ElecBatteryDTO> page) {
        return page.setRecords(elecBatteryMapper.getBatteryList(page));
    }
}
