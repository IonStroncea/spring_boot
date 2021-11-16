import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import ru.alishev.springcourse.models.Postuniv;
import ru.alishev.springcourse.models.User;

class PostunivTest {
	
	
	
	@Test
	@DisplayName("Test setters and getters")
	void testSettersAndGetter() {
		Postuniv postuniv=new Postuniv();
		postuniv.setTheme("Test");
		postuniv.setText("Test");
		User testuser=new User();
		postuniv.setAuthor(testuser);
		assertEquals("Test",postuniv.getTheme());
		assertEquals("Test",postuniv.getText());
		assertSame(testuser,postuniv.getAuthor());
	}
	@Test
	@DisplayName("Test constructors")
	void testConstructor()
	{
		Postuniv postuniv=new Postuniv();
		assertNull(postuniv.getText());
		assertNull(postuniv.getAuthor());
		assertNull(postuniv.getTheme());
		User testuser=new User();
		postuniv=new Postuniv("Test",testuser,"Test");
		assertEquals("Test",postuniv.getTheme());
		assertEquals("Test",postuniv.getText());
		assertSame(testuser,postuniv.getAuthor());
	}

}
