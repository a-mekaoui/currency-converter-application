package com.github.amekaoui.currencyconverter.repository;

import com.github.amekaoui.currencyconverter.domain.UserCredentialEntity;
import com.github.amekaoui.currencyconverter.domain.UserCredentialTestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryIntegrationTest {

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() {
    }

    @Test
    @SqlGroup({
            @Sql(statements = "INSERT INTO USER_CREDENTIAL (EMAIL, PASSWORD, AUTHORITY, BIRTH_DATE, STREET, ZIP_CODE, CITY, COUNTRY) VALUES " +
                    "('test2@test.com', '$2a$10$fx3DJpvpCo2QtrNMv5Rkp.H9FCToWzKDUgyCsy1Ugi9AmmM/U4doS', 'ROLE_USER', '1980-01-01', 'test', 'test', 'test', 'BELGIUM')"
                    , executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(statements = "DELETE FROM USER_CREDENTIAL WHERE EMAIL='test2@test.com'"
                    , executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void findUserCredentialByEmail() {
        UserCredentialEntity userCredentialEntity = this.userRepository.findByEmail("test2@test.com");
        Assert.assertNotNull(userCredentialEntity);
        Assert.assertEquals("test2@test.com", userCredentialEntity.getEmail());
    }

    @Test
    public void find() {
        // given
        UserCredentialEntity userCredentialEntity = UserCredentialTestUtils.initNewUserCredentialEntity();
        userCredentialEntity.setEmail("test2@test.com");
        userCredentialEntity.setAuthority("ROLE_USER");
        this.userRepository.save(userCredentialEntity);

        //when
        UserCredentialEntity retrievedUserCredential = this.userRepository.findByEmail("test2@test.com");

        //then
        Assert.assertEquals("test2@test.com", retrievedUserCredential.getEmail());

    }
}