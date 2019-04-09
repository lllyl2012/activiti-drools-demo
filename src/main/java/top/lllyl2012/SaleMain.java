package top.lllyl2012;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.util.HashMap;
import java.util.Map;

public class SaleMain {
    public static void main(String[] args) {
        ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = pe.getRepositoryService();
        RuntimeService runtimeService = pe.getRuntimeService();
        TaskService taskService = pe.getTaskService();

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("sale.bpmn")
                .addClasspathResource("sale.drl")
                .deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId()).singleResult();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());

        //完成第一个任务
        Task task = taskService.createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();

        Map<String,Object> vars = new HashMap<>();
        Member m = new Member();
        m.setIdentity("gold");
        m.setAmount(100);
        vars.put("member",m);
        taskService.complete(task.getId(),vars);
    }
}
