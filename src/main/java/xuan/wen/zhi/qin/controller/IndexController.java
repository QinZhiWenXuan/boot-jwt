package xuan.wen.zhi.qin.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/index", method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@ResponseBody
public class IndexController {
    @GetMapping()
    public Map<String,Object> index () {
        Map<String,Object> results = new HashMap<>(1) ;
        System.out.println("index.........");
        results.put("code",200);
        return results ;
    }
}
