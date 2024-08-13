package playground;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UsingTestData {

    @Test(dataProvider = "addingData")
    public void testAdding(int firstNumber, int secondNumber, int expectedResult) {

        int result = firstNumber + secondNumber;

        Assert.assertEquals(result, expectedResult);
    }

    @DataProvider(name = "addingData")
    private Integer[][] testData() {
        Integer[][] data = {
                {4, 6, 10},
                {10, 20, 30},
                {20, 25, 45}
        };

        return data;
    }
}
