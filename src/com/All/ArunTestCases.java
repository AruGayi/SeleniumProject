package com.All;



import org.testng.annotations.Test;


public class ArunTestCases  {
	
	

	@Test(priority=1)
	public void Arun1()
	{
		System.out.println("Hello I am trying to workout Test Ng Framework1");
	}
	@Test(priority=2,dependsOnMethods="Arun1")
	public void Arun2()
	{
		System.out.println("Hello I am trying to workout Test Ng Framework2");
	}
	
	
	@Test(priority=3,dependsOnMethods="Arun2")
	public void Arun3()
	{
		System.out.println("Hello I am trying to workout Test Ng Framework3");
	}
	@Test(priority=4,dependsOnMethods="Arun3")
	public void Arun4()
	{
		System.out.println("Hello I am trying to workout Test Ng Framework4");
	}
	@Test(priority=5,dependsOnMethods="Arun4")
	public void Arun5()
	{
		System.out.println("Hello I am trying to workout Test Ng Framework5");
	}
}
