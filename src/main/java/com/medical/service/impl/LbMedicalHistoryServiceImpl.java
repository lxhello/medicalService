package com.medical.service.impl;

import com.medical.common.Global;
import com.medical.dao.LbMedicalHistoryDao;
import com.medical.entity.LbMedicalHistory;
import com.medical.service.LbMedicalHistoryService;
import com.medical.vo.QueryVo;
import com.medical.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author: lixiang
 * @date: 2022/5/13
 * @version: 1.0版本
 */
@Service
public class LbMedicalHistoryServiceImpl implements LbMedicalHistoryService {
    @Autowired
    private LbMedicalHistoryDao lbMedicalHistoryDao;
    @Override
    public PageQuery<LbMedicalHistory> findList(QueryVo queryVo) {
        PageQuery<LbMedicalHistory> query = new PageQuery(queryVo.getPageNo(),queryVo.getPageSize());
        if (!StringUtils.isEmpty(queryVo.getPatientName())) {
            query.setPara("patientName",queryVo.getPatientName());
        }
        if (!StringUtils.isEmpty(queryVo.getDoctorName())) {
            query.setPara("doctorName",queryVo.getDoctorName());
        }
        if (queryVo.getUserId() != null) {
            query.setPara("userId",queryVo.getUserId());
        }
        if (queryVo.getPatientId() != null) {
            query.setPara("patientId",queryVo.getPatientId());
        }
        query.setOrderBy("m.id desc");
        lbMedicalHistoryDao.selectList(query);
        return query;
    }

    @Override
    public ResponseResult insertMedicalHistory(LbMedicalHistory lbMedicalHistory) {
        ResponseResult result = new ResponseResult();
        lbMedicalHistoryDao.insert(lbMedicalHistory);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public ResponseResult updateMedicalHistory(LbMedicalHistory lbMedicalHistory) {
        ResponseResult result = new ResponseResult();
        lbMedicalHistoryDao.updateById(lbMedicalHistory);
        result.setCode(Global.SAVE_CODE_SUCCESS);
        result.setMessage(Global.SAVE_MSG_SUCCESS);
        return result;
    }

    @Override
    public LbMedicalHistory findOne(Integer id) {
        return lbMedicalHistoryDao.single(id);
    }

    @Override
    public ResponseResult deleteById(Integer id) {
        ResponseResult result = new ResponseResult();

        int rows = lbMedicalHistoryDao.deleteById(id);
        if (rows > 0) {
            result.setCode(Global.DEL_CODE_SUCCESS);
            result.setMessage(Global.DEL_MSG_SUCCESS);
        } else {
            result.setCode(Global.DEL_CODE_ERROR);
            result.setMessage(Global.DEL_MSG_ERROR);
        }
        return result;
    }
}
