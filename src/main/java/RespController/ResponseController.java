package RespController;

import Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ResponseService.ResponseService;

@RestController
public class ResponseController {

    @Autowired
    private ResponseService responseService;

    @PostMapping("/responses")
    public void saveResponse(@RequestBody Response response) {
        responseService.saveResponse(response);
    }
}
