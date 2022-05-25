package com.medical.controller.admin;

import com.medical.entity.LbDoctor;
import com.medical.entity.LbMedicalHistory;
import com.medical.entity.LbPatient;
import com.medical.service.LbDoctorService;
import com.medical.service.LbMedicalHistoryService;
import com.medical.service.LbPatientService;
import com.medical.vo.QueryVo;
import com.medical.vo.ResponseResult;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 科目管理控制
 * @author: lixiang
 * @date: 2022/5/7
 * @version: 1.0版本
 */
@Controller
@RequestMapping("/admin/medicalHistory")
public class MedicalHistoryController {
    @Autowired
    private LbMedicalHistoryService lbMedicalHistoryService;
    @Autowired
    private LbPatientService lbPatientService;
    @Autowired
    private LbDoctorService lbDoctorService;

    @ModelAttribute("doctors")
    public List<LbDoctor> getDoctors() {
        return lbDoctorService.findAll();
    }
    @ModelAttribute("patients")
    public List<LbPatient> getPatients() {
        return lbPatientService.findAll();
    }

    @RequestMapping("/manage")
    public String manage(QueryVo queryVo,Model model) {
        //分页查询
        PageQuery<LbMedicalHistory> page = lbMedicalHistoryService.findList(queryVo);
        model.addAttribute("page",page);
        model.addAttribute("pageNo",queryVo.getPageNo());
        model.addAttribute("patientName",queryVo.getPatientName());
        model.addAttribute("doctorName",queryVo.getDoctorName());
        model.addAttribute("path","/admin/medicalHistory/manage");
        return "admin/medicalHistoryManage";
    }

    /**
     * 新增
     */
    @RequestMapping("/")
    public String addForm(LbMedicalHistory lbMedicalHistory,Model model) {
        model.addAttribute("medicalHistory",lbMedicalHistory);
        return "admin/MedicalHistoryForm";
    }

    /**
     * 编辑
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editForm(@PathVariable Integer id, Model model) {
        model.addAttribute("medicalHistory",lbMedicalHistoryService.findOne(id));
        return "admin/medicalHistoryForm";
    }

    /**
     * 异步插入记录
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseResult insert(@RequestBody LbMedicalHistory lbMedicalHistory) {
        return lbMedicalHistoryService.insertMedicalHistory(lbMedicalHistory);
    }

    /**
     * 异步更新记录
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseResult update(@RequestBody LbMedicalHistory lbMedicalHistory) {
        return lbMedicalHistoryService.updateMedicalHistory(lbMedicalHistory);
    }

    /**
     * 异步删除
     */
    @ResponseBody
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseResult delete(@PathVariable Integer id){
        return lbMedicalHistoryService.deleteById(id);
    }
}
