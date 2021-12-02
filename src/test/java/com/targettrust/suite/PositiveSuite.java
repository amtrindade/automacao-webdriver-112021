package com.targettrust.suite;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.targettrust.inter.*;
import com.targettrust.test.*;

@RunWith(Categories.class)
@SuiteClasses({CacheTest.class, CalcTest.class, 
		NavigationTabsTest.class, WebElementsTest.class})
@ExcludeCategory(NegativeInterface.class)
@IncludeCategory(PositiveInterface.class)

public class PositiveSuite {

}
