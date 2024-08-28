package utilities.listeners;

import com.aventstack.extentreports.Status;
import org.testng.IAlterSuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utilities.extentreport.ExtentReport;

import java.util.ArrayList;
import java.util.List;

public class TestNGListener implements IAlterSuiteListener, ITestListener {

    final private String REPORT_NAME_PARAM = "ReportName";
    private static XmlSuite suite;

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    @Override
    public void onTestFailure(ITestResult Result)
    {
        ExtentReport.getTest().log(Status.FAIL ,Result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

    @Override
    public void alter(List<XmlSuite> suites) {

        suite = suites.get(0);
        List<XmlTest> testsList = suite.getTests();
        suite.setTests(new ArrayList<>());
        List<XmlTest> updatedTestsList = new ArrayList<>();

        for (XmlTest test : testsList) {
            XmlTest newTest = getXmlTest(test);
            updatedTestsList.add(newTest);
        }
        suite.setTests(updatedTestsList);
    }

    private XmlTest getXmlTest(XmlTest test) {
        String testName = test.getName();

        XmlTest newTest = new XmlTest(suite);

        newTest.setName(testName);
        newTest.setClasses(test.getClasses());

        newTest.addParameter(REPORT_NAME_PARAM, testName);
        return newTest;
    }
}