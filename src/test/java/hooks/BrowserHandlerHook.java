package hooks;

import java.net.MalformedURLException;

import baseClass.LibraryClass;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class BrowserHandlerHook {
    
    LibraryClass lib;
    
    @Before
    public void startBrowser() throws MalformedURLException {
        lib = new LibraryClass();
        lib.launchBrowser();
    }
    
    @After
    public void closeBrowser() {
        lib.closeBrowser();
    }
}
