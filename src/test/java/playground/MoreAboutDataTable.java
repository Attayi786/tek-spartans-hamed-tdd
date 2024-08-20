package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MoreAboutDataTable {


    @Test(dataProvider = "testingData1")
    public void someTesting(String name, Integer expectedLength) {

        Assert.assertEquals(name.length(), expectedLength, "Name length should match");

    }

    @DataProvider(name = "testingData1")
    public Object[][] testDataWithObject() {
        Object[][] data = {
                {"Ali", 3},
                {"Alen", 4},
                {"Mohammad", 8}
        };
        return data;
    }

    @DataProvider
    public Person[] testWithCustomPOJO() {
        Person[] data = {
                new Person("Ali", 3),
                new Person("Alen", 4)
        };
        return data;
    }

    @Test(dataProvider = "testWithCustomPOJO")
    public void someTestingCustomObject(Person person) {

        Assert.assertEquals(person.getName().length(), person.getLength(), "Name length should match");

    }

}