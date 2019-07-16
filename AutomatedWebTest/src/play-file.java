/*
	 * REQUIREMENT 2:
	 * Every page shall include five links at the top, to "CS1632 D3 Home", "Factorial", "Fibonacci", 
	 * "Hello", and "Cathedral Pics". These shall link to /, /fact, /fib, /hello, and /cathy, respectively.
	 * 
	 */
	
	@Test
	public void homepageLinksToHomepage() {
		//get actual URL
		driver.get("https://cs1632summer2019.herokuapp.com/");
		assertLinks();
	}
	
	@Test
	public void homepageLinksToFact() {
		//get actual URL
		driver.get("https://cs1632summer2019.herokuapp.com/fact");
		assertLinks();
	}
	
	@Test
	public void homepageLinksToFiboncci() {
		//get actual URL
		driver.get("https://cs1632summer2019.herokuapp.com/fib");
		assertLinks();
	}
	
	@Test
	public void homepageLinksToHello() {
		//get actual URL
		driver.get("https://cs1632summer2019.herokuapp.com/hello");
		assertLinks();
	}

	@Test
	public void homepageLinksToCathy() {
		//get actual URL
		driver.get("https://cs1632summer2019.herokuapp.com/cathy");
		assertLinks();
	}
	
	
	public void assertLinks(){
		String expectedHome = "https://cs1632summer2019.herokuapp.com/";
		String attributeHome = driver.findElement(By.linkText("CS1632 D3 Home")).getAttribute("href");		
        assertEquals(attributeHome, expectedHome);
        
		String expectedFact = "https://cs1632summer2019.herokuapp.com/fact";
		String attributeFact = driver.findElement(By.linkText("Factorial")).getAttribute("href");		
        assertEquals(attributeFact, expectedFact);
        
		String expectedFib = "https://cs1632summer2019.herokuapp.com/fib";
		String attributeFib = driver.findElement(By.linkText("Fibonacci")).getAttribute("href");		
        assertEquals(attributeFib, expectedFib);
        
		String expectedHello = "https://cs1632summer2019.herokuapp.com/hello";
		String attributeHello = driver.findElement(By.linkText("Hello")).getAttribute("href");		
        assertEquals(attributeHello, expectedHello);
        
		String expectedCathy = "https://cs1632summer2019.herokuapp.com/cathy";
		String attributeCathy = driver.findElement(By.linkText("Cathedral Pics")).getAttribute("href");		
        assertEquals(attributeCathy, expectedCathy);
	}

	/*
	 * 
	 * REQUIREMENT 3: The factorial page (/fact) shall allow a user to enter a
	 * positive integer from 1 to 100, and upon pressing submit, shall show to the
	 * user the factorial of the value (e.g. "Factorial of 5 is 120!").
	 * 
	 * REQUIREMENT 5: For both the Fibonacci and Factorial pages, if a user enters
	 * an invalid value of any kind, they shall be informed that the value is 1
	 * (e.g., "Fibonacci of -100 is 1!")
	 * 
	 */
	
	@Test
	public void factPageValidInput1() {
		String expected = "Factorial of 1 is 1!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fact");
		waitUntil(d -> d.findElement(By.name("value")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.xpath("//input[@value='Submit']")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.name("value"));
		inputBox.sendKeys("1");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// get printed text
		By response = By.xpath("//h2");
		waitUntil(d -> d.findElement(response).isDisplayed()); // wait
		String actual = driver.findElement(response).getText();

		assertEquals(expected, actual);
	}
	
	@Test
	public void factPageValidInput100() {
		String expected = "Factorial of 100 is 93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000!";

		driver.get("https://cs1632summer2019.herokuapp.com/fact");
		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fact");
		waitUntil(d -> d.findElement(By.name("value")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.xpath("//input[@value='Submit']")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.name("value"));
		inputBox.sendKeys("100");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// get printed text
		By response2 = By.xpath("//h2");
		waitUntil(d -> d.findElement(response2).isDisplayed()); // wait
		String actual = driver.findElement(response2).getText();

		assertEquals(expected, actual);
		
	}

	@Test
	public void factPageInvalidInputZero() {
		String expected = "Factorial of 0 is 1!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fact");
		waitUntil(d -> d.findElement(By.name("value")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.xpath("//input[@value='Submit']")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.name("value"));
		inputBox.sendKeys("0");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// get printed text
		By response = By.xpath("//h2");
		waitUntil(d -> d.findElement(response).isDisplayed()); // wait
		String actual = driver.findElement(response).getText();

		assertEquals(expected, actual);
		
	}
	
	@Test
	public void factPageInvalidInput101() {		
		//Test invalid input out of range [1,100]
		String expected = "Factorial of 101 is 1!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fact");
		waitUntil(d -> d.findElement(By.name("value")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.xpath("//input[@value='Submit']")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.name("value"));
		inputBox.sendKeys("101");
		driver.findElement(By.xpath("//input[@value='Submit']")).click();

		// get printed text
		By response3 = By.xpath("//h2");
		waitUntil(d -> d.findElement(response3).isDisplayed()); // wait
		String actual = driver.findElement(response3).getText();

		// compare
		assertEquals(expected, actual);
	}

	/*
	 * REQUIREMENT 4: The Fibonacci page (/fib) shall allow a user to enter a
	 * positive integer from 1 to 32, and upon pressing submit, shall show to the
	 * user the Fibonnaci of the value (e.g. "Fibonacci of 5 is 8!").
	 * 
	 * 
	 * REQUIREMENT 5: For both the Fibonacci and Factorial pages, if a user enters
	 * an invalid value of any kind, they shall be informed that the value is 1
	 * (e.g., "Fibonacci of -100 is 1!")
	 *
	 * 
	 */
	@Test
	public void fibonacciPageValidInputOne() {
		String expected = "Fibonacci of 1 is 1!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fib");
		waitUntil(d -> d.findElement(By.id("tb1")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.id("sub")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.id("tb1"));
		inputBox.sendKeys("1");
		driver.findElement(By.id("sub")).click();

		// get printed text
		By response = By.xpath("//h2");
		waitUntil(d -> d.findElement(response).isDisplayed()); // wait
		String actual = driver.findElement(response).getText();

		assertEquals(expected, actual);
	}
	
	@Test
	public void fibonacciPageValidInput32() {
		//Test input of upper bound
		 String expected = "Fibonacci of 32 is 2178309!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fib");
		waitUntil(d -> d.findElement(By.id("tb1")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.id("sub")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.id("tb1"));
		inputBox.sendKeys("30");
		driver.findElement(By.id("sub")).click();

		// get printed text
		By response2 = By.xpath("//h2");
		waitUntil(d -> d.findElement(response2).isDisplayed()); // wait
		String actual = driver.findElement(response2).getText();

		assertEquals(expected, actual);
	}


	@Test
	public void fibonacciPageInvalidInputZero() {
		String expected = "Fibonacci of 0 is 1!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fib");
		waitUntil(d -> d.findElement(By.id("tb1")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.id("sub")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.id("tb1"));
		inputBox.sendKeys("0");
		driver.findElement(By.id("sub")).click();

		// get printed text
		By response = By.xpath("//h2");
		waitUntil(d -> d.findElement(response).isDisplayed()); // wait
		String actual = driver.findElement(response).getText();

		// compare
		assertEquals(expected, actual);
	}


	@Test
	public void fibonacciPageInvalidInput33() {
		String expected = "Fibonacci of 33 is 1!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/fib");
		waitUntil(d -> d.findElement(By.id("tb1")).isDisplayed()); // wait
		waitUntil(d -> d.findElement(By.id("sub")).isDisplayed()); // wait

		// find input box, input #, and submit
		WebElement inputBox = driver.findElement(By.id("tb1"));
		inputBox.sendKeys("33");
		driver.findElement(By.id("sub")).click();

		// get printed text
		By response = By.xpath("//h2");
		waitUntil(d -> d.findElement(response).isDisplayed()); // wait
		String actual = driver.findElement(response).getText();

		// compare
		assertEquals(expected, actual);
	}

	/*
	 * REQUIREMENT 6: Accessing the hello page (/hello) with no trailing values in
	 * the URL shall display the message "Hello CS1632, from Prof. Iser!".
	 * 
  	 * REQUIREMENT 7: If a trailing value is provided in the URL when accessing the
	 * /hello page, then the message shall display hello from that trailing value.
	 * For example, when accessing /hello/Jazzy, the system shall display
	 * "Hello CS1632, from Jazzy!". This shall work for all input values.
	 * 
	 */

	@Test
	public void helloPage() {
		String expected = "Hello CS1632, from Prof. Iser!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/hello");
		waitUntil(d -> d.findElement(By.xpath("//h2")).isDisplayed()); // wait

		// get text in h2
		By text = By.xpath("//h2");
		waitUntil(d -> d.findElement(text).isDisplayed()); // wait
		String actual = driver.findElement(text).getText();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void helloPageJazzy() {	
		String expected = "Hello CS1632, from Jazzy!";

		// go to page
		driver.get("https://cs1632summer2019.herokuapp.com/hello/Jazzy");
		waitUntil(d -> d.findElement(By.xpath("//h2")).isDisplayed()); // wait

		// get text in h2
		By text2 = By.xpath("//h2");
		waitUntil(d -> d.findElement(text2).isDisplayed()); // wait
		String actual = driver.findElement(text2).getText();

		assertEquals(expected, actual);
	}
