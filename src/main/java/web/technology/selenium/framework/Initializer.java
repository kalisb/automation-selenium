package web.technology.selenium.framework;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import web.technology.selenium.framework.config.SpringRootConfig;
import web.technology.selenium.framework.config.SpringWebConfig;

/**
 * Created by kalisb on 28.06.17.
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringRootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
