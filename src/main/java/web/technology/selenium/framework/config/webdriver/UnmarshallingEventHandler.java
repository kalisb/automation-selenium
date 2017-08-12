package web.technology.selenium.framework.config.webdriver;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

public class UnmarshallingEventHandler implements ValidationEventHandler {

	@Override
	public boolean handleEvent(ValidationEvent event) {
		return false;
	}

}
