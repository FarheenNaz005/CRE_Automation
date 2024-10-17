package com.newdemo.framework.pages.stats;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StatsNewsPage {
    @FindBy(xpath = "//div[@class='scrollable-content']")
    public WebElement allartical;
    @FindBy(xpath = "//input[@name='by_publish_date']")
    public WebElement datecheckbox;
    @FindBy(xpath = "//input[@name='dealFilter']")
    public WebElement dealcheckbox;
    @FindBy(xpath = "//input[@name='publish_date_begin']")
    public WebElement startdate;
    @FindBy(xpath = "//input[@name='publish_date_end']")
    public WebElement enddate;
    @FindBy(xpath = "//span[contains(text(),'Display')]")
    public WebElement displayButton;
    @FindBy(xpath = "//input[@name='deals_search']")
    public WebElement dealnameinput;
    @FindBy(xpath = "//td[@id='td50_0']")
    public List<WebElement> date;
    @FindBy(xpath = "//div[@class='loading-gif']")
    public WebElement loadingNews;
    @FindBy(xpath = "//a[@news-article-index='0']")
    public WebElement firstNewsArticle;
    @FindBy(xpath = "//div[@id='news-story-modal']")
    public WebElement newsPopup;


}
