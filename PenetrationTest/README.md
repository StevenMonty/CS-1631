# Deliverable-3.5 - PEN TESTING EXTRA CREDIT 

## VUNLERABILITY #1

**URL:** http://demo.testfire.net/login.jsp

**VULNERABILITY:** SQL Injection

**STEPS TO EXPLOIT:** 

	1. Navigate to login page (http://demo.testfire.net/login.jsp)
	2. In the username input field use a SQL injection (e.g., `r' or 1 =1--`)
	3. In the password input field type anything
	4. Unauthorized access granted.

**A. What part of the InfoSec Triad does this vulnerability attack (confidentiality, integrity, or availability)?**

* Confidentiality (as aunthorized users may read data)

**B. What kind of security attack can exploit this vulnerability (interruption, interception, modification, or fabrication)?**

* Interception 
* Modification (This attack gives the user Admin priviledges, granting them access to all accounts in the bank) SM

**C. Are attacks that exploit this vulnerability active or passive?**

* Passive (since there is no data to attack unless you get the correct username; potential for an active attack)
* Active ( I would say that there is data to attack- all the account information/balances/ability to transfer money) SM

**D. What business value would be lost due to exploiting this vulnerability (data loss, unauthorized access, denial of service, etc)?**

* Unauthorized access
* Compromised customer financial information

**E. What steps should the development team take to fix this vulnerability?**

* Sanitize input fields


## VUNLERABILITY #2

**URL:** http://demo.testfire.net/login.jsp

**VULNERABILITY:** Simple Password

**STEPS TO EXPLOIT:**

	1. Navigate to login page (http://demo.testfire.net/login.jsp)
	2. In the username input field type [admin]
	3. In the password input field type [admin]
	4. Unauthorized access to admin account granted.


**A. What part of the InfoSec Triad does this vulnerability attack (confidentiality, integrity, or availability)?**

* Integrity (as unauthorized users can modify data and transfer amounts inbetween accounts)

**B. What kind of security attack can exploit this vulnerability (interruption, interception, modification, or fabrication)?**

* Modification (since it's modifying existing data and money within the system)

**C. Are attacks that exploit this vulnerability active or passive?**

* Active (since money can be transfered)

**D. What business value would be lost due to exploiting this vulnerability (data loss, unauthorized access, denial of service, etc)?**

* Unauthorized access
* Note: We can get the authorization token (in the admin case: WVdSdGFXND06WVdSdGFXND06Pzs/ZW8/Lw==) using a POST requesst to http://demo.testfire.net/api/login (the body in JSON with values "admin"). We are then able to do anything that the admin can do, at their authorization level.

**E. What steps should the development team take to fix this vulnerability?**

* Update admin password to be more challenging to guess (e.g., capital letters, number, symbols, 8+ characters, no basic passwords)


## VUNLERABILITY #3

**URL:** http://crackme.cenzic.com/kelev/php/login.php

**VULNERABILITY:** XSS

**STEPS TO EXPLOIT:**

	1. Navigate to login page ()
	2. In the username input field type in a XSS attack ['';!--"<XSS>=&{()}]
	3. In the password input field type in a XSS attack ['';!--"<XSS>=&{()}]
	4. Unauthorized access granted.


**A. What part of the InfoSec Triad does this vulnerability attack (confidentiality, integrity, or availability)?**

* Confidentiality (as aunthorized users may read data)

**B. What kind of security attack can exploit this vulnerability (interruption, interception, modification, or fabrication)?**

* Interception (as there is no information for this account)

**C. Are attacks that exploit this vulnerability active or passive?**

* Passive (since there is no data to attack unless you get the correct username; potential for an active attack)

**D. What business value would be lost due to exploiting this vulnerability (data loss, unauthorized access, denial of service, etc)?**

* Unauthorized access

**E. What steps should the development team take to fix this vulnerability?**

* Avoid trusting inputs by HTML, attribute, CSS, and JavaScript escaping 
* Sanitize input fields
* Enable XSS protection header (https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/X-XSS-Protection)
* Go through this checklist: https://github.com/OWASP/CheatSheetSeries/blob/master/cheatsheets/Cross_Site_Scripting_Prevention_Cheat_Sheet.md
