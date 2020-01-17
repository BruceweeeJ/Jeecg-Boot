package org.jeecg.modules.electric.equipment_manage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUsedetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUsedetail;
import org.jeecg.modules.electric.equipment_manage.mapper.ElecUsedetailMapper;
import org.jeecg.modules.electric.equipment_manage.service.IElecUsedetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: ELEC_USEDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
@Service
public class ElecUsedetailServiceImpl extends ServiceImpl<ElecUsedetailMapper, ElecUsedetail> implements IElecUsedetailService {
    @Autowired
    private ElecUsedetailMapper usedetailMapper;
    @Override
    public Page<ElecUsedetailDTO> list(Page<ElecUsedetailDTO> page,String eqid) {
        return page.setRecords(usedetailMapper.getElecUsedetailList(page,eqid));
    }
}
