package src.test.org.example;
import org.example.RegistrationForm;
import org.junit.jupiter.api.Assertions;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;


public class RegistrationFormTest {
    @Test
    public void CheckTestAllRight(){ // все данные корректны
        RegistrationForm registrationForm = new RegistrationForm();
        boolean result = registrationForm.Check("User", "user12@gmail.com", "124376asdf1");
        Assertions.assertTrue(result);

    }


    @Test
    public void CheckTestNameMistake(){ // в поле имя пустая строка
        RegistrationForm registrationForm = new RegistrationForm();
        boolean result = registrationForm.Check("", "Anna@gmail.ru", "fht!jyr67/9d");
        Assertions.assertTrue(!result);
    }

    @Test
    public void CheckTestEmailMistake(){ // пропущен символ @ в email
        RegistrationForm registrationForm = new RegistrationForm();
        boolean result = registrationForm.Check("Anna", "AnnAlVlyandex.com", "AnPassword12");
        Assertions.assertTrue(!result);
    }


    @Test
    public void CheckTestPasswordMistake(){ // длина пароля меньше 10
        RegistrationForm registrationForm = new RegistrationForm();
        boolean result = registrationForm.Check("Ivan", "IvanUser@gmail.ru", "12");
        Assertions.assertTrue(!result);
    }
}
