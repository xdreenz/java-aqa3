package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

class FormSubmitTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequestSuccessfully() {    //Проверка на успешную отправку формы, если всё введено верно
        $("[data-test-id = name] input").setValue("Петров Иван");
        $("[data-test-id = phone] input").setValue("+71112223344");
        $("[data-test-id = agreement]").click();
        $("button").click();
        $("[data-test-id = order-success]").shouldHave(text("успешно"));

    }

    @Test
    void shouldDisplayErrorIfInvalidInput() {   //Проверка на ошибку "Телефон указан неверно.", если номер телефона введён с ошибкой
        $("[data-test-id = name] input").setValue("Иванов Петр");
        $("[data-test-id = phone] input").setValue("123");
        $("button").click();
        $(".input_invalid").shouldHave(text("неверно"));
    }

    @Test
    void shouldDisplayErrorIfNoInput() {    //Проверка на ошибку "Поле обязательно для заполнения", если ничего не введено
        $("button").click();
        $(".input_invalid").shouldHave(text("обязательно"));
    }

}
