package dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.example.toDoListTasks.dto.UrgencyDTO;

import nl.jqno.equalsverifier.EqualsVerifier;

public class UrgencyDtoUnitTest {

	@Test
	public void urgencyDtoEqualsTest() {
		EqualsVerifier.simple().forClass(UrgencyDTO.class).verify();
	}
	
	@Test
	public void urgencyToStringTest() {
		UrgencyDTO urgency = new UrgencyDTO();
		
		String result = urgency.toString();
		String expected = "UrgencyDTO [id=" + 0 + ", name=" + null + ", tasks=" + null + "]";
		
		assertEquals(expected, result);
	}
	
	@Test
	public void urgencyDtoAllArgsConstructorTest() {
		UrgencyDTO urgency = new UrgencyDTO(1, "List", new ArrayList());
		
		assertNotNull(urgency.getId());
		assertNotNull(urgency.getName());
		assertNotNull(urgency.getTasks());
		
		assertEquals(1, urgency.getId());
		assertEquals("List", urgency.getName());
		assertEquals(new ArrayList(), urgency.getTasks());
	}
	
	@Test
	public void setUrgencyDtoTest() {
		UrgencyDTO urgency = new UrgencyDTO();
		
		urgency.setId(1);
		urgency.setName("List");
		urgency.setTasks(new ArrayList());
		
		assertEquals(1, urgency.getId());
		assertEquals("List", urgency.getName());
		assertEquals(new ArrayList(), urgency.getTasks());
	}
}
