package ru.netology.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class FormSubmitTest {
    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSubmitRequestSuccessfully() {    //Проверка на успешную отправку формы, если всё указано верно
        $("[data-test-id = name] input").setValue("Петров Иван");
        $("[data-test-id = phone] input").setValue("+71112223344");
        $("[data-test-id = agreement]").click();
        $("button").click();
        $("[data-test-id = order-success]").shouldHave(text("успешно"));

    }

    @Test
    void shouldDisplayErrorIfInvalidName() {   //Проверка на ошибку, если имя указано неверно
        $("[data-test-id = name] input").setValue("Qwerty");
        $("[data-test-id = phone] input").setValue("+71112223344");
        $("[data-test-id = agreement]").click();
        $("button").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(text("неверно"));
    }

    @Test
    void shouldDisplayErrorIfNoName() {   //Проверка на ошибку, если имя не указано вовсе
        $("[data-test-id = phone] input").setValue("+71112223344");
        $("[data-test-id = agreement]").click();
        $("button").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(text("обязательно"));
    }

    @Test
    void shouldDisplayErrorIfInvalidPhone() {   //Проверка на ошибку, если телефон указан неверно
        $("[data-test-id = name] input").setValue("Петров Иван");
        $("[data-test-id = phone] input").setValue("123");
        $("[data-test-id = agreement]").click();
        $("button").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(text("неверно"));
    }

    @Test
    void shouldDisplayErrorIfNoPhone() {   //Проверка на ошибку, если телефон не указан вовсе
        $("[data-test-id = name] input").setValue("Петров Иван");
        $("[data-test-id = agreement]").click();
        $("button").click();
        $("[data-test-id = phone].input_invalid .input__sub").shouldHave(text("обязательно"));
    }

    @Test
    void shouldDisplayErrorIfNoAgreementChecked() {   //Проверка на ошибку, если не отмечен чекбокс
        $("[data-test-id = name] input").setValue("Петров Иван");
        $("[data-test-id = phone] input").setValue("+71112223344");
        $("button").click();
        $("[data-test-id =agreement].input_invalid").shouldBe(visible);
    }

    @Test
    void shouldDisplayErrorIfNoNameAndPhone() {   //Проверка на ошибку, если не указаны и имя и телефон
        $("[data-test-id = agreement]").click();
        $("button").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(text("обязательно"));
    }

    @Test
    void shouldDisplayErrorIfNoInput() {    //Проверка на ошибку, если ничего не указано
        $("button").click();
        $("[data-test-id = name].input_invalid .input__sub").shouldHave(text("обязательно"));
    }

}
