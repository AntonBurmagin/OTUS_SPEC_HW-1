package main.otus;

import org.junit.platform.suite.api.*;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("otus")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "main/otus")
@IncludeTags("prepCourse")
public class RunnerTest {

}
