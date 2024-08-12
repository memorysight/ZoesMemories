package RespController;

import Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ResponseService.ResponseService;

@Controller
@RequestMapping("/responses")
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping
    public String saveResponse(@RequestBody Response response) {
        responseService.saveResponse(response);
        return "redirect:/";
    }
}