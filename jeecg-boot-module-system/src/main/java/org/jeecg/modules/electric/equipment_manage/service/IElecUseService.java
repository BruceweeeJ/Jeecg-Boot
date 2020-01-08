package org.jeecg.modules.electric.equipment_manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUseDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ELEC_USE
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IElecUseService extends IService<ElecUse> {
    public Page<ElecUseDTO> list(Page<ElecUseDTO> page);
    public ElecUseDTO lookDetail(String id);
}
