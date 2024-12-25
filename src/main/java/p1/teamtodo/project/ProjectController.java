package p1.teamtodo.project;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.project.model.req.ProjectCreatePostReq;
import p1.teamtodo.project.model.req.ProjectListPaging;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
@Tag(name = "프로젝트")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/{projectNo}")
    @Operation(summary = "특정 프로젝트 상세 정보")
    public ResponseResult getProjectDetail(@PathVariable long projectNo, @RequestParam long signedUserNo) {
        return projectService.getProjectDetail(projectNo, signedUserNo);
    }

    @GetMapping
    @Operation(summary = "로그인 유저의 프로젝트 목록", description = "page 가 0 일때 첫페이지")
    public ResponseResult getProjectList(@RequestParam long signedUserNo,
                                         @RequestParam(required = false) Integer page) {
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
    public ResponseResult editProject(@RequestParam long projectNo,
                                      @RequestParam long signedUserNo
    ) {
        return projectService.editProject(projectNo, signedUserNo);
    }
}
