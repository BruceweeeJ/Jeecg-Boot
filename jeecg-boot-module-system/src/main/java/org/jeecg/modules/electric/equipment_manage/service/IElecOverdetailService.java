package org.jeecg.modules.electric.equipment_manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOverdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOverdetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ELEC_OVERDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IElecOverdetailService extends IService<ElecOverdetail> {
    public Page<ElecOverdetailDTO> list(Page<ElecOverdetailDTO>page);
}
