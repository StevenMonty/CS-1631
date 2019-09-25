# Deliverable-4

Hello Dustin,

Thank you for reading, please see our assignment here!

**Challenges:**
* Finding a testing plan that didn't overpromise or oversell our performance testing capabilities.
	* There is potential for variation with results, especially due to the Internet (TCP/IP), limitations/variations of computer's hardware etc. 
	* We found some good starter copy "Beautiful Testing: Professional Software Practive" in chapter 3
* Largely performance is a subjective measure
	* The requirements for each functionality should be determined and agreed upon by the lead developer, performance tester, and project manager
	* We used (based on early testing):
		* MAX_USERS: 100
		* AVG_USERS: 10
		* LONG_TIME (for stress test): 6 minutes (this would probably be much higher for real-life applications)
* Understanding throughput and how to interpret and present metrics in a succinct format
* Issue with consistency in testing 
	* On the first ramp up test, the maximum number of users the program was able to support was 40 without breaking the 300ms threshold. Then I realized that I still had a number of other applications running in the background (Chrome, Atom, Word, Outlook). After closing them and only running Activity Monitor at the same time as the Ramp Up test, the program was able to support 42 concurrent users. During the test, my CPU usage peaked at around ~760%, while only using 12.4MB of RAM. After hitting the 42 user mark, there were occasional requests that just cleared the 300ms limit (299.08 ms) with 46 users, but after clarification on the benchmark rules, I learned that **any** request above 300ms for a given number of users does not count as being able to support that number of users. To confirm this, I performed another Ramp Up tests under the same system conditions as the second test and set the max users to 42. The results showed that the maximum number of users that the system could reliably support was between 42 and 44. During this final test, my CPU utilization peaked at ~800% and memory remained at 12.4MB throughout the test.

Thank you,

Steven and Alexis
