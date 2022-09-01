package site.metacoding.red.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.red.domain.boards.BoardsDao;
import site.metacoding.red.domain.users.Users;
import site.metacoding.red.web.dto.request.users.JoinDto;
import site.metacoding.red.web.dto.request.users.UpdateDto;
import site.metacoding.red.web.dto.response.RespDto;


@RequiredArgsConstructor
@RestController
public class UsersController {

	private final BoardsDao boardsdao;
	
	
	
	@GetMapping("/users/{id}")
	public RespDto<?> getUsers(@PathVariable Integer id) {
		
		
		return new RespDto<>(1,"성공",boardsdao.findById(id));
	}
	
	@GetMapping("/users")
	public RespDto<?> getUsersList(){
		return new RespDto<>(1, "성공", boardsdao.findAll());
	}
	
	
	@PostMapping("/users")
	public RespDto<?> insert(JoinDto joinDto) {
		boardsdao.insert(joinDto);
		return new RespDto<>(1, "회원가입완료", null);
		 // 201번 - INSERT 됨
	}
	
	@PutMapping("/users/{id}")
	public RespDto<?> update(@PathVariable Integer id,UpdateDto updateDto){
		// 1번 : id로 select 하자 ( 영속화 )
		Users usersPS = boardsdao.findById(id);
		
		// 2번 : 변경 
		usersPS.전체수정(updateDto);
	
		// 3번 : 영속화된 오브젝트로 update치기 
		boardsdao.update(usersPS);
		return new RespDto<>(1,"회원수정완료",null);
	}
	@PutMapping("/users/{id}/password")
	public RespDto<?> updatePassword(@PathVariable Integer id, String password){
		// 1번 영속화 
		Users usersPS = boardsdao.findById(id);
		
		
		// 2번 변경
		usersPS.패스워드수정(password);
		
		// 3번 전체 업데이트
		boardsdao.update(usersPS);
		return new RespDto<>(1, "회원패스워드 수정완료",null);
	}
	
}
