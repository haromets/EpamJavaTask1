
/*
Write on Java a "microframe" capable of doing the following:
1. Respond to commands:
A. open (url, timeout)
B. checkLinkPresentByHref (href)
C. checkLinkPresentByName (link name) (Check for a link on the page with the specified human-readable part.)
D. checkPageTitle (text) (Check the value of the page header.)
E. checkPageContains (text) (Check for presence of the specified text on the page.)
2. Read the instructions from the specified TXT file (each line is a separate instruction).
3. Record the work log in the specified TXT file in the format:
A. "+" If the test has passed, "!" If not;
B. The original command and its parameters;
C. Time of execution (accurate to a thousandth of a second).
4. At the end of the log file, the following data should be provided:
A. Total tests performed = NNN.
B. Passed successfully / failed = XXX / YYY.
C. Total time of test execution = time accurate to a thousandth of a second.
D. Average test time = time accurate to a thousandth of a second.

Example input file:
open "http://www.google.com" "3"
checkPageTitle "Google Search Page"
checkPageContains "The best search engine"

Example of a log file:
+ [open "http://www.google.com" "3"] 0.234
! [checkPageTitle "Google Search Page"] 0.002
! [checkPageContains "The best
Search engine "] 0.003
Total tests: 3
Passed / Failed: 1/2
Total time: 0.239
Average time: 0.171
 */

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class CoreOfProgram {
    public static void main(String[] args) {
        File myfile = new File("d:\\tempFile.txt");
        CoreOfProgram coreOfProgram = new CoreOfProgram();
        DOCExtractor docExtractor = new DOCExtractor();
        HTMLExtractor htmlExtractor = new HTMLExtractor();
        LogFile logFile = new LogFile();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(myfile));
            String inputLine;
            String resultFromDoc;
            String resultFromHTML;
            String HTMLText = null;
            inputLine = reader.readLine();
            if (inputLine.indexOf("open") != -1) {
                    resultFromDoc = docExtractor.extractLinkOpen(inputLine);
                    double time;
                    long timeStratOpen = System.nanoTime();
                    if (coreOfProgram.openUrl(resultFromDoc) != null ){
                        long timeFinishOpen = System.nanoTime();
                        double timeResult =(double) (timeFinishOpen - timeStratOpen) / 1000000000;
                        HTMLText = coreOfProgram.readFromUrl(resultFromDoc);
                        time = docExtractor.extractTime(inputLine);
                        if (timeResult<time){
                            logFile.writeLogFile("+ [open \"" + resultFromDoc + "\" " + "\"" + time + "\"] " +
                                    String.format("%.3f", timeResult));
                            logFile.totalTest++;
                            logFile.passed++;
                            logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                        } else {
                            logFile.writeLogFile("! [open \"" + resultFromDoc + "\" " + "\"" + time + "\"] " +
                                    String.format("%.3f", timeResult));
                            logFile.totalTest++;
                            logFile.failed++;
                            logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                        }
                    } else {
                        System.out.println("Invalid url entered");
                    }

                } else {
                System.out.println("No open command");
                System.exit(0);
            }
            while ((inputLine = reader.readLine()) != null) {
                 if (inputLine.indexOf("checkLinkPresentByHref ") != -1) {
                    resultFromDoc = docExtractor.extractLink(inputLine);
                    resultFromHTML = htmlExtractor.extractLinkPresentByHref(HTMLText, resultFromDoc);
                    long timeStratOpen = System.nanoTime();
                    if (resultFromHTML != null && resultFromHTML.indexOf(resultFromDoc) !=-1) {
                        long timeFinishOpen = System.nanoTime();
                        logFile.writeLogFile("+ [checkLinkPresentByHref \"" + resultFromDoc + "\"] " +
                                String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                        logFile.totalTest++;
                        logFile.passed++;
                        logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                    } else {
                        long timeFinishOpen = System.nanoTime();
                        logFile.writeLogFile("! [checkLinkPresentByHref \"" + resultFromDoc + "\"] " +
                                String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                        logFile.totalTest++;
                        logFile.failed++;
                        logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                    }
                }
                else if (inputLine.indexOf("checkLinkPresentByName ") != -1) {
                        resultFromDoc = docExtractor.extractLink(inputLine);
                        resultFromHTML = htmlExtractor.extractLinkPresentByName(HTMLText, resultFromDoc);
                        long timeStratOpen = System.nanoTime();
                        if (resultFromHTML != null && resultFromHTML.indexOf(resultFromDoc) !=-1) {
                            long timeFinishOpen = System.nanoTime();
                            logFile.writeLogFile("+ [checkLinkPresentByName \"" + resultFromDoc + "\"] " +
                                    String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                            logFile.totalTest++;
                            logFile.passed++;
                            logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                        } else {
                            long timeFinishOpen = System.nanoTime();
                            logFile.writeLogFile("! [checkLinkPresentByName \"" + resultFromDoc + "\"] " +
                                    String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                            logFile.totalTest++;
                            logFile.failed++;
                            logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                        }
                    }
                 else if (inputLine.indexOf("checkPageTitle ") != -1) {
                        resultFromDoc = docExtractor.extractLink(inputLine);
                        resultFromHTML = htmlExtractor.extractPageTitle(HTMLText, resultFromDoc);
                        long timeStratOpen = System.nanoTime();
                        if (resultFromHTML != null && resultFromHTML.indexOf(resultFromDoc) !=-1) {
                            long timeFinishOpen = System.nanoTime();
                            logFile.writeLogFile("+ [checkPageTitle \"" + resultFromDoc + "\"] " +
                                    String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                            logFile.totalTest++;
                            logFile.passed++;
                            logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                        } else {
                            long timeFinishOpen = System.nanoTime();
                            logFile.writeLogFile("! [checkPageTitle \"" + resultFromDoc + "\"] " +
                                    String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                            logFile.totalTest++;
                            logFile.failed++;
                            logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                        }
                    }
                 else if (inputLine.indexOf("checkPageContains ") != -1) {
                            resultFromDoc = docExtractor.extractLink(inputLine);
                            long timeStratOpen = System.nanoTime();
                            if (HTMLText.indexOf(resultFromDoc) != -1) {
                                long timeFinishOpen = System.nanoTime();
                                logFile.writeLogFile("+ [checkPageContains  \"" + resultFromDoc + "\"] " +
                                        String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                                logFile.totalTest++;
                                logFile.passed++;
                                logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                            } else {
                                long timeFinishOpen = System.nanoTime();
                                logFile.writeLogFile("! [checkPageContains  \"" + resultFromDoc + "\"] " +
                                        String.format("%.3f", (double) (timeFinishOpen - timeStratOpen) / 1000000000));
                                logFile.totalTest++;
                                logFile.failed++;
                                logFile.totalTime = logFile.totalTime + (double) (timeFinishOpen - timeStratOpen) / 1000000000;
                            }
                        }
                    }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logFile.writeResults(logFile.totalTest, logFile.passed, logFile.failed, logFile.totalTime);
        System.out.println("The log file is generated! Default D:/logFile.txt");

    }
    public String readFromUrl (String url) {
        StringBuilder sbHTML = new StringBuilder();
        try { 
            BufferedReader br = new BufferedReader(new InputStreamReader(openUrl(url).getInputStream()));
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sbHTML.append(inputLine);
                sbHTML.append("\n");
            }
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String HTMLText = new String(sbHTML);
        return HTMLText;
    }

    public URLConnection openUrl(String urlText) {

        URLConnection conn = null;
        try {
            URL url = new URL(urlText);
            conn = url.openConnection();
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            return conn;
    }
}
