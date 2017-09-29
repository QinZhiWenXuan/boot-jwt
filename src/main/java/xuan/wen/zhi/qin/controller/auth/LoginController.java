package xuan.wen.zhi.qin.controller.auth;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import xuan.wen.zhi.qin.model.JWTModel;
import xuan.wen.zhi.qin.util.jwt.JWTUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/login", method = {RequestMethod.POST}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
@ResponseBody
public class LoginController {

    @PostMapping
    public Map<String, String> login(@RequestBody JWTModel model) {
        System.out.println("model\t" + model);
        Map<String, String> results = new HashMap<>(2);
        results.put("code", "200");
        try {
            results.put("token", JWTUtils.sign(model, 1000 * 60));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return results;
    }
}
