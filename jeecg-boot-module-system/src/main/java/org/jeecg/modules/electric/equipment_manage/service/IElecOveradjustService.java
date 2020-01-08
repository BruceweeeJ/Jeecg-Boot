package org.jeecg.modules.electric.equipment_manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustdetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecOveradjust;
import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecOveradjustDTO;


/**
 * @Description: ELEC_OVERADJUST
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IElecOveradjustService extends IService<ElecOveradjust> {
    public Page<ElecOveradjustDTO> list(Page<ElecOveradjustDTO> page);
    public ElecOveradjustdetailDTO lookDetail(String ID);
}
