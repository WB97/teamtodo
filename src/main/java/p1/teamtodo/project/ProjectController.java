package p1.teamtodo.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.req.*;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Tag(name = "프로젝트")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{projectNo}")
    @Operation(summary = "특정 프로젝트 상세 정보", description = "signedUserNo 가 참여중인 projectNo의 프로젝트만 정보보기 가능")
    public ResponseResult getProjectDetail(@PathVariable long projectNo, @RequestParam long signedUserNo) {
        return projectService.getProjectDetail(projectNo, signedUserNo);
    }

    @GetMapping
    @Operation(summary = "로그인 유저의 프로젝트 목록", description = "page 가 0 일때 첫페이지")
    public ResponseResult getProjectList(@RequestParam long signedUserNo,
                                         @RequestParam(required = false) Integer page
    ) {
        if(page == null) {
            return projectService.getProjectList(signedUserNo);
        }
        return projectService.getProjectList(signedUserNo, page);
    }

    @PostMapping
    @Operation(summary = "프로젝트 생성 요청")
    public ResponseResult createProject(@RequestBody ProjectCreatePostReq req) {
        return projectService.createProject(req);
    }

    @GetMapping("/edit")
    @Operation(summary = "프로젝트 수정 데이터 요청")
    public ResponseResult getEditProject(@RequestParam long projectNo,
                                         @RequestParam long signedUserNo
    ) {
        return projectService.getEditProject(projectNo, signedUserNo);
    }

    @GetMapping("search-user/{nickname}")
    @Operation(summary = "프로젝트 수정에서 추가할 인원 닉네임으로 검색하기")
    public ResponseResult searchUser(@PathVariable String nickname) {
        return projectService.searchUserByNickname(nickname);
    }

    @PatchMapping
    @Operation(summary = "유저 잠금", description = "유저 잠금설정")
    public ResponseResult userLock(@Valid @RequestBody ProjectUserLockReq p){
        return projectService.userLock(p);
    }

    @PostMapping("search-user")
    @Operation(summary = "프로젝트 일원 수정", description = "프로젝트 인원 추가 및 삭제")
    public ResponseResult editUserList(@Valid @RequestBody ProjectUserEdit p){
        return projectService.editUserList(p);
    }

    @PutMapping
    @Operation(summary = "프로젝트 수정", description = "수정 PUT 요청")
    public ResponseResult putEditProject(@Valid @RequestBody ProjectEditPutReq req){
        return projectService.putEditProject(req);
    }

    @DeleteMapping
    @Operation(summary = "프로젝트 삭제", description = "삭제 DELETE 요청, 연관된 일정도 모두 삭제.")
    public ResponseResult deleteProject(@RequestBody ProjectDeleteReq req){
        return projectService.deleteProject(req);
    }
}
