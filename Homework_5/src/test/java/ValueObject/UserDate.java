package ValueObject;

import org.openqa.selenium.By;

import java.util.Objects;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public  class UserDate {
    private String fullName;
    public void setFullName(String fullName){
        this.fullName =  fullName;
    }
    public String getFullName(){
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDate userDate = (UserDate) o;
        return Objects.equals(fullName, userDate.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName);
    }

}
