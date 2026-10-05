package ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class DocumentsPage {

    private final SelenideElement downloadBtn = $x("//td[@data-name='file']//a[@href]");
    private final SelenideElement uploadFileInput = $("input[type='file']");
    private final SelenideElement createDocumentBtn =$("[data-name='quickCreate']");
    private final SelenideElement saveBtn = $("[data-name='save']");
    private final SelenideElement nameInp = $("input[data-name='name']");

    @Step("Скачиваем документ")
    public File downloadDoc() {
        File file = downloadBtn.should(Condition.enabled).download();
        Assertions.assertNotNull(file, "Файл равен null, т.е не скачался");
        Assertions.assertTrue(file.length() > 0, "Файл не имеет размера, т.е не заполнен");
        return file;
    }

    @Step("Загружаем документ")
    public DocumentsPage uploadDoc(File file) {
       uploadFileInput.should(Condition.enabled).uploadFile(file);
       nameInp.should(Condition.value(file.getName()));
       return this;
    }

    @Step("Нажать на кнопку Создать документ")
    public DocumentsPage clickCreateDocumentBtn() {
        createDocumentBtn.click();
        return this;
    }

    @Step("Нажать на кнопку Сохранить при загрузке файла")
    public DocumentsPage clickSaveBtn() {
        saveBtn.click();
        return this;
    }
}
