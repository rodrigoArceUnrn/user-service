package ar.edu.unrn.userservice.dto;

import ar.edu.unrn.userservice.dto.RoleDto;
import ar.edu.unrn.userservice.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class UserDtoTest {

    @Test
    public void testUserDto() {

        RoleDto roleDto = new RoleDto();
        roleDto.setDescription("rol cliente");
        roleDto.setName("rol_cliente");

        UserDto userDto = new UserDto();
        userDto.setEmail("rodri@gmail.com");
        userDto.setPassword("1234");
        userDto.setUsername("rarce");
        userDto.setRole(roleDto);

        RoleDto roleDto1 = new RoleDto();
        roleDto1.setDescription("rol cliente");
        roleDto1.setName("rol_cliente");

        UserDto userDto1 = new UserDto();
        userDto1.setEmail("rodri@gmail.com");
        userDto1.setPassword("1234");
        userDto1.setUsername("rarce");
        userDto1.setRole(roleDto1);

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setDescription("rol cliente");
        roleDto2.setName("rol_cliente");

        UserDto userDto2 = new UserDto();
        userDto2.setEmail("rodri@gmail.com");
        userDto2.setPassword("1234");
        userDto2.setUsername("rarce");
        userDto2.setRole(roleDto2);

        assertThat(userDto.getRole().getDescription().equals("rol cliente")).isEqualTo(true);
        assertThat(userDto.getRole().getName().equals("rol_cliente")).isEqualTo(true);
        assertThat(userDto.getEmail().equals("rodri@gmail.com")).isEqualTo(true);
        assertThat(userDto.getPassword().equals("1234")).isEqualTo(true);
        assertThat(userDto.getUsername().equals("rarce")).isEqualTo(true);

        //Reflexividad
        assertEquals(userDto, userDto);

        //Simetría
        assertEquals(userDto, userDto1);
        assertEquals(userDto1, userDto);

        //Transitividad
        assertEquals(userDto, userDto1);
        assertEquals(userDto1, userDto2);
        assertEquals(userDto, userDto2);

        //consistencia
        Assertions.assertEquals(userDto.equals(userDto1), userDto.equals(userDto1));

        // Comparación con null
        Assertions.assertNotEquals(null, userDto);
    }

    @Test
    public void testHashCodeConsistency() {
        UserDto user1 = new UserDto();
        user1.setUsername("rodri");
        UserDto user2 = new UserDto();
        user2.setUsername("rodri");

        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        UserDto user1 = new UserDto();
        user1.setUsername("rodri");
        UserDto user2 = new UserDto();
        user2.setUsername("alejandro");

        assertNotEquals(user1.hashCode(), user2.hashCode());
    }
}
