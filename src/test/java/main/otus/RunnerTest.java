package main.otus;

import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;

import org.junit.platform.suite.api.*;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("otus")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "main/otus")
@IncludeTags({"browserChoice"})
public class RunnerTest {

}
