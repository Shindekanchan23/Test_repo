package com.Tests;

import com.Utilities.BaseClass;

public class MutipleBrowser {
	BaseClass base = new BaseClass();

	@Test
	public void LaunchURL() {
		String[] browsers = { "Chrome", "Firefox", "Edge" };
		for (String browser : browsers) {
			System.out.println("testing on " + browser);
			base.OpenBrowser(browser);

		}
	}

}
