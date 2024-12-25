package p1.teamtodo.home;

import org.apache.ibatis.annotations.Mapper;
import p1.teamtodo.project.model.dto.ProjectHomeDto;

import java.util.List;

@Mapper
public interface HomeMapper {

    List<ProjectHomeDto> selProjectList(String date, long userNo);

    List<String> selProjectMembersPic(long projectNo);
}
