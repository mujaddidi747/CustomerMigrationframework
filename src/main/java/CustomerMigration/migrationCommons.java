package CustomerMigration;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;

public class migrationCommons {
    static SelectOption setDropdown = new SelectOption();
    public static void commons (String newcustomerType) throws InterruptedException{

        try
        {
            Locator customer =   playwrightGenerics.page.locator("#idb");
            Locator dropdown_customerRegistration =  customer;
            dropdown_customerRegistration.selectOption(setDropdown.setLabel(newcustomerType));

            ////*[@id="idc"]
            dropdown_customerRegistration.click();


        }

        catch(Exception e){

            System.out.println("EXCEPTION: "+ e.getMessage());


        }
    }







}
