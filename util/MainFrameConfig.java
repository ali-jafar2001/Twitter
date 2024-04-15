package util;

import java.io.FileNotFoundException;

public class MainFrameConfig extends SwingConfig {
	public MainFrameConfig(String name) {
		super(name);
		initialize();
	}

	
	private int  ExtendedState;
    private boolean Resizable;

    protected void initialize() {
    	Resizable = properties.readBoolean("Resizable");
        ExtendedState = properties.readInteger("ExtendedState");
    }

    public boolean isUndecorated() {
        return Resizable;
    }

    public int getExtendedState() {
    	return ExtendedState;
    }


    @Override
    protected void setProperties() {
        try {
			this.properties = ConfigLoader.getInstance().getFrameProperties(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	public boolean isResizaable() {
		return Resizable;
	}

}
