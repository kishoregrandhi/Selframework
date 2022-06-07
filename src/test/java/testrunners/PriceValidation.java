package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(features="src/main/resources/features/placeValidations.feature",plugin ="json:target/jsonReports/cucumber-report.json",glue= {"stepdefinitions"})
public class PriceValidation {

}
