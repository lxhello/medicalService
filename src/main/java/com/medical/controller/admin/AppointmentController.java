package com.medical.controller.admin;

import com.medical.entity.LbAppointment;
import com.medical.service.LbAppointmentService;
import com.medical.service.LbPatientService;
import com.medical.vo.QueryVo;
import com.medical.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 预约管理控制
 * @author: lixiang
 * @date: 2022/5/5
 * @version: 1.0版本
 */
@Controller
@RequestMapping("/admin/appointment")
public class AppointmentController {
    @Autowired
    private LbAppointmentService lbAppointmentService;
    @Autowired
    private LbPatientService lbPatientService;

    @RequestMapping("/manage")
    public String manage(QueryVo queryVo,Model model) {
        //查询预约记录
        PageQuery<LbAppointment> page = lbAppointmentService.findList(queryVo);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",queryVo.getPageNo());
        model.addAttribute("patientName",queryVo.getPatientName());
        model.addAttribute("doctorName",queryVo.getDoctorName());
        model.addAttribute("path","/admin/appointment/manage");
        return "admin/appointmentManage";
    }

    /**
     * 新增
     */
    @RequestMapping("/")
    public String doctorAddForm(LbAppointment lbAppointment,Model model) {
        model.addAttribute("patientList",lbPatientService.findAll());
        model.addAttribute("appointment",lbAppointment);
        return "admin/appointmentForm";
    }

    /**
     * 编辑
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String doctorEditForm(@PathVariable Integer id, Model model) {
        model.addAttribute("appointment",lbAppointmentService.findOne(id));
        return "admin/appointmentForm";
    }

    /**
     * 异步插入记录
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody LbAppointment lbAppointment) {
        return lbAppointmentService.insertAppointment(lbAppointment);
    }

    /**
     * 异步更新记录
     * @param lbAppointment
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody LbAppointment lbAppointment) {
        return lbAppointmentService.updateAppointment(lbAppointment);
    }

    /**
     * 异步删除
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable Integer id){
        return lbAppointmentService.deleteById(id);
    }
}
