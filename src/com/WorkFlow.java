package com;

public class WorkFlow {

	private String workFlowId;
	private String ProductId;
	private String representativeId;
	private String workFlowStatus;
	private int reductionLimit;
	private double price;
	private String model;
	private String modelid;

	
	public WorkFlow(String workFlowId, String productId,
			String representativeId, String workFlowStatus, int reductionLimit,
			int price, String model, String modelid) {
		super();
		this.workFlowId = workFlowId;
		ProductId = productId;
		this.representativeId = representativeId;
		this.workFlowStatus = workFlowStatus;
		this.reductionLimit = reductionLimit;
		this.price = price;
		this.model = model;
		this.modelid = modelid;
	
	}
	
	

	



	public WorkFlow() {
		// TODO Auto-generated constructor stub
	}


	public double getPrice() {
		return price;
	}
	public void setPrice(double d) {
		this.price = d;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public String getWorkFlowId() {
		return workFlowId;
	}
	public void setWorkFlowId(String workFlowId) {
		this.workFlowId = workFlowId;
	}
	public String getProductId() {
		return ProductId;
	}
	public void setProductId(String productId) {
		ProductId = productId;
	}
	public String getRepresentativeId() {
		return representativeId;
	}
	public void setRepresentativeId(String representativeId) {
		this.representativeId = representativeId;
	}
	public String getWorkFlowStatus() {
		return workFlowStatus;
	}
	public void setWorkFlowStatus(String workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}
	public int getReductionLimit() {
		return reductionLimit;
	}
	public void setReductionLimit(int reductionLimit) {
		this.reductionLimit = reductionLimit;
	}
	/*public WorkFlow(String workFlowId, String productId, String representativeId,
			String workFlowStatus, int reductionLimit) {
		super();
		this.workFlowId = workFlowId;
		ProductId = productId;
		this.representativeId = representativeId;
		this.workFlowStatus = workFlowStatus;
		this.reductionLimit = reductionLimit;
	}
	*/
	
}
