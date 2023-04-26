package com.creito.apicreito.repository;

import com.creito.apicreito.entity.Usuario;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {
    private static final String EMAIL = "email@teste.com";
    @Autowired
    UsuarioRepository repository;

    @Before
    public void setUp(){
        Usuario u = new Usuario();
        u.setName("Set Up User");
        u.setPassword("Senha123");
        u.setEmail(EMAIL);

        repository.save(u);
    }

    @After
    public void tearDown(){
        repository.deleteAll();
    }
    @Test
    public void testSave(){
        Usuario u = new Usuario();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("teste@teste@gmail.com");
        Usuario response = repository.save(u);
        assertNotNull(response);
    }

    public void testFindByEmail(){
        Optional<Usuario> response = repository.findByEmailEquals(EMAIL);
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(),EMAIL);
    }
}
