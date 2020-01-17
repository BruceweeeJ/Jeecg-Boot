package org.jeecg.modules.electric.equipment_manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.ElecEquipment;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecEquipmentMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecEquipmentService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: ELEC_EQUIPMENT
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class ElecEquipmentServiceImpl extends ServiceImpl<ElecEquipmentMapper, ElecEquipment> implements IElecEquipmentService {
    @Resource
    private ElecEquipmentMapper equipmentMapper;
    @Override
    public Page<ElecEquipment> list(Page<ElecEquipment> page) {
        return page.setRecords(equipmentMapper.getElecEquipmentList(page));
    }
}
