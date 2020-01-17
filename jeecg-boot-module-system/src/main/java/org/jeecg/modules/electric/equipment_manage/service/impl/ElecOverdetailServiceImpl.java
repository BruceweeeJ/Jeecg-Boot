package org.jeecg.modules.electric.equipment_manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOverdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOverdetail;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecOverdetailMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecOverdetailService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;

/**
 * @Description: ELEC_OVERDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class ElecOverdetailServiceImpl extends ServiceImpl<ElecOverdetailMapper, ElecOverdetail> implements IElecOverdetailService {
    @Resource
    private ElecOverdetailMapper elecOverdetailMapper;
    @Override
    public Page<ElecOverdetailDTO> list(Page<ElecOverdetailDTO> page) {
        return page.setRecords(elecOverdetailMapper.getElecOverdetailList(page));
    }

}
