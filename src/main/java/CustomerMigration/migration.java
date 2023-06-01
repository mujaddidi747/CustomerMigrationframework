package CustomerMigration;

import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.SelectOption;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class migration {

    Date d = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
    String date = formatter.format(d);
    String splitter[] = date.split("-");
    String month_year = splitter[0];
    String day = splitter[1];
    ElementHandle sub_menu;
    ElementHandle sub_menu1;
    public static SelectOption setdropdown = new SelectOption();


    ///////////TO L2 TYPES ///////////
    //////////////////////////////////


    public boolean migratingProfiletol2Types(String accNumber, String Name, String CNIC, String contactNumber, String residency
            , String address, String ActualOwner, String BenificiaryCNIC, String ExpextedMonthlyNoTransactionCR, String ExpextedMonthlyAmtTransactionDB,
                                             String ExpextedMonthlyAmtTransactionCR, String ExpextedMonthlyNoTransactionDB, String AvgYearlyIncome, String AvgYearlySales, String ntn,
                                             String TaxIdentificationNumber, String newcustomerType) throws InterruptedException, IOException {


        PlaywrightGenerics.page.locator("//*[@id='msisdn']").fill(accNumber);
        // driver.findElement(By.id("displayName")).sendKeys("ajhabdc");
        PlaywrightGenerics.page.locator("#id7").click();
        PlaywrightGenerics.page.locator(("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[5]/div/table/tbody/tr/td[1]/a/div")).click();

        try {
            migrationCommons.commons(newcustomerType);
        } catch (Exception e) {
            migrationCommons.commons(newcustomerType);
        }


        PlaywrightGenerics.page.wait(1000);

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


            customerToL2Types(accNumber, Name, CNIC, contactNumber, residency,
                    address, ActualOwner, BenificiaryCNIC, ExpextedMonthlyNoTransactionCR, ExpextedMonthlyAmtTransactionDB,
                    ExpextedMonthlyAmtTransactionCR, ExpextedMonthlyNoTransactionDB,
                    AvgYearlyIncome, AvgYearlySales, ntn, TaxIdentificationNumber, newcustomerType);

        } //END if condition

        return true;
    }

    public boolean customerToL2Types(String accNumber, String Name, String CNIC, String contactNumber, String residency
            , String address, String ActualOwner, String BenificiaryCNIC, String ExpextedMonthlyNoTransactionCR, String ExpextedMonthlyAmtTransactionDB,
                                     String ExpextedMonthlyAmtTransactionCR, String ExpextedMonthlyNoTransactionDB, String AvgYearlyIncome, String AvgYearlySales, String ntn,
                                     String TaxIdentificationNumber, String newcustomerType) throws InterruptedException {

        System.out.println("HI ABSAR MUJADDIDI IS TESTING MIGRATION MODULE");

        /////////////////////////////
        ////////KIN DETAILS//////////
        ////////////////////////////

        Locator PoliticalExposed = PlaywrightGenerics.page
                .locator("kycDiv:politicallyExposedDiv:customer.kycDetailsBean.politicallyExposed.politicallyExposed");
        PoliticalExposed.selectOption(setdropdown.setLabel(""));

        Locator accType = PlaywrightGenerics.page.locator("generalInformationDiv:accountTypeDiv:customer.address.typeOfAccount");
        accType.selectOption(setdropdown.setLabel(""));

        ////////////////////////////////
        ////////HARDCODED VALUES////////
        ////////////////////////////////
        //driver.findElement(By.id("fatherHusbandName")).sendKeys("Test Automation");

        //	driver.findElement(By.id("permanentCity")).sendKeys("LAHORE");

        Locator permanentProvince = PlaywrightGenerics.page.locator
                ("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState");

        permanentProvince.selectOption(setdropdown.setLabel(""));

        PlaywrightGenerics.page.wait(5000);

        PlaywrightGenerics.page.locator("#nextOfKinName").fill("");
        PlaywrightGenerics.page.wait(2000);
        PlaywrightGenerics.page.locator("#nextOfKinName").fill(Name);
        //	Assert.assertTrue(accountTitle.matches("^[a-zA-Z\\s]*$"));

        PlaywrightGenerics.page.locator("#nextOfKinCnic").fill("");
        PlaywrightGenerics.page.locator("#nextOfKinCnic").fill(CNIC);
        //Assert.assertTrue(CNIC.matches("^(?=.*[1-9])[0-9+]{5}(-){1}[0-9+]{7}(-){1}[0-9]{1}$"));

        Locator nextofkinNationality = PlaywrightGenerics.page.locator("kycDiv:nextOfKinDiv:nextOfKinNationalityDiv:customer.kycDetailsBean.nextOfKin.nextOfKinNationality");
        nextofkinNationality.selectOption(setdropdown.setLabel(""));

        PlaywrightGenerics.page.locator("#nextOfKinContactNo").fill("");
        PlaywrightGenerics.page.locator("#nextOfKinContactNo").fill(contactNumber);

        PlaywrightGenerics.page.locator("#nextOfKinResidency").fill("");
        PlaywrightGenerics.page.locator("#nextOfKinResidency").fill(residency);

        PlaywrightGenerics.page.locator("#nextOfKinAddress").fill("");
        PlaywrightGenerics.page.locator("#nextOfKinAddress").fill(address);
        //Assert.assertTrue(address.matches("^[0-9]*|^[-0-9a-zA-ZÀ-ÿ .']*$"));

        Locator Relationship = PlaywrightGenerics.page.
                locator("kycDiv:nextOfKinDiv:nextOfKinRelationshipDiv:customer.kycDetailsBean.nextOfKin.nextOfKinRelationship");
        Relationship.selectOption(setdropdown.setLabel("")); ////*[@id="ida3"]


        Locator OccupationType = PlaywrightGenerics.page.locator("kycDiv:occupationDetailsDiv:occupationTypeDiv:customer.kycDetailsBean.occupationDetail.occupationType");

        OccupationType.selectOption(setdropdown.setLabel(""));

        ///html/body/div[1]/div[3]/div[4]/div/div[2]/div/form/div[3]/div[6]/div[3]/div[1]/div[1]/select
        for (int x = 0; x <= 2; x++) {
            try {
                Locator purposeofACCOUNT = PlaywrightGenerics.page.locator
                        ("kycDiv:occupationDetailsDiv:purposeOfAccountDiv:customer.kycDetailsBean.occupationDetail.purposeOfAccount");
                purposeofACCOUNT.selectOption(setdropdown.setLabel(""));
            } catch (Exception e) {
                Locator purposeofACCOUNT = PlaywrightGenerics.page.locator
                        ("kycDiv:occupationDetailsDiv:purposeOfAccountDiv:customer.kycDetailsBean.occupationDetail.purposeOfAccount");
                purposeofACCOUNT.selectOption(setdropdown.setLabel(""));
            }
        }

        Locator beneficialAccount = PlaywrightGenerics.page
                .locator("kycDiv:beneficialOwnerStatusDiv:isBeneficialAccountOwnerDiv:customer.kycDetailsBean.beneficialOwnerStatus.isBeneficialAccountOwner");
        beneficialAccount.selectOption(setdropdown.setLabel(""));

        Locator TxnMode = PlaywrightGenerics.page.locator
                ("kycDiv:modeOfTransactionDiv:customer.kycDetailsBean.modeOfTransaction.modeOfTransaction");
        TxnMode.selectOption(setdropdown.setLabel(""));

        PlaywrightGenerics.page.locator("#expectedCredit").fill("");
        PlaywrightGenerics.page.locator("#expectedCredit").fill(ExpextedMonthlyNoTransactionCR);
        //Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

        PlaywrightGenerics.page.locator("#monthlyExpectedWithdrawal").fill("");
        PlaywrightGenerics.page.locator("#monthlyExpectedWithdrawal").fill(ExpextedMonthlyAmtTransactionCR);
        //Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

        PlaywrightGenerics.page.locator("#expectedDebit").fill("");
        PlaywrightGenerics.page.locator("#expectedDebit").fill(ExpextedMonthlyAmtTransactionDB);
        //Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

        PlaywrightGenerics.page.locator("#monthlyExpectedDeposit").fill("");
        PlaywrightGenerics.page.locator("#monthlyExpectedDeposit").fill(ExpextedMonthlyNoTransactionDB);
        //Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

        PlaywrightGenerics.page.locator("#avergaeYearlyIncome").fill("");
        PlaywrightGenerics.page.locator("#avergaeYearlyIncome").fill(AvgYearlyIncome);
        //Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

        PlaywrightGenerics.page.locator("#averageYearlySales").fill("");
        PlaywrightGenerics.page.locator("#averageYearlySales").fill(AvgYearlySales);
        //Assert.assertTrue(ExpextedMonthlyNoTransactionCR.matches("^[0-9]{1,12}$"));

        PlaywrightGenerics.page.locator("#ntn").fill("");
        PlaywrightGenerics.page.locator("#ntn").fill(ntn);

        Locator TypeOfCustomer = PlaywrightGenerics.page
                .locator("kycDiv:informationDiv:typeOfIncomingCustomerDiv:customer.kycDetailsBean.information.typeOfIncomingCustomer");
        TypeOfCustomer.selectOption(setdropdown.setLabel(""));

        Locator HearingMedium = PlaywrightGenerics.page
                .locator("kycDiv:informationDiv:bankHearingMediumDiv:customer.kycDetailsBean.information.bankHearingMedium");
        HearingMedium.selectOption(setdropdown.setLabel(""));

        //RISK TYPES
        Utility.riskTypes();

        //RESIDENCE STATUS//

        Locator ResidenceStatus = PlaywrightGenerics.page
                .locator("kycDiv:residenceStatusDiv:customer.kycDetailsBean.residenceStatus.residentOrNot");
        ResidenceStatus.selectOption(setdropdown.setLabel(""));

//		// CRS

        Locator CountryOfBirth = PlaywrightGenerics.page
                .locator("kycDiv:crsDiv:crsIndividual:countryOfBirthDiv:customer.kycDetailsBean.crsBean.countryOfBirth");
        CountryOfBirth.selectOption(setdropdown.setLabel(""));

        Locator TaxIsResident = PlaywrightGenerics.page
                .locator("kycDiv:crsDiv:crsIndividual:customer.kycDetailsBean.crsBean.isTaxResident");
        TaxIsResident.selectOption(setdropdown.setLabel(""));

        if (TaxIsResident.textContent().equalsIgnoreCase("No")) {
            Locator TaxResidenceCountry = PlaywrightGenerics.page.locator("#idae");
            TaxResidenceCountry.selectOption(setdropdown.setLabel(""));

            PlaywrightGenerics.page.locator("#idaf").fill(TaxIdentificationNumber);

			/*Select Reason = new Select(driver.findElement(By.id("id94")));
			Reason.selectByVisibleText("Reason C");	*/

            System.out.print("\n \t HI PLEASE ATTEST THAT I AM HERE");

        } //END if condition

        else {

            ElementHandle UsPerson = (ElementHandle) PlaywrightGenerics.page.locator("kycDiv:fatcaDiv:fatcaIndividual:customer.kycDetailsBean.fatca");
            UsPerson.click();
        }

        PlaywrightGenerics.page.locator("//*[@name='update']").click();

        return true;

    }


    ////////////////////////////////////////////////////
    ////////////////MIGRATING GUEST TYPES //////////////
    ///////////////////////////////////////////////////


    //------------------------------------------------------------------------------------------------------------------------------//


    public boolean toGuest(String accNumber, String newcustomerType) throws InterruptedException {


        PlaywrightGenerics.page.locator("//*[@id='msisdn']").fill(accNumber);
        // driver.findElement(By.id("displayName")).sendKeys("ajhabdc");
        PlaywrightGenerics.page.locator("#id7").click();
        PlaywrightGenerics.page.locator("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[5]/div/table/tbody/tr/td[1]/a/div")
                .click();

        try {
            migrationCommons.commons(newcustomerType);
        } catch (Exception e) {
            migrationCommons.commons(newcustomerType);
        }

        if (newcustomerType.equalsIgnoreCase("Guest D/C Block User")) {
            guestBlockTypes(accNumber, newcustomerType);
        }

        return true;
    }


    public boolean guestBlockTypes(String accNumber, String newcustomerType) throws InterruptedException {


        try {


            System.out.println("HI ABSAR MUJADDIDI IS TESTING MIGRATION MODULE - GUEST TYPE MMIGRATION!!!!");

            PlaywrightGenerics.page.wait(1000);
            System.out.println("waaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

            Locator st = PlaywrightGenerics.page.locator("#blackListReason");
            st.selectOption(setdropdown.setLabel(""));

            //	driver.findElement(By.id("motherName")).sendKeys("ASGHAR AFHAN");
            PlaywrightGenerics.page.locator("#permanentCity").fill("LAHORE");
            Locator permanentProvince = PlaywrightGenerics.page
                    .locator("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState");
            permanentProvince.selectOption(setdropdown.setLabel(""));
            PlaywrightGenerics.page.locator("//*[@id='ida3']").click();
        } catch (Exception e) {

            PlaywrightGenerics.page.locator("#motherName").fill("ASGHAR AFHAN");
            PlaywrightGenerics.page.locator("#permanentCity").fill("LAHORE");
            Locator permanentProvince = PlaywrightGenerics.page
                    .locator("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState");
            permanentProvince.selectOption(setdropdown.setLabel(""));

            PlaywrightGenerics.page.locator("//*[@id='ida3']").click();


        }
        return true;
    }


    //---------------------------------------------------------------------------------------------------//
    //--------------------------CORPORATE TYPE MIGRATION-------------------------------------------------//

    public boolean corporatetypes(String accNumber, String accountTitle,
                                  String sellerCODE, String nameCnic, String motherName,
                                  String fatherName, String placeofBirth, String identityNumber, String permanentAddress,
                                  String permanentCity, String mailingAddress, String mailingCity, String email,
                                  String accountHolderMN, String actualOwner, String beneficialCnic,
                                  String expectedCredit, String monthlyExpectedWithdrawal,
                                  String expectedDebit, String monthlyExpectedDeposit,
                                  String avergaeYearlyIncome, String averageYearlySales,
                                  String expectedMonthlyThroughPut, String expectedAvgBalance,
                                  String expectedMonthlyCreditSales,
                                  String expectedMaxAmountPerTransaction,
                                  String expectedNoOfTransaction, String annualTurnover,
                                  String natureOfBusiness, String noTinComments, String newcustomerType) throws InterruptedException {


        try {

            PlaywrightGenerics.page.locator("//*[@id='msisdn']").fill(accNumber);
            // driver.findElement(By.id("displayName")).sendKeys("ajhabdc");
            PlaywrightGenerics.page.locator("#id7").fill("");
            PlaywrightGenerics.page.locator("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[5]/div/table/tbody/tr/td[1]/a/div").click();
        } catch (Exception e) {
            System.out.println("ex message: " + e.getMessage());

        }
        System.out.println("TESTING/MIGRATING CORPORATE TYPES");

        try {
            migrationCommons.commons(newcustomerType);
        } catch (Exception e) {
            migrationCommons.commons(newcustomerType);
        }

        PlaywrightGenerics.page.wait(1000);

        if (newcustomerType.equalsIgnoreCase("Corporate Account Master Wallet")) { //IF STARTED
            customertoCorporateTypes(accNumber, accountTitle,
                    sellerCODE, nameCnic, motherName,
                    fatherName, placeofBirth, identityNumber, permanentAddress,
                    permanentCity, mailingAddress, mailingCity, email,
                    accountHolderMN, actualOwner, beneficialCnic,
                    expectedCredit, monthlyExpectedWithdrawal,
                    expectedDebit, monthlyExpectedDeposit,
                    avergaeYearlyIncome, averageYearlySales,
                    expectedMonthlyThroughPut, expectedAvgBalance,
                    expectedMonthlyCreditSales,
                    expectedMaxAmountPerTransaction,
                    expectedNoOfTransaction, annualTurnover,
                    natureOfBusiness, noTinComments, newcustomerType);
        } //END if condition


        else if (newcustomerType.equalsIgnoreCase("Corporate Sim Sim Account User")) {

            customertoCorporateTypes(accNumber, accountTitle,
                    sellerCODE, nameCnic, motherName,
                    fatherName, placeofBirth, identityNumber, permanentAddress,
                    permanentCity, mailingAddress, mailingCity, email,
                    accountHolderMN, actualOwner, beneficialCnic,
                    expectedCredit, monthlyExpectedWithdrawal,
                    expectedDebit, monthlyExpectedDeposit,
                    avergaeYearlyIncome, averageYearlySales,
                    expectedMonthlyThroughPut, expectedAvgBalance,
                    expectedMonthlyCreditSales,
                    expectedMaxAmountPerTransaction,
                    expectedNoOfTransaction, annualTurnover,
                    natureOfBusiness, noTinComments, newcustomerType);

        } else if (newcustomerType.equalsIgnoreCase("Agent FINJA")) {

            /// for different corporate groups



		/*	customertoCorporateTypes( driver,  accNumber,  accountTitle,
	    			 sellerCODE,  nameCnic,  motherName,
	    			 fatherName,  placeofBirth,  identityNumber , permanentAddress,
	    			 permanentCity,  mailingAddress,  mailingCity,  email,
	    			 accountHolderMN,  actualOwner,  beneficialCnic,
	    			 expectedCredit,  monthlyExpectedWithdrawal,
	    			 expectedDebit,  monthlyExpectedDeposit,
	    			 avergaeYearlyIncome,  averageYearlySales,
	    			 expectedMonthlyThroughPut,  expectedAvgBalance,
	    			 expectedMonthlyCreditSales,
	    			 expectedMaxAmountPerTransaction,
	    			 expectedNoOfTransaction,  annualTurnover,
	    			 natureOfBusiness,  noTinComments,  newcustomerType);
			*/

            PlaywrightGenerics.page.locator("#permanentCity").fill("LAHORE");
            Locator permanentProvince = PlaywrightGenerics.page
                    .locator("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState");
            permanentProvince.selectOption(setdropdown.setLabel(""));

            PlaywrightGenerics.page.locator("#workingWithOtherBank").fill("NO");
            PlaywrightGenerics.page.locator("#netWorth").fill("60000");
            PlaywrightGenerics.page.locator("#typeOfTransaction").fill("Business");
            PlaywrightGenerics.page.locator("#currentBusinessSince").fill("2000");
            Locator premises = PlaywrightGenerics.page.locator("#premises");

            premises.selectOption(setdropdown.setLabel(""));

            PlaywrightGenerics.page.locator("update").click();


        } else if (newcustomerType.equalsIgnoreCase("Agent Finca")) {

            customertoCorporateTypes(accNumber, accountTitle,
                    sellerCODE, nameCnic, motherName,
                    fatherName, placeofBirth, identityNumber, permanentAddress,
                    permanentCity, mailingAddress, mailingCity, email,
                    accountHolderMN, actualOwner, beneficialCnic,
                    expectedCredit, monthlyExpectedWithdrawal,
                    expectedDebit, monthlyExpectedDeposit,
                    avergaeYearlyIncome, averageYearlySales,
                    expectedMonthlyThroughPut, expectedAvgBalance,
                    expectedMonthlyCreditSales,
                    expectedMaxAmountPerTransaction,
                    expectedNoOfTransaction, annualTurnover,
                    natureOfBusiness, noTinComments, newcustomerType);
        }


        return true;
    }


    public boolean customertoCorporateTypes(String accNumber, String accountTitle,
                                            String sellerCODE, String nameCnic, String motherName,
                                            String fatherName, String placeofBirth, String identityNumber, String permanentAddress,
                                            String permanentCity, String mailingAddress, String mailingCity, String email,
                                            String accountHolderMN, String actualOwner, String beneficialCnic,
                                            String expectedCredit, String monthlyExpectedWithdrawal,
                                            String expectedDebit, String monthlyExpectedDeposit,
                                            String avergaeYearlyIncome, String averageYearlySales,
                                            String expectedMonthlyThroughPut, String expectedAvgBalance,
                                            String expectedMonthlyCreditSales,
                                            String expectedMaxAmountPerTransaction,
                                            String expectedNoOfTransaction, String annualTurnover,
                                            String natureOfBusiness, String noTinComments, String newcustomerType) throws InterruptedException {


        System.out.println("HI ABSAR MUJADDIDI IS TESTING MIGRATION MODULE - CORPORAATTE ACCOUNT MASTER WALLET!!!");


        // System.out.println("Selected Customer Type : " + selectCustomerType.getFirstSelectedOption().getText());

        //driver.findElement(By.id("sellerCode")).sendKeys(sellerCODE);

        Locator typeAccount = PlaywrightGenerics.page
                .locator("generalInformationDiv:accountTypeDiv:customer.address.typeOfAccount");
        typeAccount.selectOption(setdropdown.setLabel(""));

        Locator nationality = PlaywrightGenerics.page
                .locator("personalDetailDiv:nationalityDiv:customer.kycDetailsBean.generalInfo.nationality");
        nationality.selectOption(setdropdown.setLabel(""));

        //	driver.findElement(By.id("id4")).sendKeys(accountTitle);
        //	Assert.assertTrue(accountTitle.matches("^[a-zA-Z\\s]*$"));

        PlaywrightGenerics.page.locator("#nameAsPerCnic").fill(nameCnic);
        //Assert.assertTrue(NameCnic.matches("^(?=.*[1-9])[0-9+]{5}(-){1}[0-9+]{7}(-){1}[0-9]{1}$"));

        PlaywrightGenerics.page.locator("#motherName").fill(motherName);
        //Assert.assertTrue(motherName.matches("^[a-zA-Z\\s]*$"));

        PlaywrightGenerics.page.locator("#fatherHusbandName").fill(fatherName);
        //Assert.assertTrue(FatherName.matches("^[a-zA-Z\\s]*$"));

        PlaywrightGenerics.page.locator("#placeOfBirth").fill(placeofBirth);

        PlaywrightGenerics.page.locator("#permanentCity").fill("LAHORE");

        Locator permanentProvince = PlaywrightGenerics.page.locator("contactInfoDiv:addressDiv:permanentAddressDiv:customer.address.permanentState");
        permanentProvince.selectOption(setdropdown.setLabel(""));

        PlaywrightGenerics.page.locator("#permanentAddress").fill(permanentAddress);
        //driver..findElement(By.id("permanentCity")).sendKeys(PermanentCity);

       /* DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
*/
        PlaywrightGenerics.page.locator("#mailingOrBusinessAddress").fill(mailingAddress);

        // dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

        PlaywrightGenerics.page.locator("#mailingOrBusinessCity").fill(mailingCity);

        //driver.findElement(By.id("email")).sendKeys(email);

        Locator pOA = PlaywrightGenerics.page
                .locator("kycDiv:occupationDetailsDiv:purposeOfAccountDiv:customer.kycDetailsBean.occupationDetail.purposeOfAccount");
        pOA.selectOption(setdropdown.setLabel(""));

        PlaywrightGenerics.page.locator("#accountHolderMsisdn").fill(accountHolderMN);

        Locator province = PlaywrightGenerics.page.locator("#permanentState");
        province.selectOption(setdropdown.setLabel(""));

        Locator MailingProvince = PlaywrightGenerics.page
                .locator("contactInfoDiv:addressDiv:mailingOrBusinessAddressDiv:customer.address.mailingOrBusinessState");
        MailingProvince.selectOption(setdropdown.setLabel(""));


        Locator BeneficialAccOwner = PlaywrightGenerics.page
                .locator("kycDiv:beneficialOwnerStatusDiv:isBeneficialAccountOwnerDiv:customer.kycDetailsBean.beneficialOwnerStatus.isBeneficialAccountOwner");
        BeneficialAccOwner.selectOption(setdropdown.setLabel(""));

        //driver.findElement(By.id("actualBeneficialOwner")).sendKeys(actualOwner);

    	/*	Select Relation = new Select(driver.findElement(By.name("kycDiv:beneficialOwnerStatusDiv:beneficialOwnerStatusDetailsDiv:relationWithApplicantDiv:customer.kycDetailsBean.beneficialOwnerStatus.relationWithApplicant")));
    		Relation.selectByVisibleText("Brother");*/

        //driver.findElement(By.id("cnicBeneficial")).sendKeys(beneficialCnic);

        Locator TxnMode = PlaywrightGenerics.page.locator("#modeOfTransaction");
        TxnMode.selectOption(setdropdown.setLabel(""));

        Locator exCredit = PlaywrightGenerics.page.locator("#expectedCredit");
        exCredit.fill(expectedCredit);

        Locator monExWithdrawal = PlaywrightGenerics.page.locator("#expectedCredit");
        monExWithdrawal.fill(monthlyExpectedWithdrawal);

        Locator exDebit = PlaywrightGenerics.page.locator("#expectedDebit");
        exDebit.fill(expectedDebit);

        Locator monExDeposit = PlaywrightGenerics.page.locator("#monthlyExpectedDeposit");
        monExDeposit.fill((monthlyExpectedDeposit));

        Locator avgYearIncome = PlaywrightGenerics.page.locator("#avergaeYearlyIncome");
        avgYearIncome.fill(avergaeYearlyIncome);

        Locator avgYearlySales = PlaywrightGenerics.page.locator("#averageYearlySales");
        avgYearlySales.fill(averageYearlySales);

        Locator exThroughput = PlaywrightGenerics.page.locator("#expectedMonthlyThroughPut");
        exThroughput.fill(expectedMonthlyThroughPut);

        Locator exAvgBalance = PlaywrightGenerics.page.locator("#expectedAvgBalance");
        exAvgBalance.fill(expectedAvgBalance);
        Locator exMonCrSales = PlaywrightGenerics.page.locator("#expectedMonthlyCreditSales");
        exMonCrSales.fill(expectedMonthlyCreditSales);

        Locator exMaxAmtTransaction = PlaywrightGenerics.page.locator("#expectedMaxAmountPerTransaction");
        exMaxAmtTransaction.fill(expectedMaxAmountPerTransaction);

        Locator exNoTransaction = PlaywrightGenerics.page.locator("#expectedNoOfTransaction");
        exNoTransaction.fill(expectedNoOfTransaction);

        Locator annualTurnOver = PlaywrightGenerics.page.locator("#annualTurnover");
        annualTurnOver.fill(annualTurnover);

        Locator natureBusiness = PlaywrightGenerics.page.locator("#natureOfBusiness");
        natureBusiness.fill(natureOfBusiness);

        Locator business = PlaywrightGenerics.page.locator("#sourcesOfIncomeOther");
        business.fill("Business");

        Locator businessType = PlaywrightGenerics.page.locator("#typeOfBusiness");
        businessType.selectOption(setdropdown.setLabel("Service"));

        Locator notinComments = PlaywrightGenerics.page.locator("#noTinComments");
        notinComments.fill(noTinComments);

        Utility.riskTypes();

        Locator CountryOfBirth = PlaywrightGenerics.page
                .locator("kycDiv:crsDiv:crsIndividual:countryOfBirthDiv:customer.kycDetailsBean.crsBean.countryOfBirth");
        CountryOfBirth.selectOption(setdropdown.setLabel(""));


        Locator TaxResidentYN = PlaywrightGenerics.page
                .locator("kycDiv:crsDiv:crsIndividual:customer.kycDetailsBean.crsBean.isTaxResident");
        TaxResidentYN.selectOption(setdropdown.setLabel(""));

        ElementHandle UsPerson = (ElementHandle) PlaywrightGenerics.page.locator("kycDiv:fatcaDiv:fatcaIndividual:customer.kycDetailsBean.fatca");
        UsPerson.click();

        //Utility.Submit(driver.);


        PlaywrightGenerics.page.wait(1500);

        ///////////////////////////////////////
        //////AUTOMATING DIRECTOR'S PAGE///////
        //////////////////////////////////////

        PlaywrightGenerics.page.locator("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[2]/div/input")
                .fill("Absar ahmed Mujaddidi");

        PlaywrightGenerics.page.locator("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[3]/div/input")
                .fill("lahore rehman gardens");

        Locator ID = PlaywrightGenerics.page.locator("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[4]/div/select");
        ID.selectOption(setdropdown.setLabel(""));

        PlaywrightGenerics.page.locator("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[5]/div/input").fill("3001254");

        Locator residentCountry = PlaywrightGenerics.page
                .locator("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[6]/div/select");
        residentCountry.selectOption(setdropdown.setLabel(""));

        PlaywrightGenerics.page
                .locator("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[2]/div[2]/div[7]/div/input").fill("Lahore");

        //////////////////////////////////////////
        //SELECTING RANDOM DATE FOR DIRECTOR DOB//
        //////////////////////////////////////////

    	/*	try {
    			driver.findElement(By.id("directorDateofBirth")).click();
    			Thread.sleep(500);
    			Utility.selectDate(driver, month_year,day);

    			Thread.sleep(500);
    		}catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} */

        //for proceeding after adding director//
        PlaywrightGenerics.page.locator("#idb8").click();
        PlaywrightGenerics.page.wait(5000);

        Thread.sleep(250);
        //CLICKING ON CONTINUE BUTTON ON DIRECTOR'S PAGE//
        PlaywrightGenerics.page.locator("/html/body/div[1]/div[3]/div[4]/div/div[2]/div/div/form/div[2]/div[3]/div/input[3]").click();

        Thread.sleep(300);

        //CLICKING ON CONTINUE BUTTON ON CORPORATE CUSTOMER ONBOARDING PAGE AFTER ADDING DIRECTOR//
        PlaywrightGenerics.page.locator("/html/body/div/div[3]/div[4]/div/div[2]/div/div/form/div[4]/div/input[1]").click();
        PlaywrightGenerics.page.locator("//*[@name='update']").click();

        return true;


    }


}
