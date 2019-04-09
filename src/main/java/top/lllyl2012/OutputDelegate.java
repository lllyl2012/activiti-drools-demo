package top.lllyl2012;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.List;

public class OutputDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("结果输出类：");
        List<Member> members = (List<Member>)delegateExecution.getVariable("members");
        for (Member m:members) {
            System.out.println("用户："+m.getIdentity() + ",优惠金额:"+m.getResult());
        }
    }
}
