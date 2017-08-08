# JavaTask1
/*
Write on Java a "microframe" capable of doing the following:
1. Respond to commands:

a. open (url, timeout)

b. checkLinkPresentByHref (href)

c. checkLinkPresentByName (link name) (Check for a link on the page with the specified human-readable part.)

d. checkPageTitle (text) (Check the value of the page header.)

e. checkPageContains (text) (Check for presence of the specified text on the page.)

2. Read the instructions from the specified TXT file (each line is a separate instruction).

3. Record the work log in the specified TXT file in the format:

a. "+" If the test has passed, "!" If not;

b. The original command and its parameters;

c. Time of execution (accurate to a thousandth of a second).

4. At the end of the log file, the following data should be provided:

a. Total tests performed = NNN.

b. Passed successfully / failed = XXX / YYY.

c. Total time of test execution = time accurate to a thousandth of a second.

d. Average test time = time accurate to a thousandth of a second.

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
