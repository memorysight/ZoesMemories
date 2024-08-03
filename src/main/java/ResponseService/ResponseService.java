package ResponseService;

import Model.Response;
import RespRepo.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Autowired
    private ResponseRepository responseRepository;

    public void saveResponse(Response response) {
        responseRepository.save(response);
    }
}