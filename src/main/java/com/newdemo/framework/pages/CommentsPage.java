package com.newdemo.framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.newdemo.framework.base.BaseSetupClass;

public class CommentsPage {

    private WebDriver driver = BaseSetupClass.getDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 20);

    /**
     * Elements
     **/
    @FindBy(css = "textarea[name='newComment']")
    public WebElement newCommentInput;

    @FindBy(css = "button[ng-click*='saveComment']")
    public WebElement saveCommentButton;

    //edit comment
    @FindBy(css = "textarea[id*='edit-textarea']")
    public WebElement editCommentInput;

    @FindBy(css = "button[ng-click*='updateComment']")
    public WebElement editCommentSave;


    /**
     * dynamic elements
     **/
    public WebElement commentByText(String commentText) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//span[contains(@class,'comment-text') and text()[contains(.,'" + commentText + "')]]")));
    }

    public WebElement commentEditButton(String commentText) {
        return wait.until(ExpectedConditions.elementToBeClickable(commentByText(commentText).findElement(By.xpath("./../..//a[@ng-click='edit(comment)']"))));
    }

    public WebElement commentDeleteButton(String commentText) {
        return wait.until(ExpectedConditions.elementToBeClickable(commentByText(commentText).findElement(By.xpath("./../..//a[@ng-click='delete(comment)']"))));
    }


}
