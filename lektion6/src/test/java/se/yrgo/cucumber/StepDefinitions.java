package se.yrgo.cucumber;

import static org.junit.jupiter.api.Assertions.*;
import java.time.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import se.yrgo.*;

public class StepDefinitions {

    private DayOfWeek dow;
    private String retVal;

    @Given("today is {string}")
    public void today_is(String dow) {
        this.dow = DayOfWeek.valueOf(dow.toUpperCase());
    }

    @When("I ask whether it's Friday yet")
    public void i_ask_whether_it_s_friday_yet() {
        this.retVal = DayUtils.isItFriday(dow);
    }

    @Then("I should be told {string}")
    public void i_should_be_told(String isit) {
        assertEquals(isit, this.retVal);
    }
}
