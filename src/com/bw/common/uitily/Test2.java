package com.bw.common.uitily;

import java.util.*;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import org.activiti.engine.FormService;  
import org.activiti.engine.ProcessEngine;  
import org.activiti.engine.RepositoryService;  
import org.activiti.engine.RuntimeService;  
import org.activiti.engine.TaskService;  
import org.activiti.engine.form.FormProperty;  
import org.activiti.engine.task.Task;  
import org.springframework.context.support.ClassPathXmlApplicationContext;  
public class Test2 {  
   /** 
    * @param args 
    */  
   public static void main(String[] args) {  
       // 加载spring配置  
       ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(  
               "springAppContext.xml");  
       RuntimeService runtimeService = (RuntimeService) ctx  
               .getBean("runtimeService");  
       ProcessEngine processEngine = (ProcessEngine) ctx  
               .getBean("processEngine");  
       FormService formService = (FormService) ctx.getBean("formService");  
       TaskService taskService = (TaskService) ctx.getBean("taskService");  
       // 发布流程  
       RepositoryService repositoryService = processEngine  
               .getRepositoryService();  
       repositoryService.createDeployment()  
               .addClasspathResource("MyProcess3.bpmn").deploy();  
       fill(runtimeService, formService, taskService);  
       singleCheck(formService, taskService);  
       sum(taskService, formService);  
   }  
   /** 
    * 填报预算 
    * 
    * @param runtimeService 
    * @param formService 
    * @param taskService 
    */  
   private static void fill(RuntimeService runtimeService,  
           FormService formService, TaskService taskService) {  
       // 流程开始参数，设置填报期间  
       Map<String, Object> p = new HashMap<String, Object>();  
       p.put("period", "2013-07");  
       // 生成3家单位填报  
       for (int i = 0; i < 3; i++) {  
           // 开始流程  
           runtimeService.startProcessInstanceByKey("formDataTest", p);  
           System.out  
                   .println("=====================kermit开始填报=======================");  
           // query kermit's tasks;  
           List<Task> tasks = taskService.createTaskQuery()  
                   .taskAssignee("kermit").list();  
           for (Task task : tasks) {  
               if ("fill".equals(task.getTaskDefinitionKey())) {  
                   // 设置填报人单位编码记录在节点  
                   taskService.setVariableLocal(task.getId(), "accoutCode",  
                           "A1104" + i);  
                   // 设置该流程实例的填报单位  
                   taskService.setVariable(task.getId(), "fillAccount", "A110"  
                           + i);  
                   Map<String, FormProperty> propMap = createMap(formService  
                           .getTaskFormData(task.getId()).getFormProperties());  
                   // 获取节点报表打开类型  
                   String fillType = (String) propMap.get("fillType").getValue();  
                   // 获取节点报表表单ID  
                   String sheetId = (String) propMap.get("sheetId").getValue();  
                   // 获取节点是否需要汇总参数  
                   String isSum = (String) propMap.get("isSum").getValue();  
                   // 获取节点填报单位编码  
                   String accoutCode = (String) taskService.getVariableLocal(  
                           task.getId(), "accoutCode");  
                   // 获取流程填报单位编码  
                   String fillAccount = (String) taskService.getVariable(  
                           task.getId(), "fillAccount");  
                   // 获取流程填报期间  
                   String period = (String) taskService.getVariable(  
                           task.getId(), "period");   
                   // 打印填报信息  
                   System.out.println("\t打开报表类型：" + fillType);  
                   System.out.println("\t填报表单：" + sheetId);  
                   System.out.println("\t填报期间：" + period);  
                   System.out.println("\t是否汇总：" + isSum);  
                   System.out.println("\t当前节点账号：" + accoutCode);  
                   System.out.println("\t提交审批表单填报单位：" + fillAccount);  
                   // 节点任务结束  
                   taskService.complete(task.getId());  
                   System.out  
                           .println("=============kermit填写预算单任务已完成=====================");  
                   System.out.println();  
               }  
           }  
       }  
   }  
   /** 
     * 单个审核 
     * 
     * @param formService 
     * @param taskService 
     */  
    private static void singleCheck(FormService formService,  
            TaskService taskService) {  
        System.out  
                .println("=====================fozzie开始单个审核=======================");  
        // query fozzie's tasks;  
        List<Task> tasks2 = taskService.createTaskQuery()  
                .taskAssignee("fozzie")  
                .processVariableValueEquals("period", "2013-07")  
                .processDefinitionKey("formDataTest").list();  
        int count = 1;  
        for (Task task : tasks2) {  
            if ("check".equals(task.getTaskDefinitionKey())) {  
                System.out.println("\t审核第" + count + "个");  
                // 设置节点审批人单位编码  
                taskService.setVariableLocal(task.getId(), "accoutCode",  
                        "A1199");  
                Map<String, FormProperty> propMap = createMap(formService  
                        .getTaskFormData(task.getId()).getFormProperties());  
                // 获取节点报表打开类型  
                String fillType = (String) propMap.get("fillType").getValue();  
                // 获取节点报表表单ID  
                String sheetId = (String) propMap.get("sheetId").getValue();  
                // 获取节点是否需要汇总参数  
                String isSum = (String) propMap.get("isSum").getValue();  
                // 获取节点填报单位编码  
                String accoutCode = (String) taskService.getVariableLocal(  
                        task.getId(), "accoutCode");  
                // 获取流程填报单位编码  
                String fillAccount = (String) taskService.getVariable(  
                        task.getId(), "fillAccount");  
                // 获取流程填报期间  
                String period = (String) taskService.getVariable(task.getId(),  
                        "period");  
                // 打印填报信息  
                System.out.println("\t报表打开类型：" + fillType);  
                System.out.println("\t填报表单：" + sheetId);  
                System.out.println("\t填报期间：" + period);  
                System.out.println("\t是否汇总：" + isSum);  
                System.out.println("\t当前节点账号：" + accoutCode);  
                System.out.println("\t提交审批表单填报单位：" + fillAccount);  
                // 节点任务结束  
                taskService.complete(task.getId());  
                System.out.println("\t审核第" + count + "个完成");  
                System.out.println();  
                count++;  
            }  
        }  
        System.out  
                .println("===================fozzie审批预算单任务已完成===================");  
        System.out.println();  
    }  
    /** 
     * 汇总审核 
     * 
     * @param taskService 
     * @param formService 
     */  
    private static void sum(TaskService taskService, FormService formService) {  
        System.out  
                .println("=======================admin开始汇总审核=====================");  
        List<Task> tasks3 = taskService.createTaskQuery()  
                .taskAssignee("admin")  
                .processVariableValueEquals("period", "2013-07")  
                .processDefinitionKey("formDataTest").list();  
        // 批量审批列表  
        List<String> completeTaskIdList = new ArrayList<String>();  
        // 获取节点报表打开类型  
        String fillType = "";  
        // 获取节点报表表单ID  
        String sheetId = "";  
        // 获取节点是否需要汇总参数  
        String isSum = "";  
        // 获取流程填报期间  
        String period = "";  
        for (Task task : tasks3) {  
            if ("sum".equals(task.getTaskDefinitionKey())) {  
                // 设置节点审批人单位编码  
                taskService.setVariableLocal(task.getId(), "accoutCode",  
                        "A1199");  
                // 获取流程填报单位编码  
                String fillAccount = (String) taskService.getVariable(  
                        task.getId(), "fillAccount");  
                Map<String, FormProperty> propMap = createMap(formService  
                        .getTaskFormData(task.getId()).getFormProperties());  
                // 获取节点报表打开类型  
                fillType = (String) propMap.get("fillType").getValue();  
                // 获取节点报表表单ID  
                sheetId = (String) propMap.get("sheetId").getValue();  
                // 获取节点是否需要汇总参数  
                isSum = (String) propMap.get("isSum").getValue();  
                // 获取流程填报期间  
                period = (String) taskService.getVariable(task.getId(),  
                        "period");  
                // 打印填报信息  
                System.out.println("\t提交汇总审批表单填报单位：" + fillAccount);  
                // 加入批量审批列表  
                completeTaskIdList.add(task.getId());  
            }  
        }  
        System.out.println("\t报表打开类型：" + fillType);  
        System.out.println("\t填报表单：" + sheetId);  
        System.out.println("\t填报期间：" + period);  
        System.out.println("\t是否汇总：" + isSum);  
        System.out.println("\t全部审批通过");  
        for (String t : completeTaskIdList) {  
            taskService.complete(t);  
        }  
        System.out.println("=========admin汇总预算单任务已完成，汇总审批"  
                + completeTaskIdList.size() + "个单位=======");  
    }  
    private static Map<String, FormProperty> createMap(List<FormProperty> props) {  
        Map<String, FormProperty> re = new HashMap<String, FormProperty>();  
        for (FormProperty p : props) {  
            re.put(p.getId(), p);  
        }  
        return re;  
    }  
}
