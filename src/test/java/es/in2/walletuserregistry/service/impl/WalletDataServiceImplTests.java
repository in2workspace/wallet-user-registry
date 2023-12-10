//package es.in2.walletuserregistry.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import es.in2.walletuserregistry.configuration.properties.WalletDataProperties;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@ExtendWith(SpringExtension.class)
//class WalletDataServiceImplTests {
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    @Mock
//    private WalletDataProperties walletDataProperties;
//
//    @InjectMocks
//    private WalletDataServiceImpl walletDataService;
//
//    @Test
//    void testSaveUser() {
//        // Arrange
//        UserRegistryRequest mockUserRegistryRequest = UserRegistryRequest.builder()
//                .username("j.doe")
//                .password("1234")
//                .email("john.doe@example.com")
//                .build();
//        // Mock
//        when(walletDataProperties.url()).thenReturn("https://example.com");
//        // Mock the postRequest method
//        try (MockedStatic<HttpUtils> httpUtilsMockedStatic = Mockito.mockStatic(HttpUtils.class)) {
//            when(postRequest(anyString(), anyString(), anyList(), anyString()))
//                    .thenReturn(Mono.empty());
//            doReturn(Mono.empty()).when(walletDataService.saveUser(anyString(), any(), anyString()));
//            // Act
//            Mono<Void> result = walletDataService.saveUser(eq("processId"), eq(mockUserRegistryRequest), eq( "1234"));
//            // Assert
//            result.as(StepVerifier::create).verifyComplete();
//        }
//    }
//
//}
