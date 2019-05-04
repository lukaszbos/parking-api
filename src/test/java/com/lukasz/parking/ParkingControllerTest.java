package com.lukasz.parking;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
class ParkingControllerTest {
    private MockMvc mvc;

    @Mock
    private ParkingService parkingService;

    @InjectMocks
    private ParkingController parkingController;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(parkingController).build();
    }

    @Test
    void getParkingtestdsa() throws Exception {
        Parking parking = new Parking(1, "parking");
        List<Parking> parkings = new ArrayList<>();
        parkings.add(parking);
        //http://localhost:8080
        URI url = URI.create("/parkings?parkingId=1");

        String json = "{\n" +
                "  \"title\": \"Greetings\",\n" +
                "  \"value\": \"Hello World\"\n" +
                "}";

        mvc.perform(post("/parkings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
        verify(parkingService).getParkings();
        //List<Parking> allParkings = Collections.singletonList(parking);
//        given(parkingController.getParking(1)).willReturn(parking);
//        mvc
//                .perform(get(url)
//                        .accept(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}