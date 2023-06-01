package CustomerMigration;

import com.microsoft.playwright.ElementHandle;
import org.testng.annotations.Test;

import java.io.IOException;

public class migrationEntry {

    static ElementHandle sub_menu;
    static ElementHandle sub_menu1;

    static Object data[][];
    //ChromeOptions opt = new ChromeOptions();

/*	@BeforeMethod
	public static void login(){

	}*/

    @Test(dataProvider = "registrationGroup", dataProviderClass = dataProvider.class)

    public static void migrationFunction(String dataValues[]) throws InterruptedException, IOException {

        String newcustomerType = dataValues[dataValues.length - 1];

        //  System.setProperty("webdriver.chrome.driver","D:/AutomationProject/chromedriver_win32/chromedriver.exe");
        //    driver = new ChromeDriver();


        //opt.addArguments("--headless");

        // UseCase1: opening the website
        PlaywrightGenerics.page.navigate("");
        //  driver.manage().window().maximize();

        // Login Usecase2: Entering Credentials

        PlaywrightGenerics.page.locator("//*[@id='user']").fill("");
        PlaywrightGenerics.page.locator("//*[@id='password']").fill("");

        // clicking on LoginButton after entering credentials
        PlaywrightGenerics.page
                .locator("//*[@id='consumerForm']/li[3]/table/tbody/tr/td[1]/input")
                .click();
        //Test Common


        System.out.println("*** Into the CustomerRegistration ***");


        Utility.getActionsObject_submenu_homepage(sub_menu, sub_menu1);

        migration mig = new migration();

        ////////////////////////////////////////
        ///////// ON BOARDING L2 TYPES /////////
        ////////////////////////////////////////

        if (newcustomerType.equalsIgnoreCase("L2 Existing Customer")
                || newcustomerType.equalsIgnoreCase("L2-Individual")
                || newcustomerType.equalsIgnoreCase("L2-Individual-10000")
                || newcustomerType.equalsIgnoreCase("L2-Individual-250000")
                || newcustomerType.equalsIgnoreCase("L2-Individual-500000")
                || newcustomerType.equalsIgnoreCase("L2-Individual-99999")
                || newcustomerType.equalsIgnoreCase("L2 Individual Staff")
                || newcustomerType.equalsIgnoreCase("L2 Individual Staff-100000")
                || newcustomerType.equalsIgnoreCase("L2 Individual Staff-2500000")
                || newcustomerType.equalsIgnoreCase("L2 Individual Staff-700000")
        ) {

            mig.migratingProfiletol2Types(dataValues[0],
                    dataValues[1], dataValues[2],
                    dataValues[3], dataValues[4], dataValues[5], dataValues[6],
                    dataValues[7], dataValues[8], dataValues[9], dataValues[10], dataValues[11], dataValues[12], dataValues[13]
                    , dataValues[14], dataValues[15], dataValues[16]);
            //driver.findElement(By.name(""))
        } else if (newcustomerType.equalsIgnoreCase("Guest D/C Block User")) {

            mig.toGuest(dataValues[0], dataValues[1]);
        } else if (newcustomerType.equalsIgnoreCase("Corporate Account Master Wallet")
                || newcustomerType.equalsIgnoreCase("Corporate Sim Sim Account User")
                || newcustomerType.equalsIgnoreCase("Agent FINJA")
                || newcustomerType.equalsIgnoreCase("Agent Finca")) {

            mig.corporatetypes(dataValues[0],
                    dataValues[1], dataValues[2],
                    dataValues[3], dataValues[4], dataValues[5], dataValues[6],
                    dataValues[7], dataValues[8], dataValues[9], dataValues[10], dataValues[11], dataValues[12], dataValues[13]
                    , dataValues[14], dataValues[15], dataValues[16], dataValues[17], dataValues[18],
                    dataValues[19], dataValues[20], dataValues[21], dataValues[22], dataValues[23],
                    dataValues[24], dataValues[25], dataValues[26], dataValues[27], dataValues[28], dataValues[29], dataValues[30]);

        }

    }


}
