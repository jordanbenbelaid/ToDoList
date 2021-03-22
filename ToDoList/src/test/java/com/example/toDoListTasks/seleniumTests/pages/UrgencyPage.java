package com.example.toDoListTasks.seleniumTests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UrgencyPage {

	public static final String URL = "http:localhost:8080/urgency";
	
	@FindBy(id = "getButton")
	private WebElement getUrgenciesBtn;
	
	@FindBy(id = "urgencyname")
	private WebElement inputCreateUrgency;
	
	@FindBy(id = "newUrgency")
	private WebElement createUrgencyBtn;
	
	@FindBy(id = "updateList")
	private WebElement inputUpdateId;
	
	@FindBy(id = "listName")
	private WebElement inputUpdateName;
	
	@FindBy(id = "updateButton")
	private WebElement updateUrgencyBtn;
	
	@FindBy(id = "deleteList")
	private WebElement inputDeleteUrgency;
	
	@FindBy(id = "deleteBtn")
	private WebElement deleteUrgencyBtn;
	
	public void getUrgencies() {
		getUrgenciesBtn.click();
	}
	
	public void createUrgencyInput() {
		inputCreateUrgency.click();
	}
	
	public void createUrgencyButton() {
		createUrgencyBtn.click();
	}
	
	public void updateIdInput() {
		inputUpdateId.click();
	}
	
	public void updateNameInput() {
		inputUpdateName.click();
	}
	
	public void updateUrgencyButton() {
		updateUrgencyBtn.click();
	}
	
	public void deleteUrgencyInput() {
		inputDeleteUrgency.click();
	}
	
	public void deleteUrgencyButton() {
		deleteUrgencyBtn.click();
	}
}
