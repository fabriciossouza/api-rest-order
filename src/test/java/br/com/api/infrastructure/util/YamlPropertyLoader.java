package br.com.api.infrastructure.util;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;

public class YamlPropertyLoader {
    private YamlPropertiesFactoryBean yaml;
    private String scenario;

    public YamlPropertyLoader(String file, String scenario) {
        yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource(file));
        this.scenario = scenario;
    }

    public String getInputBody() {
        return yaml.getObject().getProperty(scenario + ".input");
    }

    public String getOutputBody() {
        return yaml.getObject().getProperty(scenario + ".output");
    }
}
