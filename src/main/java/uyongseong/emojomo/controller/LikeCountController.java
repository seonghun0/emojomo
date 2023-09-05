package uyongseong.emojomo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uyongseong.emojomo.Service.LikeCountService;
import uyongseong.emojomo.domain.LikeCount;
import uyongseong.emojomo.domain.ResponseDto;

import java.nio.charset.Charset;
import java.util.HashMap;

@RestController
@RequestMapping(value = "like")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://quickmoji.site", "http://localhost"})
public class LikeCountController {

    private final LikeCountService likeCountService;

    @PostMapping(value = "up")
    public ResponseEntity<ResponseDto> likeCountUp(@RequestBody LikeCount likeCount)
    {
        likeCountService.save(likeCount);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Long count = likeCountService.getCount();
        ResponseDto responseDto = new ResponseDto().createRespData(
                true,
                200,
                "",
                new HashMap<String, Object>() {{
                    put("count", count);
                }}
        );

        return new ResponseEntity<>(responseDto, header, HttpStatus.OK);
    }

    @GetMapping(value = "getCount")
    public ResponseEntity<ResponseDto> getCount()
    {
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Long count = likeCountService.getCount();
        ResponseDto responseDto = new ResponseDto().createRespData(
                true,
                200,
                "",
                new HashMap<String, Object>() {{
                    put("count", count);
                }}
        );

        return new ResponseEntity<>(responseDto, header, HttpStatus.OK);
    }
}
