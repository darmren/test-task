package task.dto;

import lombok.Getter;
import task.models.Sex;
import task.models.Status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static task.models.Sex.parseSex;
import static task.models.Status.parseStatus;

@Getter
public class StoreStudentRequest {
    private final String surname;
    private final String name;
    private final String patronymic;
    private final Integer groupId;
    private final Date birthday;
    private final Sex sex;
    private final Status status;
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


    public StoreStudentRequest(String surname,
                   String name,
                   String patronymic,
                   Integer groupId,
                   String birthday,
                   String sex,
                   String status) throws ParseException {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.groupId = groupId;
        this.birthday = formatter.parse(birthday);
        this.sex = parseSex(sex);
        this.status = parseStatus(status);
    }
}
