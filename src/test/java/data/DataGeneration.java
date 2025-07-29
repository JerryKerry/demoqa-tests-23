package data;

import com.github.javafaker.Faker;

import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class DataGeneration {

    public static Faker faker = new Faker();

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getAddress() {
        return faker.address().streetAddress();
    }

    public String getMobile() {
        return faker.phoneNumber().subscriberNumber(10);
    }

    public String getGender() {
        return faker.demographic().sex();
    }

    public String getSubject() {
        return faker.options().option(DataMap.SUBJECT);
    }

    public String getHobby() {
        return faker.options().option(DataMap.HOBBIES);
    }

    public String getState() {
        return faker.options().option(DataMap.mapStateWithCity.keySet().toArray()).toString();
    }

    public String getCity(String state) {
        return faker.options().option(DataMap.mapStateWithCity.get(state));
    }

    public Date getBirthday() {
        return faker.date().birthday();
    }

    public String getDayOfBirth(Date birthday) {
        return new SimpleDateFormat("dd", Locale.ENGLISH).format(birthday);
    }

    public String getMonthOfBirth(Date birthday) {
        return new SimpleDateFormat("MMMM", Locale.ENGLISH).format(birthday);
    }

    public String getYearOfBirth(Date birthday) {
        return new SimpleDateFormat("y", Locale.ENGLISH).format(birthday);
    }
}