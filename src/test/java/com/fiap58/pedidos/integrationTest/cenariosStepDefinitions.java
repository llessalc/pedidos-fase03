package com.fiap58.pedidos.integrationTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class cenariosStepDefinitions {
    @Given("I have a step")
    public void i_have_a_step() {
        System.out.println("Given executed");
    }

    @When("I take a step")
    public void i_take_a_step() {
        System.out.println("When executed");
    }

    @Then("I complete the step")
    public void i_complete_the_step() {
        System.out.println("Then executed");
    }
}