package ar.edu.unrn.userservice.controller.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class RoleDtoTest {

    @Test
    public void testRoleDto() {
        RoleDto roleDto = new RoleDto();
        roleDto.setDescription("rol cliente");
        roleDto.setName("rol_cliente");

        RoleDto roleDto1 = new RoleDto();
        roleDto1.setDescription("rol cliente");
        roleDto1.setName("rol_cliente");

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setDescription("rol cliente");
        roleDto2.setName("rol_cliente");

        assertThat(roleDto.getDescription()).isEqualTo("rol cliente");
        assertThat(roleDto.getName()).isEqualTo("rol_cliente");

        //Reflexividad
        assertEquals(roleDto, roleDto);

        //Simetría
        assertEquals(roleDto, roleDto1);
        assertEquals(roleDto1, roleDto);

        //Transitividad
        assertEquals(roleDto, roleDto1);
        assertEquals(roleDto1, roleDto2);
        assertEquals(roleDto, roleDto2);

        //consistencia
        Assertions.assertEquals(roleDto.equals(roleDto1), roleDto.equals(roleDto1));
        // Comparación con null:
        Assertions.assertNotEquals(null, roleDto);
    }

    @Test
    public void testHashCodeConsistency() {
        RoleDto role1 = new RoleDto();
        role1.setName("cliente");
        RoleDto role2 = new RoleDto();
        role2.setName("cliente");

        assertEquals(role1.hashCode(), role2.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        RoleDto role1 = new RoleDto();
        role1.setName("cliente");
        RoleDto role2 = new RoleDto();
        role2.setName("vendedor");

        assertNotEquals(role1.hashCode(), role2.hashCode());
    }
}
