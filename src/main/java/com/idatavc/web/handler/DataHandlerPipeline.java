package com.idatavc.web.handler;

import com.idatavc.web.dto.ProjectDto;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;


@Component("dataHandlerPipeline")
public class DataHandlerPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {

        List<ProjectDto> projectDtoList = resultItems.get("projectDtoList");
    }
}
