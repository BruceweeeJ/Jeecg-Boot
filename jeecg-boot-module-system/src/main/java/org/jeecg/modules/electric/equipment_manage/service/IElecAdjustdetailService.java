package org.jeecg.modules.electric.equipment_manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecAdjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecAdjustdetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ELEC_ADJUSTDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IElecAdjustdetailService extends IService<ElecAdjustdetail> {
    public Page<ElecAdjustdetailDTO> list(Page<ElecAdjustdetailDTO>page);
}
