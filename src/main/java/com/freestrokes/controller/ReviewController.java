package com.freestrokes.controller;

import com.freestrokes.properties.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

// TODO: @RequestMapping
// 해당 컨트롤러 하위 전체 메서드에 공통 path 설정이 필요한 경우 class 상단에 어노테이션 사용.
// @RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    // TODO: ApplicationProperties 테스트를 위해 추가
    private final ApplicationProperties applicationProperties;

//    private final BoardService boardService;

    // TODO: DI(Dependency Injection)에 대한 설명
    // 일반적으로 아래 코드처럼 생성자를 통해 의존성 주입을 설정해줌.
    // 스프링 IoC Container가 해당 의존성 타입에 맞는 bean을 만들어서 주입 (의존성 관리)
    // 의존성 주입에는 생성자, 필드 + setter 2가지 방법이 있는데
    // 레퍼런스에서는 생성자를 이용한 방법을 권장 (생성자는 lombok의 @RequiredArgsConstructor를 사용해서 생략 가능)
    // 필드 + setter를 사용한 경우엔 의존성 주입 없이 인스턴스 생성이 가능하다는 문제가 있음. (이 경우엔 @Autowired 어노테이션을 사용해야 함)
    // 생성자를 사용한 경우엔 순환 참조가 발생할 수 있음.

    // TODO: 생성자를 이용한 의존성 주입(DI) 예시.
//    public BoardController(ApplicationProperties applicationProperties, BoardService boardService) {
//        this.applicationProperties = applicationProperties;
//        this.boardService = boardService;
//    }

//    @GetMapping(path = PathConstants.BOARDS, produces = "application/json")
//    @Operation(
//        summary = "게시글 목록 조회",
//        description = "페이징을 이용하여 게시글 목록을 조회한다."
//    )
//    // TODO: AOP 확인을 위해 추가 (@LogExecutionTime)
//    @LogExecutionTime
//    public ResponseEntity<Page<BoardDto.ResponseDto>> getBoards(
//        // TODO: size, sort, direction 프로퍼티를 설정한 @PageableDefault 예시
////        @ParameterObject @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
//        @ParameterObject @PageableDefault(size = 10) Pageable pageable
//    ) throws Exception {
//
//        // TODO: ApplicationProperties 테스트를 위해 추가
//        System.out.println("applicationProperties: " + applicationProperties);
//
//        Page<BoardDto.ResponseDto> result = boardService.getBoards(pageable);
//        return new ResponseEntity<Page<BoardDto.ResponseDto>>(result, HttpStatus.OK);
//    }
//
//    @PostMapping(path = PathConstants.BOARDS, produces = "application/json")
//    @Operation(
//        summary = "게시글 등록",
//        description = "게시글 정보를 등록한다."
//    )
//    public ResponseEntity<BoardDto.ResponseDto> postBoard(
//        @RequestBody BoardDto.RequestDto boardRequestDto
//    ) throws Exception {
//        BoardDto.ResponseDto result = boardService.postBoard(boardRequestDto);
//        return new ResponseEntity<BoardDto.ResponseDto>(result, HttpStatus.OK);
//    }
//
//    @PutMapping(path = PathConstants.BOARD, produces = "application/json")
//    @Operation(
//        summary = "게시글 수정",
//        description = "게시글 ID를 이용하여 게시글 정보를 수정한다."
//    )
//    public ResponseEntity<BoardDto.ResponseDto> putBoard(
//        @PathVariable("boardId") String boardId,
//        @RequestBody BoardDto.RequestDto boardRequestDto
//    ) throws Exception {
//        BoardDto.ResponseDto result = boardService.putBoard(boardId, boardRequestDto);
//        return new ResponseEntity<BoardDto.ResponseDto>(result, HttpStatus.OK);
//    }
//
//    @DeleteMapping(path = PathConstants.BOARD, produces = "application/json")
//    @Operation(
//        summary = "게시글 삭제",
//        description = "게시글 ID를 이용하여 게시글 정보를 삭제한다."
//    )
//    public ResponseEntity<?> deleteBoard(
//        @PathVariable("boardId") String boardId
//    ) throws Exception {
//        boardService.deleteBoard(boardId);
//        return new ResponseEntity<>("{}", HttpStatus.OK);
//    }

}
