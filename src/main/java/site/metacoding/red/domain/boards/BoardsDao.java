package site.metacoding.red.domain.boards;

import java.util.List;

import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.boards.WriteDto;
import site.metacoding.red.web.dto.request.users.JoinDto;

public interface BoardsDao {
	public void insert(JoinDto joinDto);
	public Users findById(Integer id);
	public List<Boards> findAll();
	public void update(Users usersPS);
	public void delete(Integer id);
}
