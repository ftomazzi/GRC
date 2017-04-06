package com.idsscheer.webapps.arcm.bl.component.issuemanagement.commands;

import com.idsscheer.webapps.arcm.bl.exception.BLException;
import com.idsscheer.webapps.arcm.bl.framework.command.CommandContext;
import com.idsscheer.webapps.arcm.bl.framework.command.CommandResult;
import com.idsscheer.webapps.arcm.bl.framework.command.ICommand;

public class CustomUpdateIssueStatus implements ICommand {

	public CustomUpdateIssueStatus() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public CommandResult execute(CommandContext cc) throws BLException {
		// TODO Auto-generated method stub
		return null;
/*		int ctOpen = 0;
		int ctGoing = 0;
		int ctFup = 0;
		int ctAttended = 0;
		int ctRiskAssumed = 0;
		int ctSettled = 0;
		
		IAppObj issueAppObj = cc.getChainContext().getTriggeringAppObj();
		IUserContext userCtx = cc.getChainContext().getUserContext();
		
		IEnumAttribute issueTypeAttr = issueAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_ACTIONTYPE);
		IEnumerationItem issueTypeItem = ARCMCollections.extractSingleEntry(issueTypeAttr.getRawValue(), true);
		if(!(issueTypeItem.getId().equals("actionplan"))){
			return CommandResult.OK;
		}
		
		List<IAppObj> iroList = issueAppObj.getAttribute(IIssueAttributeType.LIST_ISSUERELEVANTOBJECTS).getElements(userCtx);
		for(IAppObj iroAppObj : iroList){
			if(!(iroAppObj.getObjectType().equals(ObjectType.ISSUE)))
				continue;
			
			IEnumAttribute iroTypeAttr = iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_ACTIONTYPE);
			IEnumerationItem iroTypeItem = ARCMCollections.extractSingleEntry(iroTypeAttr.getRawValue(), true);
			if(!(iroTypeItem.getId().equals("issue")))
				continue;
			
			IEnumAttribute iroOwnerStatusAttr = iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_OWNER_STATUS);
			IEnumerationItem iroOwnerStatus = ARCMCollections.extractSingleEntry(iroOwnerStatusAttr.getRawValue(), true);
			
			IEnumAttribute iroCreatorStatusAttr = iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_CREATOR_STATUS);
			IEnumerationItem iroCreatorStatus = ARCMCollections.extractSingleEntry(iroCreatorStatusAttr.getRawValue(), true);
			
			IEnumAttribute iroReviewerStatusAttr = iroAppObj.getAttribute(IIssueAttributeTypeCustom.ATTR_IS_REVIEWER_STATUS);
			IEnumerationItem iroReviewerStatus = ARCMCollections.extractSingleEntry(iroReviewerStatusAttr.getRawValue(), true);
			
			if( ((iroCreatorStatus.getId().equals("in_elaboration")) || (iroCreatorStatus.getId().equals("pending")))
				 && (iroOwnerStatus.getId().equals("pending"))){
				ctOpen += 1;
				issueAppObj.getAttribute(IIssueAttributeType.ATTR_CREATOR_STATUS).setRawValue(
						Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.OPEN)
				);
			}
			
			if( ((iroCreatorStatus.getId().equals("on_going")) || (iroCreatorStatus.getId().equals("fup")) || (iroCreatorStatus.getId().equals("risk_assumed"))) &&
				((iroOwnerStatus.getId().equals("on_going")) || (iroOwnerStatus.getId().equals("fup")) || (iroOwnerStatus.getId().equals("risk_assumed"))) &&
				((iroReviewerStatus.getId().equals("settled")) || (iroReviewerStatus.getId().equals("fup")) || (iroReviewerStatus.getId().equals("risk_assumed")) || (iroReviewerStatus.getId().equals("attended"))) ){
				ctGoing += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("fup")) || (iroCreatorStatus.getId().equals("risk_assumed"))) &&
				((iroOwnerStatus.getId().equals("fup")) || (iroOwnerStatus.getId().equals("risk_assumed"))) &&
				((iroReviewerStatus.getId().equals("settled")) || (iroReviewerStatus.getId().equals("fup")) || (iroReviewerStatus.getId().equals("risk_assumed")) || (iroReviewerStatus.getId().equals("attended"))) ){
				ctFup += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("risk_assumed"))) &&
				((iroOwnerStatus.getId().equals("risk_assumed"))) &&
				((iroReviewerStatus.getId().equals("settled")) || (iroReviewerStatus.getId().equals("risk_assumed")) || (iroReviewerStatus.getId().equals("attended"))) ){
				ctAttended += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("risk_assumed"))) &&
				((iroOwnerStatus.getId().equals("risk_assumed"))) &&
				((iroReviewerStatus.getId().equals("risk_assumed"))) ){
				ctRiskAssumed += 1;
			}
			
			if( ((iroCreatorStatus.getId().equals("settled"))) &&
				((iroOwnerStatus.getId().equals("settled"))) &&
				((iroReviewerStatus.getId().equals("settled"))) ){
				ctSettled += 1;
			}
			
		}
		
		if(ctOpen > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.OPEN)
			);
			return CommandResult.OK;
		}
		
		if(ctGoing > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.ON_GOING)
			);
			return CommandResult.OK;
		}
		
		if(ctFup > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.FUP)
			);
			return CommandResult.OK;
		}
		
		if(ctAttended > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.ATTENDED)
			);
			return CommandResult.OK;
		}
		
		if(ctRiskAssumed > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.RISK_ASSUMED)
			);
			return CommandResult.OK;
		}
		
		if(ctSettled > 0){
			issueAppObj.getAttribute(IIssueAttributeType.ATTR_OWNER_STATUS).setRawValue(
					Collections.singletonList(EnumerationsCustom.ISSUE_OWNER_STATUS.SETTLED)
			);
			return CommandResult.OK;
		}
				
		return CommandResult.OK;
		//return null;
		 * 
		 */
	}

}
