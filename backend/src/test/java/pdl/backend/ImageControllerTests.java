package pdl.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.ImageIcon;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ImageControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@BeforeAll
	public static void reset() {
	// reset Image class static counter
		ReflectionTestUtils.setField(Image.class, "count", Long.valueOf(0));
	}

	@Test
	@Order(1)
	public void getImageListShouldReturnSuccess() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/images");
		mockMvc.perform(request).andExpect(status().is2xxSuccessful());
	}

	@Test
	@Order(2)
	public void getImageShouldReturnNotFound() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/images/1");
		mockMvc.perform(request).andExpect(status().isNotFound());
	}

	@Test
	@Order(3)
	public void getImageShouldReturnSuccess() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/images/0");
		mockMvc.perform(request).andExpect(status().is2xxSuccessful());
	}

	@Test
	@Order(4)
	public void deleteImageShouldReturnBadRequest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/image/-1");
		mockMvc.perform(request).andExpect(status().isBadRequest()); // a refaire
	}

	@Test
	@Order(5)
	public void deleteImageShouldReturnNotFound() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/images/1");
		mockMvc.perform(request).andExpect(status().isNotFound());
	}

	@Test
	@Order(6)
	public void deleteImageShouldReturnSuccess() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/images/0");
		mockMvc.perform(request).andExpect(status().isNoContent());
	}

	@Test
	@Order(7)
	public void createImageShouldReturnSuccess() throws Exception {
		byte[] fileContent = Files.readAllBytes(Paths.get("/home/ayoub/Desktop/test2.jpeg"));
		//java.awt.Image image = new ImageIcon(this.getClass().getResource("/home/ayoub/Desktop/test2.jpg")).getImage();
		MockMultipartFile file_test = new MockMultipartFile("file", "test2.jpg", "images/jpeg", fileContent);
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/images").file(file_test)).andExpect(status().is2xxSuccessful());
	}	
	
	@Test
	@Order(8)
	public void createImageShouldReturnUnsupportedMediaType() throws Exception {
		MockMultipartFile file_test = new MockMultipartFile("file", "test-file.gif", "images/gif", "Hello World!".getBytes());
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/images").file(file_test)).andExpect(status().isUnsupportedMediaType());
	}
	
}
