package com.creito.apicreito.repository;

import com.creito.apicreito.entity.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsuarioRepositoryTest {
    @Autowired
    UsuarioRepository repository;

    @Test
    public void testSave(){
        Usuario u = new Usuario();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("teste@teste@gmail.com");
        Usuario response = repository.save(u);
        assertNotNull(response);
    }
}
