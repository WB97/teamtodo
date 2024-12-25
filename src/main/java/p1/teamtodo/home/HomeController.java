package p1.teamtodo.home;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import p1.teamtodo.common.ResponseResult;

@Slf4j
@RestController
@RequestMapping("main")
@RequiredArgsConstructor
@Validated
@Tag(name = "메인 홈")
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    @Operation(summary = "달력 프로젝트 정보", description = "로그인 유저의 특정 년월에 존재하는 프로젝트 정보. 양식은 YYYYmm")
    public ResponseResult getHome(@NotBlank @RequestParam String date,
                                  @NotNull @RequestParam long signedUserNo
    ) {
        return homeService.getHome(date, signedUserNo);
    }

    @GetMapping("/{projectNo}")
    @Operation(summary = "달력 프로젝트 유저들 사진", description = "달력에 있는 프로젝트 마우스 호버 시 해당 프로젝트 유저들 사진 GET 요청")
    public ResponseResult getMembersPic(@NotNull @RequestParam long projectNo) {
        return homeService.getMembersPic(projectNo);
    }
}

