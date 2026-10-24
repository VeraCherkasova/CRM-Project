package applications.ui.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import ru.testit.annotations.Title;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;

public class DocumentsPage {

    private final SelenideElement downloadBtn = $x("//td[@data-name='file']//a[@href]");
    private final SelenideElement uploadFileInput = $("input[type='file']");
    private final SelenideElement createDocumentBtn =$("[data-name='quickCreate']");
    private final SelenideElement saveBtn = $("[data-name='save']");
    private final SelenideElement nameInp = $("input[data-name='name']");

    @Step("Скачиваем файл с расширением: {extension}")
    @ru.testit.annotations.Step
    @Title("Скачиваем файл с расширением: {extension}")
    public File downloadDocByType(String extension) {
        // Приводим расширение к одному виду (например, если передали "pdf", делаем ".pdf")
        String ext = extension.startsWith(".") ? extension : "." + extension;

        // Ищем строку таблицы, в которой ссылка ведет на файл с нужным расширением
        SelenideElement row = $x(String.format(
                "//tr[.//td[@data-name='file']//a[" +
                        "contains(translate(@title, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s') or " +
                        "contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '%s')" +
                        "]]",
                ext.toLowerCase(), ext.toLowerCase()
        ));

        // Кликаем по ссылке внутри найденной строки
        File file = row.$x(".//td[@data-name='file']//a[@href]")
                .should(Condition.enabled)
                .download();

        Assertions.assertNotNull(file, "Файл с расширением " + ext + " равен null, т.е не скачался");
        Assertions.assertTrue(file.length() > 0, "Файл не имеет размера, т.е не заполнен");

        // Дополнительная проверка, что скачался именно тот тип (защита от кэша Selenide)
        Assertions.assertTrue(file.getName().toLowerCase().endsWith(ext.toLowerCase()),
                "Скачался неверный тип файла: " + file.getName());

        return file;
    }


    @Step("Загружаем документ")
    @ru.testit.annotations.Step
    @Title("Загружаем документ")
    public DocumentsPage uploadDoc(File file) {
       uploadFileInput.should(Condition.enabled).uploadFile(file);
       nameInp.should(Condition.value(file.getName()));
       return this;
    }

    @Step("Нажать на кнопку Создать документ")
    @ru.testit.annotations.Step
    @Title("Нажать на кнопку Создать документ")
    public DocumentsPage clickCreateDocumentBtn() {
        createDocumentBtn.click();
        return this;
    }

    @Step("Нажать на кнопку Сохранить при загрузке файла")
    @ru.testit.annotations.Step
    @Title("Нажать на кнопку Сохранить при загрузке файла")
    public DocumentsPage clickSaveBtn() {
        saveBtn.click();
        return this;
    }
}
