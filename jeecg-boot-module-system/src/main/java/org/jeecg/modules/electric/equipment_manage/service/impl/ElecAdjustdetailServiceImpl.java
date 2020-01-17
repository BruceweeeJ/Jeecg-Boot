package org.jeecg.modules.electric.equipment_manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecAdjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecAdjustdetail;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecAdjustdetailMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecAdjustdetailService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: ELEC_ADJUSTDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class ElecAdjustdetailServiceImpl extends ServiceImpl<ElecAdjustdetailMapper, ElecAdjustdetail> implements IElecAdjustdetailService {
    @Resource
    private ElecAdjustdetailMapper elecAdjustdetailMapper;
    @Override
    public Page<ElecAdjustdetailDTO> list(Page<ElecAdjustdetailDTO> page) {
        return page.setRecords(elecAdjustdetailMapper.getElecOverdetailList(page));
    }
}
