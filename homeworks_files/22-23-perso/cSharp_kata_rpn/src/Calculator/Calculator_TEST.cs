namespace Calculator;
using Xunit;

/**
 * @file src/Calculator/Calculator_TEST.cs
 */
public class RpnTest
{
    [Fact]
    public void Process_shouldComputeTwo_whenGivingOneOnePlus()
    {
        // Given
        string input = "1 1 +";
        Calculator rpnCalc = new Calculator();

        // When
        int result = rpnCalc.Process(input);

        // Then
        Assert.Equal(2, result);
    }
    [Fact]
    public void Process_shouldComputeTwo_whenGivingThreeOneMinus()
    {
        // Given
        string input = "3 1 -";
        Calculator rpnCalc = new Calculator();

        // When
        int result = rpnCalc.Process(input);

        // Then
        Assert.Equal(2, result);
    }
    [Fact]
    public void Process_shouldComputeWell_whenGivingMultipleSequences()
    {
        // Given
        string input = "4 2 + 3 -"; // (4 + 2) - 3 = 3
        Calculator rpnCalc = new Calculator();

        // When
        int result = rpnCalc.Process(input);

        // Then
        Assert.Equal(3, result);
    }
}