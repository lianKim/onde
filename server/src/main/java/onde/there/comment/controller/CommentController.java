package onde.there.comment.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import onde.there.comment.service.CommentService;
import onde.there.dto.comment.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place/comment")
public class CommentController {

	private final CommentService commentService;

	@Transactional
	@Operation(summary = "장소에 댓글 쓰기", description = "장소에 댓글 쓰기")
	@PostMapping("/add")
	public ResponseEntity<Long> createComment(
		@RequestBody @Parameter(description = "댓글 리퀘스트", required = true) @Valid CommentDto.CreateRequest request) {
		return ResponseEntity.ok(commentService.createComment(request).getId());
	}

	@Transactional(readOnly = true)
	@Operation(summary = "장소 댓글 조회", description = "장소 댓글 조회")
	@GetMapping("/get")
	public ResponseEntity<List<CommentDto.Response>> getComments(
		@RequestParam(value = "id") @Parameter(description = "장소 아이디", required = true) Long placeId) {
		return ResponseEntity.ok(commentService.getComments(placeId));
	}

	@Transactional
	@Operation(summary = "장소 댓글 수정", description = "장소 댓글 수정")
	@PutMapping("/update")
	public ResponseEntity<Long> updateComment(
		@RequestBody @Parameter(description = "댓글 업데이트 리퀘스트", required = true) @Valid CommentDto.UpdateRequest request) {
		return ResponseEntity.ok(commentService.updateComment(request));
	}

	@Transactional
	@Operation(summary = "장소 댓글 삭제", description = "장소 댓글 삭제")
	@DeleteMapping("/delete")
	public ResponseEntity<Void> updateComment(
		@RequestParam(value = "id") @Parameter(description = "댓글 아이디", required = true) Long commentId) {
		commentService.deleteComment(commentId);
		return ResponseEntity.ok().build();
	}
}