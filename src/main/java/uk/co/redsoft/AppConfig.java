package uk.co.redsoft;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

public class AppConfig extends Configuration {

    private String foo;

    @JsonProperty
    public String getFoo() {
        return foo;
    }

    @JsonProperty
    public void setFoo(String foo) {
        this.foo = foo;
    }

}
