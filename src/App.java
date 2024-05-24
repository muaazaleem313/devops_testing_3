import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "src\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
         
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200");

        // Initialize ChromeDriver with options
        WebDriver driver = new ChromeDriver(options);
        // Test Case: Invalid Login
        testInvalidLogin(driver);

        // Test Case: Valid Login
        testValidLogin(driver);

        testLogout(driver);

        testInvalidUser(driver);

        testEmptyFields(driver);

        

        //driver.close();
    }

    private static void testInvalidLogin(WebDriver driver) {
        driver.navigate().to("http://localhost:3001/auth");

        // Provide invalid login credentials
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[1]/div[1]/div/div/input")).sendKeys("az@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[1]/div[2]/div/div/input")).sendKeys("12");

        // Click on the login button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/button[1]")).click();

        // Add a wait to ensure the error message has time to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Check if the error message is displayed
        if (driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/p")).isDisplayed()) {
            System.out.println("Login with Incorrect Credentials: Passed");
        } else {
            System.out.println("Login with Incorrect Credentials: Failed");
        }
    }

    private static void testValidLogin(WebDriver driver) {
        driver.navigate().to("http://localhost:3001/auth");

        // Provide valid login credentials
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[1]/div[1]/div/div/input"))
                .sendKeys("ahmadmuhdpk@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[1]/div[2]/div/div/input"))
                .sendKeys("123456");

        // Click on the login button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/button[1]")).click();

        // Add a wait to ensure the next page has time to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Check if a specific element on the next page is displayed (adjust the XPath accordingly)
        if (driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div/h6")).isDisplayed()) {
            System.out.println("Login with Valid Credentials: Passed");
        } else {
            System.out.println("Login with Valid Credentials: Failed");
        }
    }
    
    private static void testLogout(WebDriver driver) {

        // Click on the logout button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div/button/span[1]")).click();

        // Add a wait to ensure the next page has time to load
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Check if a specific element on the next page is displayed (adjust the XPath accordingly)
        if (driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[2]/div/button/span[1]"))
                .isDisplayed()) {
            System.out.println("Logout Test: Passed");
        } else {
            System.out.println("Logout Test: Failed");
        }
    }
    
    private static void testEmptyFields(WebDriver driver) {
        driver.navigate().to("http://localhost:3001/auth");

        // Do not provide any login credentials

        // Click on the login button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/button[1]")).click();

        // Add a wait to ensure any error messages have time to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Check if the error messages for empty fields are displayed
        if (driver.findElement(By.xpath("/*[@id=\"root\"]/div/main/div/form/div[1]/div[1]/div/div/small")).isDisplayed()
                &&
                driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[1]/div[2]/div/div/small"))
                        .isDisplayed()) {
            System.out.println("Login with Empty Fields: Passed");
        } else {
            System.out.println("Login with Empty Fields: Failed");
        }
    }


    private static void testInvalidUser(WebDriver driver) {
        driver.navigate().to("http://localhost:3001/auth");

        // Provide invalid login credentials
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[1]/div[1]/div/div/input")).sendKeys("a@gmail.com");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/div[1]/div[2]/div/div/input")).sendKeys("123456");

        // Click on the login button
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/button[1]")).click();

        // Add a wait to ensure the error message has time to appear
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        // Check if the error message is displayed
        if (driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/form/p")).isDisplayed()) {
            System.out.println("User does not exist: Passed");
        } else {
            System.out.println("User does not exist: Failed");
        }
    }

}
