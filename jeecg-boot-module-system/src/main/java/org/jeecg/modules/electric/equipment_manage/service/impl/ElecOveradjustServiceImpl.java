package org.jeecg.modules.electric.equipment_manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOveradjust;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustDTO;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecOveradjustMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecOveradjustService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: ELEC_OVERADJUST
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class ElecOveradjustServiceImpl extends ServiceImpl<ElecOveradjustMapper, ElecOveradjust> implements IElecOveradjustService {
    @Resource
    private ElecOveradjustMapper elecOveradjustMapper;
    @Override
    public Page<ElecOveradjustDTO> list(Page<ElecOveradjustDTO> page) {
        return page.setRecords(elecOveradjustMapper.getOveradjustList(page));
    }

    @Override
    public ElecOveradjustdetailDTO lookDetail(String ID) {
        return elecOveradjustMapper.lookDetail(ID);
    }
}
