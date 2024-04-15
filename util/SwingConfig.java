package util;

public abstract class SwingConfig {
    protected Configs properties;
    protected String name;

    public SwingConfig(String name) {
        this.name = name;
        setProperties();

    }

    

    protected abstract void setProperties();
}
