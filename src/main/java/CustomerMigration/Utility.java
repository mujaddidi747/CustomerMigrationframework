package CustomerMigration;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static CustomerMigration.playwrightGenerics.page;

public class Utility {

    public static SelectOption setdropdown = new SelectOption();
    static Playwright playwright = Playwright.create();

    static Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));


    static Workbook book;
    static Sheet sheet;
    public static String TestData_Sheet_Path = "";


/*    Login login = new Login();*/

    ///////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////

    public static synchronized void getActionsObject_submenu_homepage(Locator submenu, Locator submenu1) throws InterruptedException {

        //    submenu = page.locator("//*[@id=\"pageWrap\"]/div[3]/ul[1]/span/span[6]/li/span");
        submenu = playwrightGenerics.page.locator("//*[@id=\"pageWrap\"]/div[3]/ul[1]/span/span[6]/li/span");
        submenu.hover();


        //  submenu.selectOption(setDropdown.setLabel("CUSTOMER REGISTRATION"));

        submenu1 = playwrightGenerics.page.locator("//*[@id=\"pageWrap\"]/div[3]/ul[1]/span/span[6]/li/ul/span[7]/li/a/span").last();

        synchronized (playwright) {
            if(submenu1.isEnabled())
                submenu1.click();
        }

    }






    public static String[] getSheetNames() {

        System.out.println("*** Into the Sheets Name ***");

        FileInputStream file = null;

        try {
            file = new FileInputStream(TestData_Sheet_Path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            book = WorkbookFactory.create(file);
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Get No Of Sheets : " + book.getNumberOfSheets());

        String[] strName = new String[book.getNumberOfSheets()];

        for (int i = 0; i < book.getNumberOfSheets(); i++) {

            strName[i] = book.getSheetAt(i).getSheetName();
            System.out.println(strName[i]);
        }

        return strName;
    }

    public static Object[][] ReadSheet(String sheetName) throws IOException {

        System.out.println("*** Into the ReadFile ***");

        Object[][] data = null;

        sheet = book.getSheet(sheetName);

        data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        System.out.println("Sheet Total Records:" + sheet.getLastRowNum());

        try {

            for (int j = 0; j < sheet.getLastRowNum(); j++) {
                for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {

                    if ((data[j][k]) == null) {
                        //we have to add check here that if the array value contains the null values delete the array
                        data[j][k] = sheet.getRow(j + 1).getCell(k).toString();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void selectDate(String month_year, String select_day) throws InterruptedException {

        Random numberCalendar = new Random();
        int low = 1;
        int high = 10;
        int randomYear = numberCalendar.nextInt(high - low) + low;
        //System.out.println("randomYear: "+randomYear);
        List<ElementHandle> elementsYear = (List<ElementHandle>) page.locator("/html/body/div[2]/div/div/select[2]/option[" + randomYear + "]");
        //System.out.print("a");
        //System.out.println(elementsYear.get(0).getText());
        elementsYear.get(0).click();
        numberCalendar = new Random();
        low = 1;
        high = 12;
        int randomMonth = numberCalendar.nextInt(high - low) + low;
        //System.out.println("randomMonth: "+randomMonth);
        List<ElementHandle> elements = (List<ElementHandle>) page.locator("/html/body/div[2]/div/div/select[1]/option[" + randomMonth + "]");

        for (int i = 0; i < elements.size(); i++) {
            //System.out.println(elements.get(i).getText());
            elements.get(0).click();
        }
        // Selecting the month
        // Selecting the date
        numberCalendar = new Random();
        low = 2;
        high = 4;
        int randomDateRow = numberCalendar.nextInt(high - low) + low;
        //System.out.println("randomDateRow: "+randomDateRow);
        numberCalendar = new Random();
        low = 1;
        high = 7;
        int randomDateCol = numberCalendar.nextInt(high - low) + low;
        //System.out.println("randomDateCol: "+randomDateCol);

        List<ElementHandle> days = (List<ElementHandle>) page.locator("/html/body/div[2]/table/tbody/tr[" + randomDateRow + "]/td[" + randomDateCol + "]/a");

        for (ElementHandle d : days) {
            //		System.out.println(d.getText());
            d.click();
            return;
        }
    }


    public static void riskTypes(){

        Locator AccountOrganizationType =page.locator("#id8b");
        AccountOrganizationType.selectOption(setdropdown.setValue("Low Risk"));

        Locator PoliticallyExposedPerson =  page.locator("#id8d");
        PoliticallyExposedPerson.selectOption("Low Risk");

        Locator CustomerOwnership =  page.locator("#id8f");
        CustomerOwnership.selectOption("Low Risk");

        Locator CustomerRefuse = page.locator("#id91");
        CustomerRefuse.selectOption("Low Risk");

        Locator CustomerDealing = page.locator("#id93");
        CustomerDealing.selectOption("Low Risk");

        Locator IsResident = page.locator("#id95");
        IsResident.selectOption("Low Risk");

        Locator IsActualOwner = page.locator("#id97");
        IsActualOwner.selectOption("Low Risk");

        Locator IsCustomerDirector = page.locator("#id8c");
        IsCustomerDirector.selectOption("Low Risk");

        Locator IsCustomerHouseWife = page.locator("#id8e");
        IsCustomerHouseWife.selectOption("Low Risk");

        Locator IsMinorAccount = page.locator("#id90");
        IsMinorAccount.click();
        IsMinorAccount.selectOption("High Risk");

        Locator IsHighRiskBusiness = page.locator("#id92");
        IsHighRiskBusiness.selectOption("Low Risk");

        Locator IsClientProfile = page.locator("#id94");
        IsClientProfile.selectOption("Low Risk");

        Locator IsComplexStructure = page.locator("#id96");
        IsComplexStructure.selectOption("High Risk");

        Locator IsMailInstruction = page.locator("#id98");
        IsMailInstruction.selectOption("High Risk");

        //RISK RATING
        Locator RiskRating = page.locator("#riskRating");
        RiskRating.selectOption("Low Risk");

    }





}
