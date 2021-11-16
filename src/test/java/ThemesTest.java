import org.junit.jupiter.api.Test;

import ru.alishev.springcourse.models.Themes;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;

class ThemesTest {
	
	@Test
	@DisplayName("Test isTheme")
	void testIsTheme()
	{
		Themes theme=new Themes();
		assertTrue(theme.isThemes("caiet"));
		assertTrue(theme.isThemes("fisa"));
		assertTrue(theme.isThemes("raport"));
		assertTrue(theme.isThemes("prezentare"));
	}

}
