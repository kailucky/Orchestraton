package com.nsfocus.orchestration.handler;

import java.util.List;

import com.nsfocus.orchestration.entity.OrchestrationEntity;
import com.nsfocus.orchestration.entity.OrchestrationNode;
import com.nsfocus.orchestration.scheduler.OrchestrationScheduledManager;


public class OrchestrationHandler {

	private static OrchestrationHandler orchestrationHandler=null;
	
	private static OrchestrationScheduledManager scheduledManager=
			OrchestrationScheduledManager.getScheduledManager();
	
	//初始化，获取实例
	public static OrchestrationHandler getOrchestrationHandler(){
		if(orchestrationHandler==null){
			orchestrationHandler=new OrchestrationHandler();
		}
		return orchestrationHandler;
	}
	
	//添加编排任务
	public void addOrchestrationNode(OrchestrationEntity entity){
		List<OrchestrationNode> lst = entity.getOrchestration_nodes();
		for (OrchestrationNode orchestrationNode : lst) {
			scheduledManager.addJob(orchestrationNode);
		}
	}
}
