# Performance Test Plan

## Key
* **MAX_USERS** = determined by intial testing
* **AVG_USERS** = **MAX_USERS** / 10
* **LONG_TIME** = 5 minutes
* **AVG_TIME** = 35 seconds
* **USER** = 10 ms thread delay in the "Constant Timer"

## Initial Test
* How did we find key values?
	* Step 1: Run a ramp up test to find turning point in throughput curve, where the slope approaches 0, to identify the max throughput
		* Run with a **USER** 
	* Step 2: Run another ramp up test to understand the full performance, get the bigger picture
		* Run with a **USER**

## Tests
* **Baseline**: Collect baseline system performance metric and vertify that each functional task included in the system usage model achieves performance requestments unders a user load of **1** for each performance testing build in which the functional task has been implemented:
	* **Functional Test**: When sending a JSON in the body of the request with "input" as the property and a number as its value (e.g., `{ "input": 1 }`), the program returns the tangent of the number value (e.g., `{ "response": 1.55740772465 }`).
	* **Why?**: 
		* Smoke-Testing, to ensure that the base functionality of the program is functioning
* **Soak/Stablility/Realistic Load Test**: Collect system performance metrics and verify that each functional task included in the system usage model achieves performance requirements under a user load of **AVG_USERS** for a **LONG_TIME** for each performance testing build in which the functional task has been implemented:
	* **Functional Test**: When sending a JSON in the body of the request with "input" as the property and a number as its value (e.g., `{ "input": 1 }`), the program returns the tangent of the number value (e.g., `{ "response": 1.55740772465 }`).
	* **Why?**: 
		* To ensure that the system under test is able to maintain performance and be available for a long period of time with an average number of users
		* To determine whether the performance optimizations are interfering with basic functionality 
* **Ramp Up**: Collect system performance metrics and verify that each functional task included in the system usage model achieves performance requirements under the following loads to the degree that the usgae model has been implemented in the each performance testing build:
	* **Functional Tests**: 
		* Increasing loads from **1** sers to **AVG_USERS** users for **AVG_TIME**
		* Increasing loads from **1** users to **MAX_USERS** users for **AVG_TIME**
		* Additional loads may be tested, upon request
	* **Why?**: 
		* To ensure that the system under test can maintain performance with an upward influx in users
		* To understand the throughput (how many events can occur and be processed in a given amount of time)
* **Stress Test**: Collect system performance metrics and verify that the system usage model achieves performance requirements for the duration of a **LONG_TIME**, **MAX_USERS** stress test on performance testing builds that the team (Steven/Alexis) deam appropriate. 
	* **Why?**: 
		* To ensure that the system under test can withstand high usage for long periods at a time and still be available for users
		* To understand and establish what uptime goals should look like (i.e., how many 9's can the program under test maintain uptime)
	
## Hardware Specs
* **Steven's Computer Specs:**
	* CPU: Intel Core i7 
		* Base speed: 2.2Ghz
		* Logical Processors: 4
	* Memory: 16 GB RAM (2 x * GB Chips)
		* Speed: 1600MHz each
* **Alexis' Laptop Specs:**
	* CPU: Intel(R) Core (8 CPUs)
		* Base speed: ~1.9GHz
		* Logical Processors: 8
	* Memory: 8192MB RAM
		* Speed: ~2,400 MHz
		
