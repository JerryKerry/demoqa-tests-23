package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
        SelenideElement
                selectMonth = $(".react-datepicker__month-select"),
                selectYear = $(".react-datepicker__year-select");

    public void setDate(String day, String month, String year) {
        selectMonth.selectOption(month);
        selectYear.selectOption(year);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();

    }
}