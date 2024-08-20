package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Activity2 {

    //Write a method that takes firstName and Last Name as parameters
    //and Return full name in format LASTNAME, Firstname
    // mohammad -> Mohammad

    //Step 2) Write A test in TestNG to test above method functionality
    public String getFullName(String firstName, String lastName) {
        if (firstName == null || lastName == null)
            throw new RuntimeException("FirstName or Last Name can NOT be null");

        if (firstName.isEmpty() || lastName.isEmpty())
            throw new RuntimeException("First Name or Last Name can NOT be Empty");

        lastName = lastName.trim();
        firstName = firstName.trim();

        return lastName.toUpperCase() + ", " +
                firstName.substring(0, 1).toUpperCase() +
                firstName.substring(1).toLowerCase();
    }

    @Test(dataProvider = "positiveTestData")
    public void positiveTesting(String firstName, String lastName, String expectedFullName) {
        String fullName = getFullName(firstName, lastName);

        Assert.assertEquals(fullName, expectedFullName, "FullName should match format");
    }

    @DataProvider(name = "positiveTestData")
    private String[][] positiveTestData() {
        String[][] data = {
                {"mohammad", "shokriyan", "SHOKRIYAN, Mohammad" },
                {"JoHN", "SMITH", "SMITH, John" },
                {" ALEN ", " smith ", "SMITH, Alen" },

        };
        return data;
    }

    @Test
    public void negativeTesting() {
        try {
            getFullName(null, null);
            Assert.fail("Test Supposed to throw Exception");
        }catch (RuntimeException ex) {
            Assert.assertTrue(true, "Catch the Exception Passing the Test");
        }
    }

    @Test(expectedExceptions = {RuntimeException.class})
    public void testNegativeWithExpectedException() {
        getFullName("", null);
    }

    @Test
    public void testNegativeWithAssertionThrow() {
        Assert.assertThrows(RuntimeException.class, () -> {
            getFullName("", "");
        });
    }



}