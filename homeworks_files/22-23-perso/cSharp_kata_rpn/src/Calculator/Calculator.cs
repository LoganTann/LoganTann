using System;

/**
 * @file src/Calculator/Calculator.cs
 */
namespace Calculator
{
    public class Calculator
    {
        public int Process(string input)
        {
            List<string> operationsAsList = input.Split(" ").Reverse().ToList();
            Stack<string> operations = new Stack<string>(operationsAsList);

            while (operations.Count() > 1)
            {
                int a = Int32.Parse(operations.Pop());
                int b = Int32.Parse(operations.Pop());
                string operation = operations.Pop();

                string result = processOneOperation(a, b, operation).ToString();
                operations.Push(result);
            }

            return Int32.Parse(operations.Pop());
        }

        private int processOneOperation(int a, int b, string operation)
        {
            if (operation == "+")
            {
                return a + b;
            }
            if (operation == "-")
            {
                return a - b;
            }
            return -9999999;
        }
    }
}