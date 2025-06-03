package Pages;

public abstract class LoadablePage<T extends LoadablePage<T>> {
    public abstract T checkPage();

}