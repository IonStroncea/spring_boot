package ru.alishev.springcourse.models;

import org.springframework.stereotype.Component;

@Component
public class Themes {
	
	private String[] listThemes= {"caiet","fisa","raport","prezentare"};
	
	public boolean isThemes(String thems)
	{
		boolean exists=false;
		for(int i=0;i<listThemes.length;i++) 
		{
			if(listThemes[i].equals(thems))
			{
				exists=true;
			}
		}
		return exists;
	}
}
