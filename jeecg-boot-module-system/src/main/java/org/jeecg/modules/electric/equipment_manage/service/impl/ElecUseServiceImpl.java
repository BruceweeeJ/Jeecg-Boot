package org.jeecg.modules.electric.equipment_manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUseDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUse;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecUseMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecUseService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: ELEC_USE
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class ElecUseServiceImpl extends ServiceImpl<ElecUseMapper, ElecUse> implements IElecUseService {
    @Resource
    private ElecUseMapper elecUseMapper;
    @Override
    public Page<ElecUseDTO> list(Page<ElecUseDTO> page) {
        return page.setRecords(elecUseMapper.getUseList(page));
    }

    @Override
    public ElecUseDTO lookDetail(String id) {
        return elecUseMapper.lookDetail(id);
    }
}
