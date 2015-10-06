package com.bikiegang.ridesharing.DreamCatcher.api;

import com.bikiegang.ridesharing.DreamCatcher.ApiDescription;
import com.bikiegang.ridesharing.DreamCatcher.ParameterDescription;
import com.bikiegang.ridesharing.pojo.response.UserShortDetailResponse;

/**
 * Created by hpduy17 on 10/1/15.
 */
@ApiDescription(
        description = "description"
)
public class TestAPI {
    @ParameterDescription(
            isOptional = true,
            description = "para des",
            type = ParameterDescription.ParaType.PRIMARY

    )
    long para1;

    @ParameterDescription(
            isOptional = false,
            description = "para des",
            type = ParameterDescription.ParaType.OBJECT

    )
    UserShortDetailResponse para2;


}
