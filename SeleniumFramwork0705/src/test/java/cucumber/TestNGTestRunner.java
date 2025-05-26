package cucumber;

import java.io.IOException;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import AutomationPractise.SeleniumFramwork0705.SubmitOrderTest;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//@CucumberOptions(features="D:\\eclipse\\eclipse-workspace\\SeleniumFramwork0705\\src\\test\\java\\cucumber",glue="AutomationPractise.StepDefination",monochrome=true, plugin= {"html:target/cucumber.html"})
//public class TestNGTestRunner extends AbstractTestNGCucumberTests{
//	
//}

@CucumberOptions(features="D:\\eclipse\\eclipse-workspace\\SeleniumFramwork0705\\src\\test\\java\\cucumber",glue="AutomationPractise.StepDefination",monochrome=true, plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Factory
    public Object[] createTests() throws IOException {
        Set<String> tags = SubmitOrderTest.getTagsToRun();

        if (tags.isEmpty()) {
            throw new RuntimeException("No test cases to run (Run != Y)");
        }

        String tagExpression = String.join(" or ", tags);

        System.setProperty("cucumber.filter.tags", tagExpression);
        return new Object[]{new TestNGTestRunner()};
    }
}