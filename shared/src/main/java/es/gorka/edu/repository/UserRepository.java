package es.gorka.edu.repository;

import java.util.List;

import es.gorka.edu.dto.UserDTO;

public interface UserRepository extends Dao<UserDTO> {

	boolean existUser(UserDTO dto);

	List<UserDTO> searchAll(UserDTO dto);

}
