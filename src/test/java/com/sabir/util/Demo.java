package com.sabir.util;

import org.testng.annotations.Test;

public class Demo {
	@Test
	public void demo() {
		System.setProperty("browser", "firefox");
		Config.initialize();
	}

}
