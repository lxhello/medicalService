package com.medical.service;

import com.medical.entity.LbDoctor;
import com.medical.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;

import java.util.List;

/**
 * @author: lixiang
 * @date: 2022/5/7
 * @version: 1.0版本
 */
public interface LbDoctorService {
    //查医生集合
    PageQuery<LbDoctor> findList(Integer pageNo, Integer pageSize,String name,String certId);

    //查询所有
    List<LbDoctor> findAll();

    /**
     * 保存
     */
    ResponseResult insertDoctor(LbDoctor lbDoctor);

    /**
     * 更新记录
     */
    ResponseResult updateDoctor(LbDoctor lbDoctor);

    /**
     * 根据主键id查询医生
     */
    LbDoctor findOne(Integer id);

    /**
     * 删除医生
     */
    ResponseResult deleteDoctor(Integer id);

    /**
     * 根据部门id获取医生
     */
    List<LbDoctor> getListByDepartment(String department);

    /**
     * 根据userID查询医生
     */
    LbDoctor findOneByUserId(Integer userId);
}
