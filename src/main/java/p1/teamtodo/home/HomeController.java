package p1.teamtodo.home;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import p1.teamtodo.common.ResponseResult;
import p1.teamtodo.home.model.req.HomeGetReq;

@Slf4j
@RequestMapping("main")
@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping
    public ResponseResult getHome(@RequestBody HomeGetReq req) {
        log.info("req = {}", req);
        return homeService.getHome(req);
    }

}
