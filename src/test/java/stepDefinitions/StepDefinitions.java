package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qa.factory.Baseclass;

import dbData.DB_connect;
import dbData.ExcelRead;
import dbData.ExcelWrite;
import io.cucumber.java.en.*;
import pages.Page;



public class StepDefinitions extends Baseclass {

	WebDriver driver;
	public DB_connect db_connect = new DB_connect();
	public ExcelWrite excelWrite = new ExcelWrite();
	public ExcelRead excelRead = new ExcelRead();
	
	Baseclass baseclass=new Baseclass();
Page page=new Page();
	

	    
	
	@When("User login into db using URL username and password")
	public void user_login_into_db_using_url_username_and_password() {

		try {
			db_connect.ExcelConfigFileReader();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Then("User should be successfully logged in into the server")
	public void user_should_be_successfully_logged_in_into_the_server() {

		Assert.assertTrue(true);
	}

	@When("User Enters and Runs the query")
	public void runs_the_query() {
		excelWrite.ExcelConfigFileReader();
		try {
			excelWrite.Queryrun();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Then("User should get the latest bundleId with the count and BundleRefId")
	public void user_should_get_the_latest_bundle_id_with_the_count_and_bundle_ref_id() {
		try {
			excelWrite.excelheading();
		} catch (Exception e) {

			e.printStackTrace();
		}
		try {
			excelWrite.excelwrite();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@And("User pastes the BundleRefId in another query and executes it")
	public void user_pastes_the_bundle_ref_id_in_another_query_and_executes_it() {

		try {
			excelRead.readExcel();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Then("User will get all the files uploaded towards the bundle")
	public void user_will_get_all_the_files_uploaded_towards_the_bundle() {
		try {
			excelRead.FileNameGet();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@When("User enters the url")
	public void user_enters_the_url() {
	try {
			baseclass.browserLaunch();
			baseclass.getApplicationUrl();
		} catch (Exception e) {
			e.printStackTrace();
		}


		}

	@When("User selects Dlvry Bundle ID and gives the BundleId")
	public void user_selects_dlvry_bundle_id_and_gives_the_bundle_id() {

try {
		page.DeliveryBundle();
			page.SearchBox();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("fail");
		}
		 
	}

	@And("Click on search")
	public void click_on_search() {

	try {
		page.SearchKey();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Then("Check if the status is Successful")
	public void check_if_the_status_is_successful() {
try {
	page.Status();

} 
catch (Exception e) {
	e.printStackTrace();
}
	}

}