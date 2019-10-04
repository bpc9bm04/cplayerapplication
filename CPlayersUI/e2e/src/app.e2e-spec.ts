import { AppPage } from './app.po';
import { browser, by, element, protractor } from 'protractor';

describe('frontend App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    browser.sleep(5000);
    browser.ignoreSynchronization = true;
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('CPlayerclientapplication');
  });

  it('should be redirected to /login route', () => {
   
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be redirected to /register route', () => {
    browser.element(by.css('.register-user')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {
    browser.element(by.id('firstName')).sendKeys('super');
    browser.element(by.id('lastName')).sendKeys('super');
    browser.element(by.id('username')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    browser.element(by.css('.register-button')).click();
    console.log(browser.getCurrentUrl());
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should able to login and navigate to home page', () => {
    browser.element(by.id('username')).sendKeys('super');
    browser.element(by.id('password')).sendKeys('super');
    browser.element(by.css('.login-user')).click();
    expect(browser.getCurrentUrl()).toContain('/players/search');
  });

  it('should able to search players', () => {
      
    browser.element(by.css('.search-button')).click();
    expect(browser.getCurrentUrl()).toContain('/players/search');
    browser.element(by.css('.search-button-input')).sendKeys('sachin');
    browser.element(by.css('.search-button-input')).sendKeys(protractor.Key.ENTER);

    const searchItems = element.all(by.css('.player-thumbnail'));
    expect(searchItems.count()).toBe(25);

    for(let i=0; i<1; i += 1){
      expect(searchItems.get(i).getText()).toContain('Sachin');
    }

  });

  it('should able to add players to watchlist', () => {
    browser.driver.manage().window().maximize();
    browser.sleep(1000);
    

    const searchItems = element.all(by.css('.player-thumbnail'));
    expect(searchItems.count()).toBe(25);
    searchItems.get(0).click();
    browser.element(by.css('.addButton')).click();

  });

});
