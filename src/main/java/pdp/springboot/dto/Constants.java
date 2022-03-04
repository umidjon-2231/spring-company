package pdp.springboot.dto;

import pdp.springboot.entity.ProjectEntity;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static List<ProjectEntity> entities=new ArrayList<>(List.of(
            new ProjectEntity("/company", "Company", new String[]{"name"}),
            new ProjectEntity("/department","Department",new String[]{"name", "company"}),
            new ProjectEntity("/employee","Employee", new String[]{"full_name", "department"}),
            new ProjectEntity("/bank","Bank",new String[]{}),
            new ProjectEntity("/card","Card",new String[]{}),
            new ProjectEntity("/address","Address",new String[]{})
    ));

}
