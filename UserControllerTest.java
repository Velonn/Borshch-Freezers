import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.MethodArgumentNotValidException;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    private final UserController userController = new UserController();
    private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    @Test
    @DisplayName("GET / - should return empty user list")
    void testGetAllUsersReturnsEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/похххх, потом сделаю как надо, главное не проебать сделать/"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @DisplayName("GET /{id} - should return UserDATA with given id")
    void testGetUserByIdReturnsUserWithId() throws Exception {
        int testId = 42;
        mockMvc.perform(MockMvcRequestBuilders.get("/похххх, потом сделаю как надо, главное не проебать сделать/{id}", testId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testId)));
    }

    @Test
    @DisplayName("POST / - should create user when data is valid")
    void testCreateUserSuccess() throws Exception {
        String json = "{\"surnameString\":\"Ivanov\",\"username\":\"ivan\",\"userEmail\":\"ivan@mail.com\",\"password\":\"pass\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/похххх, потом сделаю как надо, главное не проебать сделать/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surnameString", is("Ivanov")))
                .andExpect(jsonPath("$.username", is("ivan")))
                .andExpect(jsonPath("$.userEmail", is("ivan@mail.com")))
                .andExpect(jsonPath("$.password", is("pass")));
    }

    @Test
    @DisplayName("POST / - should throw when data is null")
    void testCreateUserNullData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/похххх, потом сделаю как надо, главное не проебать сделать/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("null"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("POST / - should throw when id is present")
    void testCreateUserWithIdThrows() throws Exception {
        String json = "{\"id\":1,\"surnameString\":\"Ivanov\",\"username\":\"ivan\",\"userEmail\":\"ivan@mail.com\",\"password\":\"pass\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/похххх, потом сделаю как надо, главное не проебать сделать/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isInternalServerError());
    }

    @Test
    @DisplayName("PUT / - should return update message")
    void testUpdateUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/похххх, потом сделаю как надо, главное не проебать сделать/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Update User"));
    }

    @Test
    @DisplayName("DELETE / - should return delete message")
    void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/похххх, потом сделаю как надо, главное не проебать сделать/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete User"));
    }
}