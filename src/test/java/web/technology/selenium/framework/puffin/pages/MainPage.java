package web.technology.selenium.framework.puffin.pages;

import web.technology.selenium.framework.core.view.Page;

/**
 * Created by kalisb on 28.05.17.
 */
//@PageCheck(url = "http://loremipsum2.fmi.uni-sofia.bg/WEBTECH/www_8ed_referats/")
public class MainPage extends Page {

    public MainPage open() {
        browser().get(url());
        return this;
    }
}
