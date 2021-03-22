package ru.alfabank.test.task.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestTaskControllerTest {

    @Autowired
    private TestTaskController controller;

    @Test
    public void controllerLoadsIntoContext() throws Exception {
        assertThat(controller).isNotNull();
    }
}