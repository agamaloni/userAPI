package com.example.userAPIbackend;

import com.example.userAPIbackend.entity.User;
import com.example.userAPIbackend.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserAppApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	User[] users = new User[] {
			User.builder()
				.userId(1L)
				.firstName("Agam")
				.lastName("Aloni")
				.email("aa@hfhg.com")
				.password("Qq!10000").build(),

		User.builder().userId(2L).firstName("Dan").lastName("Ban").email("bb@hfhg.com").
				password("Aa!10000").build()
	};

	@BeforeEach
	void init(){
		Arrays.stream(users).forEach(userRepository::save);
	}

	@AfterEach
	void cleanRepo(){
		userRepository.deleteAll();
	}

	@Test
	@Order(1)
	void getAllUsersSuccess() throws Exception {
		RequestBuilder request =
				MockMvcRequestBuilders.get("/user/all")
						.contentType("application/json");

		mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().json("[\n" +
						"  {\n" +
						"    \"userId\": 1,\n" +
						"    \"firstName\": \"Agam\",\n" +
						"    \"lastName\": \"Aloni\",\n" +
						"    \"email\": \"aa@hfhg.com\",\n" +
						"    \"password\": \"Qq!10000\"\n" +
						"  },\n" +
						"  {\n" +
						"    \"userId\": 2,\n" +
						"    \"firstName\": \"Dan\",\n" +
						"    \"lastName\": \"Ban\",\n" +
						"    \"email\": \"bb@hfhg.com\",\n" +
						"    \"password\": \"Aa!10000\"\n" +
						"  }\n" +
						"]"));

		assertThat(userRepository.findAll()).hasSize(2);
	}

	@Test
	void testAddUserSuccessful() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/user")
				.content("{\n" +
						"    \"firstName\": \"fgfail\",\n" +
						"    \"lastName\": \"DDDhDgjhgjD\",\n" +
						"    \"email\": \"dsfgf@gmaidf.com\",\n" +
						"    \"password\": \"Qq1!0000\"\n" +
						"}")
				.contentType("application/json");

		MvcResult mvcResult = mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful()).andReturn();

		System.out.println(mvcResult.getResponse());
		System.out.println(mvcResult.getModelAndView());

	}

	@Test
	void testAddUserFirstNameFail() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/user")
				.content("{\n" +
						"    \"firstName\": \"\",\n" +
						"    \"lastName\": \"DDDhDgjhgjD\",\n" +
						"    \"email\": \"dsfgf@gmaidf.com\",\n" +
						"    \"password\": \"Qq1!0000\"\n" +
						"}")
				.contentType("application/json");

		MvcResult mvcResult = mockMvc.perform(request)

				.andExpect(status().is4xxClientError()).andReturn();

		System.out.println(mvcResult.getModelAndView());
	}

	@Test
	void testAddUserLastNameFail() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/user")
				.content("{\n" +
						"    \"firstName\": \"asdf\",\n" +
						"    \"lastName\": \"\",\n" +
						"    \"email\": \"dsfgf@gmaidf.com\",\n" +
						"    \"password\": \"Qq1!0000\"\n" +
						"}")
				.contentType("application/json");

		 mockMvc.perform(request)
				.andExpect(status().is4xxClientError()).andReturn();
	}

	@Test
	void testAddUserEmailFail() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/user")
				.content("{\n" +
						"    \"firstName\": \"asdf\",\n" +
						"    \"lastName\": \"fsgh\",\n" +
						"    \"email\": \"dsfgfgmaidf.com\",\n" +
						"    \"password\": \"Qq1!0000\"\n" +
						"}")
				.contentType("application/json");

		mockMvc.perform(request)
				.andExpect(status().is4xxClientError()).andReturn();
	}

	@Test
	void testAddUserPasswordFail() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders.post("/user")
				.content("{\n" +
						"    \"firstName\": \"asdf\",\n" +
						"    \"lastName\": \"fsgh\",\n" +
						"    \"email\": \"dsfgfgmaidf.com\",\n" +
						"    \"password\": \"Qq10000\"\n" +
						"}")
				.contentType("application/json");

		mockMvc.perform(request)
				.andExpect(status().is4xxClientError()).andReturn();
	}




}


