package top.lllyl2012;

import org.drools.KnowledgeBase;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import java.util.Collection;

public class DiscountTest {
    public static void main(String[] args) {
        // 创建一个KnowledgeBuilder
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        // 添加规则资源到 KnowledgeBuilder
//        kbuilder.add(ResourceFactory.newClassPathResource("first.drl",
        kbuilder.add(ResourceFactory.newClassPathResource("sale.drl",
                DiscountTest.class), ResourceType.DRL);
        // 获取知识包集合
        Collection<KnowledgePackage> pkgs = kbuilder
                .getKnowledgePackages();
        // 创建KnowledgeBase实例
        KnowledgeBase kbase = kbuilder.newKnowledgeBase();
        // 将知识包部署到KnowledgeBase中
        kbase.addKnowledgePackages(pkgs);
        // 使用KnowledgeBase创建StatefulKnowledgeSession
        StatefulKnowledgeSession ksession = kbase
                .newStatefulKnowledgeSession();


        // 定义一个事实对象
        Member m = new Member();
        m.setIdentity("gold");
        m.setAmount(130);
        // 向StatefulKnowledgeSession中加入事实
        ksession.insert(m);
        // 匹配规则
        ksession.fireAllRules();

        System.out.println("优惠金额："+m.getResult());
        // 关闭当前session的资源
        ksession.dispose();
    }
}
