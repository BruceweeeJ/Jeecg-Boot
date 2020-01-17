package org.jeecg.modules.electric.equipment_manage.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.modules.electric.equipment_manage.entity.DTO.ElecUsedetailDTO;
import org.jeecg.modules.electric.equipment_manage.entity.ElecUsedetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: ELEC_USEDETAIL
 * @Author: jeecg-boot
 * @Date:   2019-12-30
 * @Version: V1.0
 */
public interface IElecUsedetailService extends IService<ElecUsedetail> {
    public Page<ElecUsedetailDTO> list(Page<ElecUsedetailDTO> page,String eqid);
}
